package morse;

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

public class MorseKeyboardApplication extends Application implements UpdateIntervalListener {
	
	private long lastPressedTimestamp = 0;
	
	private boolean pressed = false;
	
	private static final long MORSE_DASH = 800; //1.2 seconds
	private static final long EVALUATE_TIME = 3000; //3 seconds
	
	private List<Boolean> sentence = new ArrayList<Boolean>();
	
	private MorseCode codes = new MorseCodeInternational();
		
	public MorseKeyboardApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		
				
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
	
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_SPACE)) {
			lastPressedTimestamp = event.getTimestamp();
			pressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_SPACE)) {			
			//Evaluate released
			evaluateReleased(event.getTimestamp());						
			pressed = false;
		}		
		
		return null;
	};
	
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

}
