package com.sunny.programmingChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an input of timestamps(integers) determine which timestamps to reject depending on window size and
 * number of events
 * <p>
 * For instance [101,103,106,112,117,118], window size = 10 and limit = 2 means there should be only 2 items in a 10
 * second period
 * <p>
 * 101 and 103 are fine
 * 101, 103 with 106 should be rejected as count exceeds 2 within the time frame
 * 103, 106, 112 should be rejected
 * 112, 117 and 118 should be reject
 */
public class RateLimit {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(new Integer[]{101, 103, 106, 112, 117, 118});
        List<Integer> output = simulateRateLimit(input, 10, 2);
        // reject? 106, 112, 118
        for (int ts : output) {
            System.out.println(ts);
        }
    }


    //O(n^2) - runtime
    //O(1) - space
    public static List<Integer> simulateRateLimit(List<Integer> inviteTimestamps, int window, int limit) {

        // input validation
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < inviteTimestamps.size(); i++) {
            int currentCount = 1;
            int j = i+1;
            for (; j < inviteTimestamps.size(); j++) {
                if (inviteTimestamps.get(j) - inviteTimestamps.get(i) < window) {
                    currentCount++;
                    if (currentCount > limit) {
                        break;
                        // j = 2
                    }
                } else {
                    break;
                }
            }

            if (currentCount > limit) {
                output.add(inviteTimestamps.get(j));
            }
        }
        return output;
    }

}
