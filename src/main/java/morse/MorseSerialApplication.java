package morse;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.malt.serial.SerialReader;

import br.com.etyllica.context.Application;
import br.com.etyllica.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;

public class MorseSerialApplication extends Application implements UpdateIntervalListener, SerialPortEventListener {

	private static final boolean DASH = true;
	private static final boolean DOT = false;
	
	private Map<BooleanArray, String> codes = new HashMap<BooleanArray, String>();
	
	private long lastPressedTimestamp = 0;
	
	private boolean pressed = false;
	
	private static final long MORSE_DASH = 800; //1.2 seconds
	private static final long EVALUATE_TIME = 3000; //3 seconds
	
	private List<Boolean> sentence = new ArrayList<Boolean>();
	
	private SerialReader reader;
	
	private static final String PREFIX = "LED";
		
	public MorseSerialApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		reader = new SerialReader();
		reader.init(this);
		
		initInternationalCodes();
				
		updateAtFixedRate(10, this);
		
		loading = 100;
	}

	private void initInternationalCodes() {
		codes.put(new BooleanArray(DOT, DASH), "A");
		codes.put(new BooleanArray(DASH, DOT, DOT, DOT), "B");
		codes.put(new BooleanArray(DASH, DOT, DASH, DOT), "C");
		codes.put(new BooleanArray(DASH, DOT, DOT), "D");
		codes.put(new BooleanArray(DOT), "E");
		codes.put(new BooleanArray(DOT, DOT, DASH, DOT), "F");
		codes.put(new BooleanArray(DASH, DASH, DOT), "G");
		codes.put(new BooleanArray(DOT, DOT, DOT, DOT), "H");
		codes.put(new BooleanArray(DOT, DOT), "I");
		codes.put(new BooleanArray(DOT, DASH, DASH, DASH), "J");
		codes.put(new BooleanArray(DASH, DOT, DASH), "K");
		codes.put(new BooleanArray(DOT, DASH, DOT, DOT), "L");
		codes.put(new BooleanArray(DASH, DASH), "M");
		codes.put(new BooleanArray(DASH, DOT), "N");
		codes.put(new BooleanArray(DASH, DASH, DASH), "O");
		codes.put(new BooleanArray(DOT, DASH, DASH, DASH, DOT), "P");
		codes.put(new BooleanArray(DASH, DASH, DOT, DASH), "Q");
		codes.put(new BooleanArray(DOT, DASH, DOT), "R");
		codes.put(new BooleanArray(DOT, DOT, DOT), "S");
		codes.put(new BooleanArray(DASH), "T");
		codes.put(new BooleanArray(DOT, DOT, DASH), "U");
		codes.put(new BooleanArray(DOT, DOT, DOT, DASH), "V");
		codes.put(new BooleanArray(DOT, DASH, DASH), "W");
		codes.put(new BooleanArray(DASH, DOT, DOT, DASH), "X");
		codes.put(new BooleanArray(DASH, DOT, DASH, DASH), "Y");
		codes.put(new BooleanArray(DASH, DASH, DOT, DOT), "Z");
		
		codes.put(new BooleanArray(DOT, DASH, DASH, DASH, DASH), "1");
		codes.put(new BooleanArray(DOT, DOT, DASH, DASH, DASH), "2");
		codes.put(new BooleanArray(DOT, DOT, DOT, DASH, DASH), "3");
		codes.put(new BooleanArray(DOT, DOT, DOT, DOT, DASH), "4");
		codes.put(new BooleanArray(DOT, DOT, DOT, DOT, DOT), "5");
		codes.put(new BooleanArray(DASH, DOT, DOT, DOT, DOT), "6");
		codes.put(new BooleanArray(DASH, DASH, DOT, DOT, DOT), "7");
		codes.put(new BooleanArray(DASH, DASH, DASH, DOT, DOT), "8");
		codes.put(new BooleanArray(DASH, DASH, DASH, DASH, DOT), "9");
		codes.put(new BooleanArray(DASH, DASH, DASH, DASH, DASH), "0");
	}
	
	@Override
	public void draw(Graphic g) {
		
		int count = 0;
		int offsetX = 10;
		
		g.setColor(Color.BLACK);
		
		for(Boolean b: sentence) {
			if(b == DOT) {
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
	};
	
	
	
	private void evaluateReleased(long currentTimestamp) {
		
		if(currentTimestamp-lastPressedTimestamp >= MORSE_DASH) {
			sentence.add(DASH);
		} else {
			sentence.add(DOT);
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
		
		String symbol = codes.get(bArray);
		
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
