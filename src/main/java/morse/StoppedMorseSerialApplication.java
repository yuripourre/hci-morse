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
import com.malt.morse.MorseCodeInternational;
import com.malt.serial.SerialReader;

public class StoppedMorseSerialApplication extends Application implements UpdateIntervalListener, SerialPortEventListener {
	
	private MorseCode codes = new MorseCodeInternational();
	
	private long lastPressedTimestamp = 0;
	private long validTimer = 300;
	private boolean valid = false;
	private boolean pressed = false;
	
	private static final long MORSE_DASH = 800; //1.2 seconds
	private static final long EVALUATE_TIME = 3000; //3 seconds
	
	private List<Boolean> sentence = new ArrayList<Boolean>();
	
	private SerialReader reader;
	
	private static final String PREFIX = "LED";
	
	private long lastReceivedTimestamp = 0;
		
	public StoppedMorseSerialApplication(int w, int h) {
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
		
		g.setColor(Color.BLACK);
		
		for(Boolean b: sentence) {
			if(b == MorseCode.DOT) {
				g.fillOval(offsetX+5*count+1, 40, 3, 3);
			} else {
				g.drawLine(offsetX+5*count, 40+2, offsetX+5*count+4, 40+2);
			}
			count++;
		}
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
	}
	
	private void evaluateReleased(long currentTimestamp) {
		
		if(currentTimestamp-lastPressedTimestamp >= MORSE_DASH) {
			sentence.add(MorseCode.DASH);
		} else {
			sentence.add(MorseCode.DOT);
		}
		
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
		
		String symbol = codes.getSymbol(bArray);
		
		if(symbol != null) {
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
						if(!valid) {
							lastReceivedTimestamp = System.currentTimeMillis();
						}
					} else if(inputLine.equals(PREFIX+" OFF")) {
						if(valid) {
							releaseButton(System.currentTimeMillis());
							valid = false;
						}
					} else {
						System.out.println("Invalid command");
					}
				}				
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		
		long now = System.currentTimeMillis();
		
		if(now > lastReceivedTimestamp + validTimer) {
			valid = true;
			pressButton(lastReceivedTimestamp);
		}

	}
	
}
