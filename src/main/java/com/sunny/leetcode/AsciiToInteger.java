package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Implement atoi to convert a string to an integer.
 *
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character
 * is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as
 * many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored
 * and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence
 * exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range
 * of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */
public class AsciiToInteger {

    /**
     * Approach:
     *  ascii equivalent for 0 -> 9 are 48 -> 57
     *  skip white spaces until first character is found
     *  if first character is 0-9, -, + it is valid
     *  else, input is invalid, return 0
     *
     *  for each valid integer, multiply current accumulation by 10 and add integer but validate for
     *  over/underflow
     *
     * Algorithm:
     *  if input is null or empty, return 0
     *  index = 0
     *  convertedInteger = 0
     *  while index < input length AND char at index is whitespace:
     *      index++
     *  detect -ve, +ve sign if present and advance index
     *  while index < input length:
     *      if character at index not valid:
     *          return convertedInteger thus far
     *      else:
     *          test for over/under flow:
     *              return MAX/MIN value if over/underflow occurs
     *          multiply existing convertedInteger by 10 and add digit
     *
     *  return convertedInteger * -ve sign
     *
     * @param input
     * @return
     */
    public static int asciiToInteger(String input) {
        int convertedInteger = 0, index = 0, sign = 1;
        if (input == null || input.length() == 0) {
            return convertedInteger;
        }

        /** skip white spaces */
        while (index < input.length() && input.charAt(index) == ' ') {
            index++;
        }
        /** detect -ve, +ve sign */
        if (input.charAt(index) == '-' || input.charAt(index) == '+') {
            if (input.charAt(index) == '-') {
                sign = -1;
            }
            index++;
        }
        while (index < input.length()) {
            char currentChar = input.charAt(index);
            switch (currentChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    /** determine integer value */
                    int digit = currentChar - '0';
                    if (convertedInteger > ((Integer.MAX_VALUE - digit) / 10)) {
                        /** over/under flow occured */
                        return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                    convertedInteger = convertedInteger * 10 + digit;
                    break;
                default:
                    return 0;
            }
            index++;
        }

        return convertedInteger * sign;
    }

    public static void main(String[] args) {
        String testString = "        -2000ABC";
        int convertedInt = asciiToInteger(testString);

        Logger.log("Converted int = " + convertedInt);
    }
}
