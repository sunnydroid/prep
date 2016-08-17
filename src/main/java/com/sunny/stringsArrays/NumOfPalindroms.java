package com.sunny.stringsArrays;

import com.sunny.installationDependencies.Logger;

public class NumOfPalindroms {
	
	public static void palindromeRecurse(char[] input, int start, int end) {
		if(start < end && start < input.length && end < input.length) {
			StringBuilder sb = new StringBuilder();
			for(int i = start; i != end; i++) {
				sb.append(input[i]);
			}
			if(isPalindrome(sb.toString())) {
				Logger.log("Palindrome found: " + sb.toString());
			}
			/* recurse with end incremented */
			palindromeRecurse(input, start, end+1);
			/* recurse with start and end incremented */
			palindromeRecurse(input, start+1, end+1);
			
		} else {
			return;
		}
	}
	
	public static boolean isPalindrome(String string) {
		
		if(string.length() < 2) {
			return false;
		}
		
		int i = 0;
		int j = string.length() - 1;
		
		for(; i < j; i++, j-- ) {
			if(!(string.charAt(i) == string.charAt(j))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String test = "abaccaba";
		palindromeRecurse(test.toCharArray(), 0, 1);
	}

}
