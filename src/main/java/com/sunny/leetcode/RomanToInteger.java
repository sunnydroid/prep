package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {

    /**
     * Approach:
     *  Roman numerals are built around by summing or subtracting values depending on their ordering. Going from right
     *  to left:
     *      if lower numeral is followed by higher or equal numeral, perform addition
     *          e.g. VI, XVI -> from right to left, each numeral is smaller than the next so we add value of each
     *              add 1 + 5
     *              add 1 + 5 + 10
     *      else perform subtraction
     *          e.g IXX -> from right to left, add 10, 10 and subtract 1
     *
     * Algorithm:
     *  current sum <- 0
     *  previous character value <- 0
     *  map <- map from roman characters to integer values
     *
     *  for each character:
     *      current character value <- get from map value of current character
     *      if current character value is greater than previous character value
     *          sum <- sum + current character value
     *      else
     *          sum <- sum - current character value
     *
     *      current character value <- current character value
     *
     *   return sum
     *
     * @param input
     * @return
     */
    public static int romanToInt(String input) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);


        int sum = 0;
        int previousCharValue = 0;

        for(int i = input.length()-1; i > -1; i--) {
            int currentCharValue = charMap.get(input.charAt(i));
            if(currentCharValue >= previousCharValue) {
                sum = sum + currentCharValue;
            } else {
                sum = sum - currentCharValue;
            }

            previousCharValue = currentCharValue;
        }

        return sum;
    }

    public static void main(String[] args) {
        String input = "XLIX";
        int value = romanToInt(input);

        Logger.log("Roman numeral " + input + " equals " + value);
    }
}
