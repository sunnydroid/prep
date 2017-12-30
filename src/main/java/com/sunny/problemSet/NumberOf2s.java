package com.sunny.problemSet;

/**
 * Write a method to count the number of 2's between 0 and n
 * Example:
 * input: 35
 * output: 14
 * list of 2s [2, 12, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32]
 */
public class NumberOf2s {

    /**
     * The number of 2s can be broken down to the following pattern
     * 0 -> 9 = 1
     * 10 -> 19 = 1
     * 20 -> 29 = 11
     * .
     * .
     * 90 -> 99 = 1
     *
     * Therefore number of 2s between
     * 0 - 99 = 11 + (9 * 1) = 20
     * Similarly number of 2s between
     * 0 - 999 = 100 + (10 * 20) = 300 i.e. 100 2s from the 200 range and there are 10 intervals of 0-99 that have 20 2s
     * For number of 2s between
     * 0 - 9999 = 1000 + (10 * 300) i.e 1000 2s from the 2000 range and there are 10 intervals of 0-999 that have 300 2s
     *
     * This rule can be generalized as:
     *  between 0 -> 10^n there are n * (10^n-1) 2s
     *  between 0 -> 10^1 there are 1 * (10^0) = 1
     *  between 0 -> 10^2 there are 2 * (10^1) = 20
     *  between 0 -> 10^3 there are 3 * (10^2) = 300
     *  formula = f(n) = n * (10 ^(n -1))
     *
     * If we want to find the number of 2s between the range 1 -> x * 10^n
     *  if x > 2 -> x * f(n) + 10^(n-1)
     *
     * Approach:
     *  For a given number, find the number of 2s for that upper range, for example if input = 35, upper range is 99
     *  which has 20 2's.
     *  For input of 350, upper range is 999, which has 300 2s.
     *  The input can be broken down into ranges:
     *  35 = 3 * (0-9) + 11 + 1 (since
     *  350 = 3 * (0-99) + 4 * (0-9)
     *
     * @param number
     * @return
     */
    public int numberOf2s(int number) {
        return 0;
    }
}
