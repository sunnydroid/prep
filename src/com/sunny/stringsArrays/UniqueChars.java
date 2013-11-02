package com.sunny.stringsArrays;

import java.util.HashMap;
import java.util.Map;


public class UniqueChars {

	/*
	 * Implement an algorithm to determine if a string has all unique characters
	 * Addon: implement solution without additional data structures
	 */
	public static boolean hasUniqueChars(String input) {
		/* Implemented with hashmap of size O(n), running time of O(n)*/
		boolean allUnique = true;
		Map<Character,Integer> charMap = new HashMap<Character,Integer>();
		
		for(Character c : input.toCharArray()) {
			if(charMap.containsKey(c)) {
				allUnique = false;
				break;
			} else {
				charMap.put(c, 1);
			}
		}
		return allUnique;
	}
	
	public static boolean hasUniqueCharsBruteForce(String input) {
		/* implemented without extra data structure */
		/* possible solution include brute force and doing a sort */
		boolean allUnique = true;
		
		for(int i = 0 ; i < input.length(); i++) {
			for(int j = i +1; j < input.length(); j++) {
				if(input.charAt(i) == input.charAt(j)) {
					allUnique = false;
					break;
				}
			}
		}
		return allUnique;
	}
	
	public static boolean hasUniqueCharsSortFist(String input) {
		boolean allUnique = true;
		
		return allUnique;
	}
}
