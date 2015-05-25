package com.malt.morse;

public class MorseCodeInternational extends MorseCode {

	public MorseCodeInternational() {
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
		
		//6 dots = white space
		codes.put(new BooleanArray(DOT, DOT, DOT, DOT, DOT, DOT), " ");
	}
	
}
