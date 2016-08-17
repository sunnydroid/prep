package com.sunny.stringsArrays;

import com.sunny.common.Logger;

public class WarmUpStringArrayProb {
	
	public static void main(String[] args) {
		String input1 = "123456789";
		String input2 = "abc";
		String input3 = "This iss a stringg witth duplicatteess";
//		mergeArrays(input1.toCharArray(), input2.toCharArray());
		removeDupChars(input3.toCharArray());
	}
	
	/*
	 * Remove duplicate charaters in a string
	 */
	public static void removeDupChars(char[] input) {
		Logger.log("Input : " + new String(input));
		int j = 0;
		for(int i = 0; i< input.length - 1; i++) {
			if(input[i] != input[i+1]) {
				input[j] = input[i];
				j++;
			}
		}
		
		while(j < input.length) {
			input[j] = ' ';
			j++;
		}
		
		Logger.log("Duplicate Removed : " + new String(input));
	}
	
	/*
	 * suppose we have arrays a1, a2... an and b1, b2...bn, implement a function to change that to 
	 * a1,b1,a2,b2..an,bn
	 */
	
	public static char[] mergeArrays(char[] a, char[] b) {
		char[] combined = new char[a.length + b.length];
		int k = 0;
		int i = 0;
		int j = 0;
		
		for(; i < a.length && j < b.length; i++, j++ ) {
			combined[k] = a[i];
			combined[k+1] = b[j];
			k +=2;
		}
		
		while(i < a.length) {
			combined[k] = a[i];
			i++;
			k++;
		}
		
		while(j < b.length) {
			combined[k] = b[j];
			j++;
			k++;
		}
		
		Logger.log("input 1 : " + new String(a) + ", input 2 : " + new String(b));
		Logger.log("combined output: " + new String(combined));
		
		return combined;
	}

}
