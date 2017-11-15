package com.sunny.leetcode;


import java.util.*;

/**
 * We need a way to compare the intervals in order to sort them. In java comparisons are done either using
 * the comparator class or the comparable interface. Here is the difference between the two:
 *
 * Implementing Comparable means "I can compare myself with another object." This is typically useful
 * when there's a single natural default comparison.
 *
 * Implementing Comparator means "I can compare two other objects." This is typically useful when
 * there are multiple ways of comparing two instances of a type - e.g. you could compare people by age, name etc.
 *
 * We can sort intervals either using start or end times, hence need to implement two comparator classes
 */
public class MergeRangesEfficient {

    public static List<Interval> mergeRangesExtraSpace(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return Collections.emptyList();
        }
        /**
         * Sort the collections by start time
         */
        SortByStartTime sortByStartTime = new SortByStartTime();
        Collections.sort(intervals, sortByStartTime);

        return intervals;
    }

    /**
     * Ordering by decreasing end times ensures the intervals if merged, are guaranteed to not intersect with
     * intervals already merged or processed previously. This ensures we do not need additional space or reprocessing
     * Algorithm:
     *  sort intervals with descending end times
     *  iterator -> list iterator
     *  current interval -> iterator get next
     *  while iterator has next
     *      next interval = iterator get next
     *      if next interval's end time is greater than or equals current interval's end time:
     *          merge lists
     *          update current interval's start and end times to that of the merged list
     *          remove next interval since it has been merged
     *      else
     *          current interval = next interval
     *
     * @param intervals
     * @return
     */
    public static List<Interval> mergeRangesEfficient(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return Collections.emptyList();
        }

        /**
         * sort the collections by end time and reverse list so interval with
         * highest end time is first interval in the list
         */
        SortByEndTime sortByEndTime = new SortByEndTime();
        Collections.sort(intervals, sortByEndTime);
        Collections.reverse(intervals);

        /**
         * use an iterator to merge intervals
         */
        ListIterator<Interval> iterator = intervals.listIterator();
        Interval currentInterval = iterator.next();
        while (iterator.hasNext()) {
            Interval nextInterval = iterator.next();
            if (nextInterval.end <= currentInterval.end && nextInterval.end >= currentInterval.start) {
                Interval mergedInterval = merge(currentInterval, nextInterval);
                iterator.remove();
                currentInterval.start = mergedInterval.start;
                currentInterval.end = mergedInterval.end;
            } else {
                currentInterval = nextInterval;
            }
        }

        return intervals;
    }

    public static Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(2,4));
        intervals.add(new Interval(2,4));
        intervals.add(new Interval(6,8));
        intervals.add(new Interval(7,9));

        System.out.println(mergeRangesEfficient(intervals));
    }

    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class SortByStartTime implements Comparator<Interval> {

        /**
         * If interval1 has higher start time, than interval2, a positive number is returned
         * else a negative number. If they are equal, 0 should be returned.
         * @param interval1 first interval to compare
         * @param interval2 second interval to compare with
         * @return either -ve, +ve or 0
         */
        @Override
        public int compare(Interval interval1, Interval interval2) {
            return interval1.start - interval2.start;
        }
    }


    public static class SortByEndTime implements Comparator<Interval> {
        /**
         * If interval1 end time is higher than interval2 end time, a +ve integer is returned, else
         * a -ve interger is returned. If they have the same end time, 0 is returned
         * @param interval1 first interval to compare
         * @param interval2 second interval to compare
         * @return +ve, -ve or 0
         */
        @Override
        public int compare(Interval interval1, Interval interval2) {
            return interval1.end - interval2.end;
        }
    }

}
