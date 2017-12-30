package com.sunny.sort;

import com.sunny.common.Logger;

import java.util.Arrays;

/**
 * Selection sort is an internal, in-place, in order sort, i.e. it doesn't require external storage, additional space
 * and original ordering of the elements is preserved.
 *
 */
public class SelectionSort {

    /**
     * Approach:
     *  start first index from the second element to array length ->
     *  have a secondary index which runs from first index to 0 in the reverse direction <-
     *      continue swapping values until secondary index reaches 0 and value at current secondary index is less than
     *      to the value at secondary index -1.
     *
     * Algorithm:
     *  for index i = 1 to array length
     *      j = i
     *      while j != 0 && arr[j] > arr[j-1]
     *          swap arr[j] and arr[j-1]
     *          j--
     *
     * Runtime = O(N^2)
     * Space = O(1)
     *
     * @param arr array to be sorted
     */
    public static void selectionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int j = i;
            while (j != 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
//        int[] toSort = {9, 3, 0, 4, 5, 1};
        int[] toSort = {1, 1, 1, 1, 1, 1};

        Logger.log("Before sort " + Arrays.toString(toSort));
        selectionSort(toSort);
        Logger.log("After sort " + Arrays.toString(toSort));

    }
}
