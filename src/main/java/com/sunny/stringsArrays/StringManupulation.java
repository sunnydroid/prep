package com.sunny.stringsArrays;

import com.sunny.common.Logger;

public class StringManupulation {

	
	public static void main(String[] args) {
		String testString = "This is a sentence";
//		reversWordsInSentense(testString.toCharArray());
		reverseWordsInSentenceLessSpace(testString.toCharArray());
	}
	
	public static void reverseWordsInSentenceLessSpace(char[] input) {
		int wordStartIndex = 0;
		int wordEndIndex = 0;
		
		Logger.log("Input string : " + new String(input));
		
		for(int i = 0; i < input.length; ) {
			while( i < input.length && input[i] != ' ') {
				i++;
			}
			/* i points to empty space */
			wordEndIndex = i-1;
			reverseString(input, wordStartIndex, wordEndIndex);
			/* next word starts after the space*/
			i++;
			wordStartIndex = i;
		}
		Logger.log("Final string : " + new String(input));
	}
	
	public static void reverseString(char[] input, int start, int end) {
		int i, j;
		for(i = start, j = end; i <= j; i++, j--) {
			char tmp = input[j];
			input[j] = input[i];
			input[i] = tmp;
		}
	}
	
	/*
	 * Reverses words in a sentence
	 * This is sample => sitT si elpmas
	 */
	public static void reversWordsInSentense(char[] sentence) {
		// TODO: check for valid input
		// TODO: optimize
		
		if(sentence == null) {
			return;
		}
		
		int previousWordBoundry = 0;
		int curWordCharIndex = 0;
		char[] output = new char[sentence.length];
		
		for(int i = 0; i < sentence.length; ) {
			while(i < sentence.length && sentence[i] != ' ') {
				i++;
			}
			/* found word, i current at index of space */
			curWordCharIndex = i-1;
			/* copy current word to new array */
			while(previousWordBoundry < i) {
				output[previousWordBoundry] = sentence[curWordCharIndex];
				previousWordBoundry++;
				curWordCharIndex--;
			}
			
			if( i < sentence.length) {
				output[previousWordBoundry] = sentence[i];
				previousWordBoundry++;
				i++;
			}
		}
		Logger.log("Input: " + new String(sentence));
		Logger.log("Outpu: " + new String(output));
	}
}
