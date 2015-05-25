package com.malt.morse;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {

	public static final boolean DASH = true;
	public static final boolean DOT = false;
	
	protected Map<BooleanArray, String> codes = new HashMap<BooleanArray, String>();
	
	public String getSymbol(BooleanArray code) {
		return codes.get(code);
	}
	
}
