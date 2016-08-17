package com.sunny.ctci.chpt1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunshah on 12/24/15.
 */
public class StringUniqueChars {

    public static void main(String[] args) {
        String test = "abcdefghij";
        String test2 = "abcdefghiij";
        String test3 = "012345";
        String test4 = "0123450";

        uniqueCharsUsingBits(test);
        uniqueCharsUsingBits(test2);
        uniqueCharsUsingBits(test3);
        uniqueCharsUsingBits(test4);
    }

    /* Use a set to keep track of characters already added */
    public static boolean uniqueChars(String subject) {
        Set<Character> set = new HashSet<>();

        for(Character c : subject.toCharArray()) {
            if(!set.add(c)) {
                System.out.println(subject + " contains duplicate: " + c);
                return false;
            }
        }
        System.out.println("no duplicates found in " + subject);
        return true;
    }

    /* Use bit operators to keep track of characters already set
     * convert each character to ascii, left shift by 'a' - ascii char.
     * Keep history of previous characters by ORing shifted values. If AND
     * operation results in 0, duplicate found
     */
    public static boolean uniqueCharsUsingBits(String subject) {
        int history = 0;
        for(int i = 0; i < subject.length(); i++) {
            char c = subject.charAt(i);
            int asciiVal = c - 'a';
            System.out.println("ascii val: " + asciiVal);
            int shifted = 1 << asciiVal;
            System.out.println("shifted val: " + shifted);
            if((history & shifted) > 0) {
                System.out.println(subject + " contains duplicate: " + c);
                return true;
            }
            history = history | shifted;
        }
        System.out.println("No duplicates found in " + subject);
        return false;
    }
}
