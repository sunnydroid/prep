package com.sunny.programmingChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Given busy times of individuals, find free slots between their busy times so that a meeting can be scheduled.
 */
public class FindFreeTimes {

    // day_start = 1, day_end = 12
// Input: [2, 5] [3, 4] [5, 6] [9, 10] [11, 12]
// Output: [1, 2] [6, 9] [10, 11]


    /**
     * Approach:
     *  Merge the busy time intervals. Times between the start and end times of the busy intervals are can be returned
     *  as free times
     * @param busyIntervals
     * @param dayStart
     * @param dayEnd
     * @return
     */
    public static List<Interval> getFreeIntervals(List<Interval> busyIntervals, int dayStart, int dayEnd) {
        if(busyIntervals == null || busyIntervals.isEmpty()) {
            return Collections.emptyList();
        }


        SortByEndTime sortByEndTime = new SortByEndTime();
        Collections.sort(busyIntervals, sortByEndTime);
        Collections.reverse(busyIntervals);

        // valdiate dayStart and dayEnd

    /* merge overlapping times */
        ListIterator<Interval> listIterator = busyIntervals.listIterator();
        Interval currentInterval = listIterator.next();
        while(listIterator.hasNext()) {
            Interval previousInterval = listIterator.next();
            // check for overlap
            if(overLaps(previousInterval, currentInterval)) {
                Interval mergedInterval = merge(previousInterval, currentInterval);
                listIterator.remove();
                currentInterval.start = mergedInterval.start;
                currentInterval.end = mergedInterval.end;
            } else {
                currentInterval = previousInterval;
            }
        }

//        return busyIntervals;

        List<Interval> freeIntervals = new ArrayList<>();
        /* free times are times between end time of the current interval and start time of the next interval */
        Iterator<Interval> iterator = busyIntervals.iterator();
        Interval interval1 = iterator.next();
        while(iterator.hasNext()) {
            Interval nextInterval = iterator.next();
            Interval freeInterval = new Interval(nextInterval.end, interval1.start);
            freeIntervals.add(freeInterval);
            interval1 = nextInterval;
        }

        // add times available between first non busy interval and dayStart, similarly with last interval and dayEnd
        // since they are sorted by end time
        Interval lastInterval = busyIntervals.get(0);
        Interval firstInterval = busyIntervals.get(busyIntervals.size() - 1);

        if(lastInterval.end < dayEnd) {
            freeIntervals.add(new Interval(lastInterval.end, dayEnd));
        }

        if(dayStart < firstInterval.start) {
            freeIntervals.add(new Interval(dayStart, firstInterval.start));
        }

        return freeIntervals;
    }

    /** driver */

    public static void main(String[] args) {
        Interval i1 = new Interval(2, 3);
        Interval i2 = new Interval(2, 5);
        Interval i3 = new Interval(7, 9);
        Interval i4 = new Interval(11, 12);

        List<Interval> testIntervals = new ArrayList<>();
        testIntervals.add(i1);
        testIntervals.add(i2);
        testIntervals.add(i3);
        testIntervals.add(i4);

        List<Interval> freeTimes = getFreeIntervals(testIntervals, 1, 13);
        System.out.println(freeTimes);
    }

    public static Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public static boolean overLaps(Interval i1, Interval i2) {
        if(i1.end <= i2.end && i1.end >= i2.start) {
            return true;
        }
        return false;
    }

    static class Interval {
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
