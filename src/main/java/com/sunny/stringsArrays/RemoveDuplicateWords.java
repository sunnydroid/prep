package com.sunny.stringsArrays;

/**
 * Given an input string, remove duplicate words immediately following each other.
 * Words could be separated by list of valid separators.
 * Preserve separators and special characters, only remove duplicate words
 * 
 * E.g. Input => Tony  \t is here\n here, going going here
 * Output => Tony  \t is here \n, going here.
 * @author sunnshah
 *
 */
public class RemoveDuplicateWords {
	private static String seperators = " ,.;!\n\t";
	
	public static String removeDupWords(String input) {
		StringBuilder sb = new StringBuilder();
		String currentWord = null;
		String nextWord = null;
		StringBuilder wordBuilder = new StringBuilder();
		boolean specialChar = false;
		
		for(int i = 0; i < input.length(); i++) {
			if(seperators.indexOf(input.charAt(i)) > -1) {
				specialChar = true;
			} else {
				/* normal character, append to word builder */
				wordBuilder.append(input.charAt(i));
			}
			
			if(specialChar || (i+1 == input.length()) ) {
				/* we have found a special character or at the end of the string */
				if(wordBuilder.length() > 0) {
					/* we've found a word */
					if(currentWord == null) {
						/* base case when first word is null */
						currentWord = wordBuilder.toString();
						sb.append(currentWord);
					} else {
						nextWord = wordBuilder.toString();
						if(!nextWord.equalsIgnoreCase(currentWord)) {
							/* next word and current word are not the same, append */
							sb.append(nextWord);
							currentWord = nextWord;
						} 
					}
					/* reset word builder by creating a new one 
					 * faster to create a new string builder than clear
					 * existing characters which is O(n) operation*/
					wordBuilder = new StringBuilder();
				}
				if(specialChar) {
					/* append special character to result string*/
					sb.append(input.charAt(i));
					specialChar = false;
				}
				
			} 
		}
		log("Input String => " + input);
		log("Output String => " + sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String testString = "a a a a a ; ; ; ; ; a ; b; b a; ";
		removeDupWords(testString);
		
	}
	
	private static void log(String str) {
		System.out.println(str);
	}

}
