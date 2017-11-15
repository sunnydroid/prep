package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromeNumber {

    /**
     * Approach:
     *  the input integer cannot be converted to a string followed by usual string palindrome determination due to
     *  the restriction of extra space.
     *
     *  key property of palindrome number is, it is the exact value if constructed from reverse
     *      5555 -> 5555 if new integer is constructed by taking digits from the end
     *  even better property is that reversed number and original number will be the same at the half
     *  way point as digits are removed from the original number to the reversed number.
     *      for odd length number
     *          10301 -> 1030, 1 -> 103, 10 -> 10, 3, 10
     *      for even length number
     *          1221 -> 122, 1 -> 12, 12
     *
     *  negative numbers are considered to be non palindrome.
     *
     * Algorithm:
     *  if input is less than 10
     *      return true
     *  newInteger = 0
     *  oldInteger = input
     *  while oldInteger is greater than newInteger
     *      digit = oldInteger % 10
     *      oldInteger = oldInteger / 10
     *      // for the odd length case, check for equality before adding digit to new number
     *      if oldInteger equals newInteger
     *          return true
     *      newInteger > (MAX_INTEGER - digit)/10
     *          overflow has occurred, return false
     *      newInteger = newInteger * 10 + digit
     *      // for the even length case, check for equality after adding digit to new number
     *
     *  return false at this point
     *
     * @param input
     * @return
     */
    public static boolean isPalindrome(int input) {
        if (input < 0) {
            return false;
        }
        if (input < 10) {
            return true;
        }
        int newInteger = 0, integer = input;
        while (integer > newInteger) {
            int digit = integer % 10;
            integer = integer / 10;
            // case1: odd length number, check for equality after removing the middle digit
            if (integer == newInteger) {
                return true;
            }
            if (newInteger > (Integer.MAX_VALUE - digit) / 10) {
                /** overflow occurred, not a palindrome */
                return false;
            }
            newInteger = newInteger * 10 + digit;
            // case2: even length case, check for equality after center point
            if (integer == newInteger) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int testInteger = -2147447412;
//        int testInteger = 10001;
        int testInteger = 2147447412;
        Logger.log("Integer " + testInteger + " is palindrome? " + isPalindrome(testInteger));
    }
}
