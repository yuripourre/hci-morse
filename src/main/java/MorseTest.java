import java.util.HashMap;
import java.util.Map;

import com.malt.morse.BooleanArray;


public class MorseTest {

	private static final boolean LONG = true;
	private static final boolean SHORT = false;
	
	public static void main(String[] args) {
		
		//Short is 1 unit
		//Long is 3 units
		
		Map<BooleanArray, String> codes = new HashMap<BooleanArray, String>();
		
		codes.put(new BooleanArray(SHORT, LONG), "A");
		codes.put(new BooleanArray(LONG, SHORT, SHORT, SHORT), "B");
		codes.put(new BooleanArray(LONG, SHORT, LONG, SHORT), "C");
		codes.put(new BooleanArray(LONG, SHORT, SHORT), "D");
		
		String symbol = codes.get(new BooleanArray(SHORT, LONG));
				
		System.out.println("Found: "+symbol);
	}

}
