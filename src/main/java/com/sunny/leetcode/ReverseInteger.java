package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.math.BigInteger;

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return
 * Example2: x = -123, return -321
 *
 * The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    /**
     * Approach:
     *  determine highest order of 10 present in the number:
     *      123 has 100, 1234 has 1000
     *      this will be needed to build number in the reverse order 321 or 4321
     *  determining numbers to be added and order to be added:
     *      123 will be reversed to for 321 which can be broken downs as
     *      300+20+1. The modulus operator returns the remainder term from division
     *      which is the least significant digit. The combination of modulo and division
     *      by 10 will return digits in revers order. This works for negative numbers as well:
     *          123 % 10 = 3, 123 / 10 = 12;
     *          12 % 10 = 2, 12 / 10 = 1;
     *          1 % 10 = 1, 1 / 10 = 0
     *  combine the 2 steps to:
     *      digits returned by the modulo then divide operation will by order of 10.
     *      123 -> 3 * 100 + 2 * 10 + 1 * 1 = 321
     *      1234 -> 4 * 1000 + 3 * 100 + 2 * 10 + 1 * 1 = 4321
     *  test for overflow:
     *      overflow can occur at either the multiplication or addition stage.
     *      test at each stage using the Java 8 library Math.multiplyExact and Math.addExact
     *      which throw ArithmeticException if overflow is detected. This is the preferred method
     *      to detect overflow as libraries are better tuned and tested than manually testing it.
     *      To manually test:
     *          BigInteger c = BigInteger.valueOf(a) + BigInteger.valueOf(b);
     *          if(c.compareTo(BigInteger.valueOf(Long.MAX_VALUE) > 0) // overflow
     *
     *          similarly for negative values, use Long.MIN_VALUE.
     *
     * Algorithm:
     *  if input is less than 10 AND greater than -10:
     *      return input
     *  MSB = 1, number = input
     *  while number > 0:
     *      MSB = MSB * 10;
     *      number = number / 10;
     *
     *  newNumber = 0
     *  while input > 0:
     *      digit = number % 10
     *      // check overflow exception for code
     *      newNumber = newNumber + digit * MSB
     *      MSB = MSB / 10
     *      number = number / 10
     *
     *  if exception occurs, return 0
     *  return newNumber
     *
     * @param input
     * @return
     */
    public static int reverseInt(int input) {

        int newNumber = 0;
        while (input != 0) {
            /** manual overflow check, skipped in favour of Math library */
            if((newNumber > 0 && newNumber > (Integer.MAX_VALUE - (input % 10)) / 10) || (newNumber < 0 && newNumber < (Integer.MIN_VALUE - (input % 10)) / 10)) {
                return 0;
            }
            newNumber = (newNumber * 10) + (input % 10);
            input = input / 10;
            /** detecting over/underflow using Math library - slower */
//            try {
//                newNumber = Math.addExact(Math.multiplyExact(newNumber, 10), (input % 10));
//                input = input / 10;
//            } catch (ArithmeticException ae) {
//                Logger.log("overflow/underflow occurred, returning 0");
//                return 0;
//            }
        }

        return newNumber;
    }

    public static void main(String[] args) {
//        int number = -2147483648;
//        int number = 1534236469;
        int number = 1463847412;
//        int number = -123;
        int reverse = reverseInt(number);
        Logger.log("reverse integer = " + reverse);
    }
}
