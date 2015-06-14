package morse;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;

import com.malt.morse.BooleanArray;
import com.malt.morse.MorseCode;
import com.malt.serial.SerialReader;

public class MorseSerialApplication extends Application implements UpdateIntervalListener, SerialPortEventListener {
	
	private MorseCode code = new MorseCode();
	
	private long lastPressedTimestamp = 0;
	
	private boolean pressed = false;
	
	private static final long MORSE_DASH = 800; //1.2 seconds
	private static final long EVALUATE_TIME = 3000; //3 seconds
	
	private List<Boolean> sentence = new ArrayList<Boolean>();
	
	private String symbols = "";
	
	private SerialReader reader;
	
	private static final String PREFIX = "LED";
		
	public MorseSerialApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		reader = new SerialReader();
		reader.init(this);
				
		updateAtFixedRate(10, this);
		
		loading = 100;
	}

	
	@Override
	public void draw(Graphic g) {

		int count = 0;
		int offsetX = 10;
		int size = 10;
		
		g.setColor(Color.BLACK);
		g.setBasicStroke(3);
		
		for(Boolean b: sentence) {
			if(b == MorseCode.DOT) {
				g.fillOval(offsetX+(size*2)*count+1, 40, size, size);
			} else {
				g.drawLine(offsetX+(size*2)*count, 40+(size/2), offsetX+(size*2)*count+size, 40+(size/2));
			}
			count++;
		}
		
		g.setFontSize(40);
		g.drawStringShadowX(300, symbols);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_SPACE)) {
			pressButton(event.getTimestamp());
		} else if(event.isKeyUp(KeyEvent.TSK_SPACE)) {			
			releaseButton(event.getTimestamp());
		}		
		
		return null;
	}

	private void releaseButton(long timestamp) {
		//Evaluate released
		evaluateReleased(timestamp);
		pressed = false;
	}

	private void pressButton(long timestamp) {
		lastPressedTimestamp = timestamp;
		pressed = true;
	};
	
	private void evaluateReleased(long currentTimestamp) {
		
		if(currentTimestamp-lastPressedTimestamp >= MORSE_DASH) {
			sentence.add(MorseCode.DASH);
			pressDash();
		} else {
			sentence.add(MorseCode.DOT);
			pressDot();
		}
		
	}
	
	protected void pressDot() {
		
	}
	
	protected void pressDash() {
		
	}
	
	@Override
	public void timeUpdate(long now) {
		
		long time = System.currentTimeMillis();
		
		if(!pressed && !sentence.isEmpty()) {
			if(time-lastPressedTimestamp > EVALUATE_TIME) {
				evaluateSentence(sentence);
				sentence.clear();
			}
		}
	}

	private void evaluateSentence(List<Boolean> sentence) {
		boolean[] array = new boolean[sentence.size()];
		for(int i=0;i<sentence.size();i++) {
			array[i] = sentence.get(i);
		}
		
		BooleanArray bArray = new BooleanArray(array);
		
		String symbol = code.getSymbol(bArray);
		
		if(symbol != null) {
			symbols += symbol;
			System.out.println("Found: "+symbol);
		} else {
			System.out.println("Symbol not Found.");
		}
		
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = reader.getInput().readLine();
				
				if(inputLine.startsWith(PREFIX)) {
					System.out.println(inputLine);
					
					if(inputLine.equals(PREFIX+" ON")) {
						pressButton(System.currentTimeMillis());
					} else if(inputLine.equals(PREFIX+" OFF")) {
						releaseButton(System.currentTimeMillis());
					} else {
						System.out.println("Invalid command");
					}
				}				
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	
}
