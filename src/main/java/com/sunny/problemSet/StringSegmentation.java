package com.sunny.problemSet;

import java.util.Arrays;

/**
 * Given a string input and a dictionary of words dict, determine if input can be segmented into a space-separated sequence
 * of one or more dictionary words.
 */
public class StringSegmentation {

    public static void main(String[] args) {
        String dict[] = {
                "is", "a", "view", "this", "inter", "sy", "ape", "intermediate",
                "interview", "fors", "for", "an", "ich", "which", "amazing",
                "any", "com", "comp", "company", "tow", "kfor", "work", "for", "or",
        };
        String s = "thisisaninterviewforcompanywhichisanamazingcompanytoworkfor";

        String s2 = "company";
        String dict2[] = {"com", "compa", "any", "y"};
        String dict3[] = {"com", "comp", "any", "y"};

        System.out.println(s + " can be reduced to ? " + Arrays.toString(dict)  + " " + wordBreak(s, dict));
        System.out.println(s2 + " can be reduced to ? " + Arrays.toString(dict2)  + " " + wordBreak(s2, dict2));
        System.out.println(s2 + " can be reduced to ? " + Arrays.toString(dict2)  + " " + wordBreak(s2, dict3));
    }

    /**
     * Approach:
     *  There could be multiple paths from the beginning to the end of the given string. All paths need to be traversed
     *  E.g. input = companyisawesome and dict = {"comp", "company", "is", "awes", "some", "awesome"}
     *      valid path = company -> is -> awesome
     *      failed path = comp -|
     *      failed path = company -> is -> awes -|
     *  DFS with recursion and backtracking if match is not found in sub path is the best approach
     *  Recursive functions need to have the following 3 conditions:
     *      1) Base case which breaks from the recursion
     *      2) Each recursive function should break the problem further
     *      3) Results from sub calls are floated up to make a decision
     *
     * Algorithm:
     *  if input string or dictionary are null:
     *      return false
     *  if input string or dictionary are empty:
     *      return false
     *  for int i = 0; i < input.length; i++:
     *      if substring(0, i) is present in dictionary:
     *          if we have reached end of input:
     *              return true
     *          if recursive call with remaining substring all return true:
     *              return true
     *
     *      continue checking other paths
     *
     *  return false, no valid paths found for this string input
     *
     * @param input input string to segment and test
     * @param dict string array to check
     * @return
     */
    public static boolean wordBreak(String input, String[] dict) {
        /** error checking and default cases for null input, dict empty, s empty */
        if(input == null || input.isEmpty()) {
            return false;
        }

        if(dict == null || dict.length < 1) {
            return false;
        }

        /** Find all substring combinations that are valid dictionary words and recurse with remaining substring.
         *  Backtrack if sub paths fail.
         */
        for(int i = 0; i != input.length(); i++) {
            if(contains(dict, input.substring(0, i+1))) {
                /** We've reached the end and found the last match */
                if(i == input.length() - 1) {
                    return true;
                }
                /** Check with remaining substring, if all sub paths return true, we've found a valid segmentation
                 * path */
                if (wordBreak(input.substring(i + 1, input.length()), dict)) {
                    return true;
                }
                /** else backtrack and continue checking other paths */
            }
        }
        /** We've reached end of the current input but no match found in the dictionary */
        return false;
    }

    /**
     * Utility function to check if String s is present in array dict
     * @param dict Array of strings to match against
     * @param input String to match
     * @return true if input is present, false otherwise
     */
    public static boolean contains(String [] dict, String input) {
        for (int i = 0; i < dict.length; i++) {
            if (input.equals(dict[i])) {
                return true;
            }
        }
        return false;
    }
}
