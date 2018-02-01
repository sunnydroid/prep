package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 */
public class BacketCombinations {

    public static List<String> findBracketCombinations(int count) {
        List<String> stringCombinations = new ArrayList<>();
        recurseBracketCombinations(stringCombinations, "", 0, 0, count);
        return stringCombinations;
    }

    /**
     * Approach, use recursion with backtracking to combine open and close brackets.
     * @param bracketList
     * @param stringTillNow
     * @param openCount
     * @param closeCount
     * @param max
     */
    public static void recurseBracketCombinations(List<String> bracketList, String stringTillNow, int openCount, int closeCount, int max) {
        /** base case for recursion is when length of string equals 2 x max, i.e. all open brackets are matched */
        if (stringTillNow.length() == max * 2) {
            bracketList.add(stringTillNow);
            return;
        }

        if (openCount < max) {
            recurseBracketCombinations(bracketList, stringTillNow + "(", openCount + 1, closeCount, max);
        }
        /** close count always has to be less than open count to ensure that close bracket is added only after an open bracket in the
         * next recursion
         */
        if (closeCount < openCount) {
            recurseBracketCombinations(bracketList, stringTillNow + ")", openCount, closeCount + 1, max);
        }
    }

    public static void main(String[] args) {
        Logger.log(findBracketCombinations(4).toString());
    }
}
