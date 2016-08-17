package com.sunny.greedy;

/**
 * Created by sunshah on 7/25/16.
 */
public class MaxActivitySelection {

    public static void main(String[] args) {

    }

    /**
     * returns maximum number of activities that can be done given range of
     * start and end times
     * 0 - 3, 2 - 5, 4 - 8, 7 - 14, 9 - 12, 13 - 14
     * should result in 0 - 3, 4 - 8, 9 - 12 and 13 - 14 being selected
     * Algorithm: Use greedy algorithm to maximize number of activities
     *  sort start/end times by end times  in ascending order
     *  count -> 0
     *  index -> 1
     *  start = startTime[0]
     *  end = endTime[0]
     *  while index less than start length
     *      if startTime[index] > end
     *          start = startTime[index]
     *          end = endTime[index]
     *          count++
     *
     *      index++
     *  return count
     *
     * @param startTimes
     * @param endTimes
     * @return
     */
    public static int maxActivities(int[] startTimes, int[] endTimes) {
        return 0;
    }
}
