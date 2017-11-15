package com.sunny.leetcode;

/**
 * Created by sunshah on 6/21/17.

 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.
 Example:

 Input: "cbbd"

 Output: "bb"
 */
public class LongestPalindromSubstring {

    public static void main(String[] args) {
        System.out.println(findLongestPalindrome("babad"));
    }

    /**
     * Approach:
     * Find a substring that is a palindrome and expand on that. Palindromes can consist of
     * the same character. Test with the character on the right (case abb), if not a palindrome, check with both
     * the characters on the left and right (case abcb - bc and cb are not a palindromes but bcb is). This is
     * especially important when a combined case occurs (case abbbba - ab, bbbba are not palindromes but abbbba is)
     * Algorithm:
     *  currentIndex = 0;
     *  leftIndex = currentIndex;
     *  rightIndex = currentIndex + 1;
     *  longestStart = 0;
     *  longestEnd = 0;
     *  For each character index as i:
     *      expand on right while characters in current pool are a palindrome and index is in bounds
     *      record longest start and end for each iteration
     *      while left index is greater than 0, right index is less than string length and characters
     *          in current pool form a palindrome, expand both left and right.
     *          update records for longest start and end.
     *
     * Use helper function to determine if characters within current indices are a palindrome
     * @param string
     * @return
     */
    public static String findLongestPalindrome(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }

        /** special case, check if entire string is palindrome */
        if (isPalindrome(string, 0, string.length() - 1)) {
            return string;
        }

        int currentIndex = 0, longestStart = 0, longestEnd = 0;
        int leftIndex, rightIndex;

        while(currentIndex < string.length()) {
            leftIndex = currentIndex;
            rightIndex = currentIndex + 1;
            /**
             * case: expand to right as long as palindrome is valid
             * e.g: abbbbb
             */
            if(rightIndex < string.length() && isPalindrome(string, leftIndex, rightIndex)) {
                while (rightIndex < string.length() && string.charAt(rightIndex) == string.charAt(leftIndex)) {
                    if (rightIndex - leftIndex > longestEnd - longestStart) {
                        longestEnd = rightIndex;
                        longestStart = leftIndex;
                    }
                    rightIndex++;
                }
            }


            /**
             * case: expand on both right and left as long as palindrome is valid and
             * left/right indices are in bounds. Right index has already been expanded, expand just the
             * left side for isPalindrome test
             * e.g: abbbbba, madam
             */

            while (leftIndex > 0 && rightIndex < string.length())  {
                /* expand to the left */
                leftIndex = leftIndex - 1;
                if(string.charAt(leftIndex) == string.charAt(rightIndex)) {
                    /* update longest start/end */
                    if (rightIndex - leftIndex > longestEnd - longestStart) {
                        longestStart = leftIndex;
                        longestEnd = rightIndex;
                    }
                    /* expand to right after test*/
                    rightIndex++;
                } else {
                    /* reset right index to next position after current index and continue algorithm */
                    rightIndex = currentIndex + 1;
                    break;
                }
            }

            /* update currentIndex to the last index that was tested for palindrome, i.e. rightIndex */
            currentIndex = rightIndex;
        }

        /** substring returns till the end - 1 characters, hence the need for + 1 */
        return string.substring(longestStart, longestEnd+1);
    }

    public static boolean isPalindrome(String string, int startIndex, int endIndex) {
        /* single letters are not considered palindromes */
        if (startIndex >= endIndex) {
            return false;
        }
        while (startIndex < endIndex) {
            if(string.charAt(startIndex) != string.charAt(endIndex)) {
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }

}
