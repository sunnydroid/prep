package com.sunny.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {
	
	public static void main(String[] args) {
		int len = lengthOfLongestSubstring("aab");
		System.out.println(len);
		len = lengthOfLongestSubstring("dvdf");
		System.out.println(len);
	}
	
public static int lengthOfLongestSubstring(String s) {
		int curIndex = 0, startIndex = 0, endIndex = 0;
		int longestStartIndex = 0, longestEndIndex = 0;

		while (startIndex < s.length()) {
			
			/* reset indices */
			curIndex = startIndex;
			endIndex = startIndex;

			/* reset seen characters for each segment */
			Set<Character> seenChars = new HashSet<>();

			/*
			 * do while current char hasn't been seen and length within bounds
			 */
			while (curIndex < s.length() && !seenChars.contains(s.charAt(curIndex))) {
				seenChars.add(s.charAt(curIndex));
				curIndex++;
				endIndex = curIndex;
			}

			/* check if current start and stop beat longest */
			if ((endIndex - startIndex) > (longestEndIndex - longestStartIndex)) {
				longestStartIndex = startIndex;
				longestEndIndex = endIndex;
			}
			startIndex++;
		}

		return (longestEndIndex - longestStartIndex);
	}

}
