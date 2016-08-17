package com.sunny.stringsArrays;

import com.sunny.common.Logger;

public class LongestPalindrome {
	
	/*
	 * Expanding around the center for even and odd lengths
	 * O(N^2) time
	 * Store previously computed palindromes 
	 */
	public static String longestPalindromeDP(String input) {
		String longest = "";
		
		for(int i = 0; i < input.length(); i++) {
			/* for odd length strings */
			String palindrome = expandAroundCenter(input, i, i);
			
			if(palindrome.length() > longest.length()) {
				longest = palindrome;
			}
			
			/* for even length strings */
			palindrome = expandAroundCenter(input, i, i+1);
			
			if(palindrome.length() > longest.length()) {
				longest = palindrome;
			}
		}
		
		Logger.log("Input string : " + input);
		Logger.log("Longest palindrome = " + longest);
		return longest;
	}
	
	public static String expandAroundCenter(String input, int c1, int c2) {
		/* expand around the two centers until a mismatch is found */
		while(c1 >= 0 && c2 < input.length() && input.charAt(c1) == input.charAt(c2)) {
			c1--;
			c2++;
		}
		
		return input.substring(c1+1, c2);
	}
	
	/*
	 * Manacher's algorithm
	 * O(n) time
	 * 
	 */
	
	public static void main(String[] args) {
		String test = "abbacdedcabbg";
		longestPalindromeDP(test);
	}

}
