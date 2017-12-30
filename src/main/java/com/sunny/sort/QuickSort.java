package com.sunny.sort;

import com.sunny.common.Logger;

public class QuickSort {

	public static void main(String[] args) {
        int[] a = {2,3,4,5,1,2};
        Logger.log("before sort");
        Logger.logArray(a);
        quickSort(a, 0, a.length - 1);
        Logger.log("After sort");
        Logger.logArray(a);
        /* pair of arrays that need to be sorted as a pair */
        int[] startTimes = {1, 3, 0, 5, 8, 5};
        int[] finishTimes = {2, 4, 6, 7, 9, 9};

//        Logger.log("before sort");
//        Logger.logArray(startTimes);
//        Logger.log("");
//        Logger.logArray(finishTimes);
//        quickSortPair(startTimes, finishTimes, 0, startTimes.length - 1);
//        Logger.log("After sort");
//        Logger.logArray(startTimes);
//        Logger.log("");
//        Logger.logArray(finishTimes);
	}


    /**
     * Algorithm:
     *  If input array has length less than 2, return
     *  If start index less than end index
     *      Get partition after sorting
     *      recurse with start = initial start and end = partition
     *      recurse with start = partition + 1 and end = initial end
      * @param a
     * @param start
     * @param end
     */
	public static void quickSort(int[] a, int start, int end) {
		if(a.length < 2) {
			return;
		}

        if(start < end) {
            int partition = partition(a, start, end);
            quickSort(a, start, partition);
			quickSort(a, partition+1, end);
		}
	}

    /**
     * Algorithm
     *  Use element at center of start - end as pivot
     *  Use 2 indices, one from the left and one from the right
     *      increment left index until you find element at index bigger than pivot
     *      decrement right index until you find element at right index smaller than pivot
     *      then swap
 *      return the left index as new pivot
     * @param nums
     * @param start
     * @param end
     * @return
     */
	public static int partition(int[] nums, int start, int end) {

	    /** use end as pivot */
		int pivot = end;
		int i = start;

		for(int j = start; j < end; j++) {
		    /** value at current index is greater than value at pivot, swap */
			if(nums[j] > nums[pivot]) {
                swap(nums, pivot, j);
                /** update the location of where the next partition should take place */
                i++;
            }
		}
		
		return i;
	}
	
	public static void swap(int[] a, int b, int c) {
		int tmp = a[b];
		a[b] = a[c];
		a[c] = tmp;
	}

    /**
     * Sort pair of related arrays using quick sort.
     * Sorting is done based off of elements in leading array but swapping is done on both
     * leading and trailing arrays
     * @param leadingPair
     * @param trailingPair
     */
    public static void quickSortPair(int[] leadingPair, int[] trailingPair, int start, int end) {

        if(leadingPair == null || trailingPair == null || leadingPair.length != trailingPair.length) {
            Logger.log("invalid input, leading and trailing pairs have to be non null and equal in length");
            return;
        }

        if(leadingPair.length < 2) {
            return;
        }

        if(start < end) {
            int partition = quickSortPairParition(leadingPair, trailingPair, start, end);
            quickSortPair(leadingPair, trailingPair, start, partition);
            quickSortPair(leadingPair, trailingPair, partition + 1, end);
        }

    }

    public static int quickSortPairParition(int[] leadingPair, int[] trailingPair, int start, int end) {
        /* use center element of leading pair as pivot */
        int pivot = leadingPair[ (start + end) / 2];
        int leftSwapIndex = start;
        int rightSwapIndex = end;

        while(leftSwapIndex < rightSwapIndex) {
            /* find first element left of pivot which needs to be swapped
            * need to ensure duplicates are handled with less than or equal
            */
            while(leadingPair[leftSwapIndex] < pivot) {
                leftSwapIndex++;
            }

            while(leadingPair[rightSwapIndex] > pivot) {
                rightSwapIndex--;
            }

            doubleSwap(leadingPair, trailingPair, leftSwapIndex, rightSwapIndex);
        }

        return leftSwapIndex;
    }

    /**
     * Swaps elements at the same index for pair of input arrays
     * Useful when sorting a pair of arrays where the elements in the first array form pair with elements
     * in the second array
     * @param firstArray
     * @param secondArray
     * @param i
     * @param j
     */
    public static void doubleSwap(int[] firstArray, int[] secondArray, int i, int j) {
        /* swap elements in first array */
        int temp = firstArray[i];
        firstArray[i] = firstArray[j];
        firstArray[j] = temp;
        /* swap elements in second array */
        temp = secondArray[i];
        secondArray[i] = secondArray[j];
        secondArray[j] = temp;
    }

}
