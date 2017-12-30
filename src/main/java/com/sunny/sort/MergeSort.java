package com.sunny.sort;


import com.sunny.common.Logger;

import java.util.Arrays;

/**
 * Merge sort is divide and conquer, recursive algorithm. It is stable algorithm where original ordering is preserved.
 * However it requires allocation of additional
 *
 *
 * Runtime O(Nlog(N))
 */
public class MergeSort {
	/**
     * Approach:
     *  Split the array into smaller sub arrays, each sub array is half of the original
     *  Merge the two sub arrays by way of merging two sorted arrays:
     *      have indexes run in both sub arrays and while indexes are less than respective sub array lengths:
     *        copy over the smaller value into the merged array
     *      if index for either sub array is less than its respective sub array length, copy over its values into
     *      merged array. This happens when all most of values from the first sub array were copied over and there
     *      are elements remaining from the second sub array.
     *
	 * Algorithm:
	 *  given A[0....n]
	 *  	i <- 0
	 *  	j <- A.length - 1
	 *  
	 * 		while(j > i) 
	 *  		split(A from i -> (i+j)/2 and ((i+j)/2) + 1 -> j)
	 *  		merge subarrays
     *
     *
     * Merge algorithm
     *  i, j , k -> 0
     *  while(i is less than arr1.length AND j is less arr2.length):
     *      arr[k] = smaller of arr[i] and arr[j] // increment i or j depending on which value was inserted
     *      k++
     *
     *  if i < arr1.length:
     *      copy over remaining values from arr1 into arr
     *  if j < arr2.length
     *      copy over remaining values from arr2 into arr
	 *
     * Running time = O(nlogn)
	 */
	public static void mergeSort(int[] input) {
	    mergeSortHelper(input, 0, input.length - 1);
	}

	public static void mergeSortHelper(int[] input, int start, int end) {
//        if (start < end) {
//            /** split recursively */
//            mergeSortHelper(input, start, (start + end)/2);
//            mergeSortHelper(input, (start + end)/2 + 1, end);
//            /** once recursive split is complete, begin merging the splits into original array */
//            merge(input, start, (start+end)/2, (start + end)/2 + 1, end);
//        }
        merge(input, 0, 1, 2, 3);
        merge(input, 4, 5, 6, 7);
        merge(input, 0, 3, 4, 7);
    }

	public static void merge(int[] input, int start1, int end1, int start2, int end2) {
		int i = start1;
		int j = start2;
        /** k tracks the index location in the original input array and will run from
         * start1 to end2
         */
		int k = start1;
		
		while(i < end1 && j < end2) {
			if(input[i] < input[j]) {
				input[k] = input[i];
				i++;
				k++;
			} else {
				input[k] = input[j];
				k++;
                j++;
            }
		}
		/* copy remaining elements, if any, from both sub-arrays to merged array */ 
		while(i < end1) {
			input[k] = input[i];
			i++;
			k++;
		}
		while(j < end2) {
			input[k] = input[j];
			j++;
			k++;
		}
	}

    public static void main(String[] args) {
        int[] toSort = {3, 1, 9, 2, 0, 7, 8, 5};
        Logger.log("Before merge sort " + Arrays.toString(toSort));
        mergeSort(toSort);
        Logger.log("After merge sort " + Arrays.toString(toSort));
    }
}
