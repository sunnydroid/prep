package com.sunny.leetcode;


import com.sunny.common.Logger;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") return false
 * isMatch("aa","aa") return true
 * isMatch("aaa","aa") return false
 * isMatch("aa", "a*") return true
 * isMatch("aa", ".*") return true
 * isMatch("ab", ".*") return true
 * isMatch("aab", "c*a*b") return true
 */
public class PatternMatching {


    /**
     * Approach:
     *  handle the pattern matching in chunks
     *  cases:
     *      '.' - match any character
     *      '*' - matches any number of the previous characters
     *      'default' - exact match of characters
     *
     *  start matching from index = 0 to (string length - pattern length) rather than end of the string so as to be
     *  able to match the entire pattern in the last portion of the string.
     *  run a secondary index starting from index till end of string to progress index for current iteration check.
     *  note that '*' operator can match one or more.
     *  if there are more match characters after the '*' operator, the '*' match has to stop to allow matching of the
     *  remaining pattern
     *  if match fails at any of the patterns, break out of inner match loop and advance outer index to begin matching
     *  from the next character.
     *
     * Algorithm:
     *  if pattern length is longer than string length:
     *      return false
     *  index -> 0
     *  while index + pattern.length < string.length:
     *      run secondary index currentIndex = index
     *      boolean match = true
     *      for int i = 0; i < pattern.length
     *          character -> pattern.charAt(i)
     *          switch(character):
     *              case '.' :
     *                  match any character
     *                  i++
     *                  break;
     *              case '*' :
     *                  if current character not same as previous character:
     *                      match = false
     *                      index++
     *                      break;
     *                  while previous character is the same as current character and currentIndex < (pattern.length - i)
     *                      currentIndex++
     *                  i++
     *                  break;
     *              case default:
     *                  if character != string.charAt(currentIndex):
     *                      match = false
     *                      break;
     *          if match == false:
     *              break for loop
     *      if match:
     *          return true
     *
     *      increment index and being matching from the next index position index++
     * return false
     *
     * NOTE: Incomplete - need to fix
     *
     * @param input
     * @param pattern
     * @return
     */
    public static boolean patternMatch(String input, String pattern) {
        if (input == null || input.isEmpty() || pattern == null || pattern.isEmpty()) {
            return false;
        }
        /** if pattern length is greater than input length, return false */
        if (pattern.length() > input.length()) {
            return false;
        }

        /** if pattern doesn't contain special characters, do an exact string match */
        if (pattern.indexOf('.') == -1 && pattern.indexOf('*') == -1) {
            return input.equals(pattern);
        }

        /** index tracks start position for each iteration */
        int index = 0;
        /** run index so that the last iteration has at least the number of characters in the pattern to check for match*/
        while (index <= (input.length() - pattern.length())) {
            /** run secondary index for current iteration to test for match in the remainder of the string */
            int currentIndex = index;
            /** keep track of match against pattern.
             * if match fails at any point during the pattern, no need to check against remaining pattern, break
             * and advance start index to begin testing for match from new position.
             */
            boolean match = true;
            for(int i = 0; i < pattern.length(); i++) {
                char character = pattern.charAt(i);
                /** match based on current pattern */
                switch (character) {
                    case '.':
                        /** current character at currentIndex can be any character */
                        currentIndex++;
                        break;
                    case '*':
                        /** current character has to match previous character.
                         * continue matching with character at currentIndex until number of characters remaining
                         * is equals to the number of match characters remaining in the pattern string to test for
                         * match against them.
                         * handle special case where '.' followed by '*' which matches everything
                         */
                        if (input.charAt(currentIndex - 1) == '.') {
                            /** advance current index as long as match is valid and number of characters remaining
                             * in the input string is greater than the number of characters remaining to be matched
                             * in the pattern string
                             */
                            while (currentIndex < (input.length() - (pattern.length() - i))) {
                                currentIndex++;
                            }
                        } else if (input.charAt(currentIndex) != input.charAt(currentIndex - 1)) {
                            match = false;
                            break;
                        } else {
                            /** advance current index as long as match is valid and number of characters remaining
                             * in the input string is greater than the number of characters remaining to be matched
                             * in the pattern string
                             */
                            while (currentIndex < (input.length() - (pattern.length() - i)) && input.charAt(currentIndex) == input.charAt(currentIndex - 1)) {
                                currentIndex++;
                            }
                        }
                        break;
                    default:
                        if (input.charAt(currentIndex) != character) {
                            match = false;
                            break;
                        }
                        currentIndex++;
                }
                /** if any of the matches failed, break for loop and begin testing for match from the next index */
                if (!match) {
                    break;
                }
            }
            /** if all patterns matched, return true */
            if (match) {
                return true;
            }
            index++;
        }

        /** match not found */
        return false;
    }

    public static void main(String[] args) {
        String input = "ab";
        String pattern = ".*";

        Logger.log("pattern for " + pattern + " in " + input + " found? " + patternMatch(input, pattern));
    }
}
