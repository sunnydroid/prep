package com.sunny.leetcode;

import com.sunny.common.Logger;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import com.sunny.leetcode.MergeRangesEfficient.Interval;

/**
 * Given a set of non-overlapping & sorted intervals, insert a new interval into the intervals (merge if necessary).
 *
 * Example 1 :
 * Given intervals [1 ,3] ,[6 ,9] , insert and merge [2 ,5] in as [1 ,5] ,[6 ,9].
 * Example 2 :
 * Given [1 ,2] ,[3 ,5] ,[6 ,7] ,[8 ,10] ,[12 ,16] , insert and merge [4 ,9] in as
 * [1 ,2] ,[3 ,10] ,[12 ,16].
 * This is because the new interval [4 ,9] overlaps with [3 ,5] ,[6 ,7] ,[8 ,10].
 *
 */
public class InsertRange {

    /**
     * Algorithm:
     *  Add the new range to the list of existing intervals
     *  Sort the list in order of end time.
     *  Using a list iterator, begin with the interval with highest end time:
     *      if the previous interval overlaps with current range:
     *          merge intervals, i.e. max of end time, min of begin time
     *          remove merged interval
     *
     * return list
     *
     * Note: You cannot use the Pair class from javafx.util because key value pairs are immutable.
     *
     * Runtime : O(nlog(n)) due to sort
     * Space: O(1) no additional space required
     *
     * @param intervals
     * @return
     */
    public static List<Interval> insertRange(List<Interval> intervals, Interval interval) {
        /** add new range to the list */
        intervals.add(interval);
        /** sort by end times */
        IntervalComparator intervalComparator = new IntervalComparator();
        Collections.sort(intervals, intervalComparator);
        Collections.reverse(intervals);

        ListIterator<Interval> listIterator = intervals.listIterator();
        Interval currentInterval = listIterator.next();
        while (listIterator.hasNext()) {
            Interval nextInterval = listIterator.next();
            /** check if the intervals can be merged, do if so and remove next range once merged with current range */
            if (merge(currentInterval, nextInterval)) {
                /** next interval has been merged into current interval, remove next interval from the list */
                listIterator.remove();
            } else {
                currentInterval = nextInterval;
            }
        }

        return intervals;
    }

    /**
     * This problem can also be tackled without sorting in O(n) time but O(n) space
     * Approach:
     *  Add intervals to a new list based on the following cases
     *  There are 3 cases that need to be handled as we iterate through list of intervals
     *     1) current interval end time is less than new interval start time:
     *          add current interval to list
     *     2) current interval has start time greater than the end time of new interval
     *          add new interval to list
     *          make interval new interval to check for overlap with remaining intervals
     *     3) there is overlap between interval and new interval
     *          create new interval with start time = Min(interval.start_time, newInterval.start_time) and
     *              end time = Max(interval.end_time, newInterval.end_time)
     *          this becomes the new interval to check remaining intervals against
     * Algorithm:
     *  initialize new list of intervals
     *  for each interval:
     *      if interval end time is < new interval start time:
     *          add interval to new list
     *      else if interval start time > new interval end time:
     *          add new interval to new list
     *          set interval to new interval
     *      else
     *          create new interval from interval and new interval
     *          set newly created interval as new interval
     *
     *  return new list
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static List<Interval> insertRangeNoSort(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                /** case 1, no over lap: interval has earlier end time than new interval's start time */
                mergedIntervals.add(interval);
            } else if (interval.start > newInterval.end) {
                /** case 2, no over lap: interval has later start time than new interval's end time */
                mergedIntervals.add(newInterval);
                newInterval = interval;
            } else {
                /** there is over lap, merge the two intervals and use that as new interval */
                Interval mergedInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(interval.end, newInterval.end));
                newInterval = mergedInterval;
            }
        }

        /** add new interval */
        mergedIntervals.add(newInterval);

        return mergedIntervals;
    }

    public static boolean merge(Interval interval1, Interval interval2) {
        /** determine if ranges overlap */
        if ((interval1.start >= interval2.start && interval1.start <= interval2.end)
                || (interval2.end >= interval1.start && interval2.end <= interval1.end)) {
            interval1.start = Math.min(interval1.start, interval2.start);
            interval1.end = Math.max(interval1.end, interval2.end);
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        List<MergeRangesEfficient.Interval> ranges = new ArrayList<>();
        ranges.add(new Interval(1, 2));
        ranges.add(new Interval(3, 5));
        ranges.add(new Interval(6, 7));
        ranges.add(new Interval(8, 10));
        ranges.add(new Interval(12, 16));

        Interval newInterval = new Interval(4, 9);

//        Logger.log("Using insert ranges in place but sort" + insertRange(ranges, newInterval).toString());
        Logger.log("Using insert ranges with extra space but no sort" + insertRangeNoSort(ranges, newInterval).toString());

    }

    public static class IntervalComparator implements Comparator<MergeRangesEfficient.Interval> {
        @Override
        public int compare(MergeRangesEfficient.Interval o1, MergeRangesEfficient.Interval o2) {
            return o1.end - o2.end;
        }
    }

}
