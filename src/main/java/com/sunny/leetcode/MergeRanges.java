package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.*;

/**
 * Given two list of unsorted intervals V1 and V2 write 2 functions 'OR ' and 'And' to return a new list
 *
 * OR Function (union of list ): Input V1 = (2,4) (6,8) (1,3) OR V2 = (7,9) (2,5)
 * output = (1,5) (6,9)
 *
 * AND function : This will be intersection function and will return intersection of the lists
 * Input V1 = (2,4) (6,8) (1,3) AND V2 = (7,9) (2,5)
 * output = (2,4) (7,8)
 *
 * Algorithm:
 *  first merge ranges within each list using the OR operation. This will consolidate the ranges
 *  for the AND operation, repeat the merge algorithm below but use upper floor and lower ceiling ranges to
 *  get the intersection
 *
 * Merge Algorithm (brute force)
 *  merged list = empty list
 *  add all elements in the list to a queue
 *  while queue is not empty:
 *      current range -> queue.remove
 *      if merged list is empty:
 *          add current range to list
 *      else:
 *          for each range element in the merged list:
 *              if overlap with current range:
 *                  create a new range from the two ranges
 *                  remove range from merged list
 *                  add new merged range to the queue to be compared to remaining elements in merged list
 *              else
 *                  add current range to the list
 *
 * This approach requires comparision of range with every other range and additional space for the
 * queue resulting in:
 * Runtime = O(n^2)
 * Space = O(n) additional space
 *
 * A more efficient approach would be to sort the ranges first and merge in place. Refer to MergeRangesEfficient class
 *
 */
public class MergeRanges {

    public static List<List<Integer>> mergeListUsing(List<List<Integer>> unmergedList, MERGE_ALGORITHM algorithm) {
        if (unmergedList.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<Integer>> mergedList = new ArrayList<>();
        Queue<List<Integer>> processQueue = new LinkedList<>();
        processQueue.addAll(unmergedList);

        /**
         * process till no overlapping ranges remain in the queue
         */
        while (!processQueue.isEmpty()) {
            List<Integer> processRange = processQueue.remove();
            /**
             * if this is the very first element, add it to the merged list
             */
            if (mergedList.isEmpty()) {
                mergedList.add(processRange);
            } else {
                /**
                 * iterate through all the ranges in the merged list to check if merge is possible with process range
                 */
                boolean merge = false;
                ListIterator<List<Integer>> mergedListIterator = mergedList.listIterator();
                while (mergedListIterator.hasNext()) {
                    List<Integer> currentRange = mergedListIterator.next();
                    Optional<List<Integer>> possibleMerge;
                    /**
                     * Select merge algorithm based on what has been passed in
                     */
                    if (algorithm.equals(MERGE_ALGORITHM.UNION_MERGE)) {
                        possibleMerge = unionMerge(processRange, currentRange);
                    } else {
                        possibleMerge = intersectionMerge(processRange, currentRange);
                    }

                    if (possibleMerge.isPresent()) {
                        /**
                         * merged current range in merged list with range from the queue:
                         *  remove current range from merged list
                         *  add merged range to queue to be processed against ranges already merged
                         *  no need to check with remaining ranges if currentRange and processRange merge
                         */
                        processQueue.add(possibleMerge.get());
                        mergedListIterator.remove();
                        merge = true;
                        break;

                    }
                }
                if (!merge) {
                    /**
                     * if processRange did not merge with any of the processed ranges, add
                     * it to the merged list
                     */
                    mergedListIterator.add(processRange);
                }
            }
        }

        return mergedList;
    }

    /**
     * merges ranges if possible using union
     * @param range1 first range to be merged
     * @param range2 second range to be merged
     * @return  merged range or null if merge is not possible
     */
    public static Optional<List<Integer>> unionMerge(List<Integer> range1, List<Integer> range2) {
        if ((range1.get(0) >= range2.get(0) && range1.get(0) <= range2.get(1)) ||
                (range1.get(1) >= range2.get(0) && range1.get(1) <= range2.get(1))) {
            int lowerRange = Math.min(range1.get(0), range2.get(0));
            int upperRange = Math.max(range1.get(1), range2.get(1));
            List<Integer> mergedRange = new ArrayList<>();
            mergedRange.add(lowerRange);
            mergedRange.add(upperRange);

            return Optional.of(mergedRange);
        }

        return Optional.empty();
    }

     /**
     * merges ranges if possible using intersection
     * @param range1 first range to be merged
     * @param range2 second range to be merged
     * @return  merged range or null if merge is not possible
     */
    public static Optional<List<Integer>> intersectionMerge(List<Integer> range1, List<Integer> range2) {
        if ((range1.get(0) >= range2.get(0) && range1.get(0) <= range2.get(1)) ||
                (range1.get(1) >= range2.get(0) && range1.get(1) <= range2.get(1))) {
            int lowerRange = Math.max(range1.get(0), range2.get(0));
            int upperRange = Math.min(range1.get(1), range2.get(1));
            List<Integer> mergedRange = new ArrayList<>();
            mergedRange.add(lowerRange);
            mergedRange.add(upperRange);

            return Optional.of(mergedRange);
        }

        return Optional.empty();
    }

    public static enum MERGE_ALGORITHM {
        UNION_MERGE, INTERSECTION_MERGE
    }

    public static void main(String[] args) {
        List<List<Integer>> V1 = new ArrayList<>();
        List<List<Integer>> V2 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list5 = new ArrayList<>();

        list1.add(2);
        list1.add(4);
        list2.add(6);
        list2.add(8);
        list3.add(1);
        list3.add(3);

        list4.add(7);
        list4.add(9);
        list5.add(2);
        list5.add(5);

        V1.add(list1);
        V1.add(list2);
        V1.add(list3);

        V2.add(list4);
        V2.add(list5);

        List<List<Integer>> combinedList = new ArrayList<>();
        combinedList.addAll(V1);
        combinedList.addAll(V2);

        List<List<Integer>> unionMergedList = mergeListUsing(combinedList, MERGE_ALGORITHM.UNION_MERGE);

        List<List<Integer>> unionMergedList1 = mergeListUsing(V1, MERGE_ALGORITHM.UNION_MERGE);
        List<List<Integer>> unionMergedList2 = mergeListUsing(V2, MERGE_ALGORITHM.UNION_MERGE);
        unionMergedList1.addAll(unionMergedList2);
        /**
         * Determine intersection only after merging the ranges using intersection
         */
        List<List<Integer>> intersectionMergedList = mergeListUsing(unionMergedList1, MERGE_ALGORITHM.INTERSECTION_MERGE);

        Logger.log("Union merge");
        for (List<Integer> listToPrint : unionMergedList) {
            Logger.log(listToPrint);
        }

        Logger.log("Intersection merge");
        for (List<Integer> listToPrint : intersectionMergedList) {
            Logger.log(listToPrint);
        }
    }
}
