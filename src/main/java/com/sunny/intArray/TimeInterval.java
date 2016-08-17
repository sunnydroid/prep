package com.sunny.intArray;

import com.sunny.common.Logger;

/**
 * Adds an interval [from, to] into internal structure.
 * 
 * void addInterval(int from, int to);
 * 
 * /** Returns a total length covered by intervals. If several intervals
 * intersect, intersection should be counted only once. Example:
 * 
 * addInterval(3, 6) addInterval(8, 9) addInterval(1, 5)
 * 
 * getTotalCoveredLength() -> 6 i.e. [1,5] and [3,6] intersect and give a total
 * covered interval [1,6] [1,6] and [8,9] don't intersect so total covered
 * length is a sum for both intervals, that is 6.
 * 
 * t1 ____t5 ________t2 t3_________t4 t5 ___t6
 * 
 * 
 * 0 1 2 3 4 5 6 7 8 9 10
 * 
 * 
 * int getTotalCoveredLength();
 */
public class TimeInterval {

	Interval head;

	public TimeInterval() {

	}

	public static void main(String[] args) {
		TimeInterval test = new TimeInterval();
		test.insert(4, 6);
		test.printIntervals();
		test.getTotalTimeCovered();
		test.insert(1, 5);
		test.insert(7, 10);
		test.insert(8, 13);
		test.printIntervals();
		test.getTotalTimeCovered();
		test.insert(5, 20);
		test.printIntervals();
		test.getTotalTimeCovered();
	}

	public void insert(int start, int end) {

		Interval newInterval = new Interval(start, end);
		if (head == null) {
			head = newInterval;

			return;
		}
		/*
		 * since we are inserting intervals in sorted order, check if
		 * newInterval comes before first interval
		 */
		if (newInterval.end < head.start) {
			newInterval.next = head;
			head = newInterval;
			return;
		}

		Interval currentInterval = head;
		Interval next = head;
		while (next != null) {
			if (next.covers(newInterval)) {
				/* new interval already covered by existing interval */
				return;
			}
			if (newInterval.covers(next) || next.intersects(newInterval)) {
				/*
				 * new interval covers existing interval or intersects with
				 * current interval: merge with current interval and continue
				 * merging
				 */
				next.merge(newInterval);
				mergeRemaining(currentInterval);
				return;
			}
			if (next.end < newInterval.start) {
				/* proceed to the next node */
				currentInterval = next;
				next = next.next;
			} else {
				/* insert new node here */
				newInterval.next = currentInterval.next ;
				currentInterval.next = newInterval;
				return;
			}
		}

		if (next == null) {
			/*
			 * If we get to the end, it means we need to add new interval to the
			 * end of the list
			 */
			currentInterval.next = newInterval;
		}
	}

	private void mergeRemaining(Interval from) {
		while (from.next != null
				&& (from.covers(from.next) || from.intersects(from.next))) {
			from.merge(from.next);
			Interval temp = from.next;
			from.next = temp.next;
			/*
			 * after merging two intervals, remove the merged interval so it can
			 * be GCed
			 */
			temp.next = null;
		}
	}

	public void getTotalTimeCovered() {
		int sum = 0;
		Interval current = head;
		while (current != null) {
			sum += current.end - current.start;
			current = current.next;
		}

		Logger.log("Total time coverd => " + sum);
	}

	public void printIntervals() {
		Interval current = head;
		while (current != null) {
			Logger.log("Interval => start: " + current.start + ", end: "
					+ current.end);
			current = current.next;
		}
	}

	private static class Interval {

		int start;
		int end;
		Interval next;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
			next = null;
		}

		public void merge(Interval interval) {
			start = Math.min(interval.start, start);
			end = Math.max(interval.end, end);
		}

		public boolean intersects(Interval interval) {
			if (interval.start < start && interval.end <= end
					|| interval.start <= end && interval.end > end) {
				return true;
			}

			return false;
		}

		public boolean covers(Interval interval) {
			if (interval.start >= start && interval.end <= end) {
				return true;
			}

			return false;
		}
	}

}
