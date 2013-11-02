package com.sunny.misc;

import java.util.HashMap;

import com.sunny.common.Logger;

/**
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that
 * string can generate. Give a count as well as print the strings.
 * 
 * For example: Input: "1123". You need to general all valid alphabet codes from
 * this string.
 * 
 * Output List: 
 * aabc //a = 1, a = 1, b = 2, c = 3 
 * kbc // since k is 11, b = 2, c=3 
 * alc // a = 1, l = 12, c = 3 
 * aaw // a= 1, a =1, w= 23 
 * kw // k = 11, w = 23
 * 
 * @author sunnshah
 * 
 */

public class StringCodes {
	
	public static HashMap<Integer, Character> map = new HashMap<Integer, Character>();
	
	
	public static void main(String[] args) {
		String testInput = "1123";
		buildMap();
//		printMap();
		decode(testInput.toCharArray(), "", 0);
	}
	
	/*
	 * algorithm: use a backtracking method.
	 * since alphabets map from 1 -> 26, for each iteration check if char at start and start + 1 
	 * are equal to or less than 26. Then append the corresponding alphabet to the prefix and recurse
	 * 1) is start == input. length ? print prefix : recurse
	 * 2) check chars at start and start + 1, append corresponding codes to prefix, increment start and 
	 * recurse 
	 */
	public static void decode(char[] input, String prefix, int start) {
		/* base case, reached till end of string , print it */
		if(start >= input.length) {
			Logger.log(prefix);
			return;
		}
		
		if(start + 1 < input.length) {
			StringBuilder sb = new StringBuilder();
			sb.append(input[start]);
			sb.append(input[start + 1 ]);
			Integer integer = Integer.parseInt(sb.toString());
			/* check if integer is less than or equal to 26, if so, append code to prefix and recurse */
			if(integer < 27) {
				String newPrefix = prefix;
				newPrefix += map.get(integer);
				decode(input, newPrefix, start+2);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(input[start]);
		Integer integer = Integer.parseInt(sb.toString());
		prefix += map.get(integer);
		decode(input, prefix, start+1);
		
	}
	
	private static void buildMap() {
		char a = 'a';
		for(int i = 1; i < 27; i++) {
			map.put(i, a);
			a++;
		}
	}
	
	private static void printMap() {
		for(Integer key : map.keySet()) {
			Logger.log(key + " => " + map.get(key));
		}
	}

}
