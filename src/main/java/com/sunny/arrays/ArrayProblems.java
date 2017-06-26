package com.sunny.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayProblems {

	public static void main(String[] args) {
		testArrayIntersection();
	}
	
	public static void testArrayIntersection() {
		int[] arr1 = {1, 2, 3, 4, 7, 8, 9};
		int[] arr2 = {2, 7, 8};
		
		int[] intersection = arrayIntersection(arr1, arr2);
		System.out.println("Intersections of arrays is: ");
		Arrays.stream(intersection).forEach(val -> System.out.println(val));
	}
	
	/**
	 * Returns intersection of 2 sorted arrays
	 * @param arr1
	 * @param arr2
	 * @return int[] containing intersection of the two sorted arrays
	 */
	public static int[] arrayIntersection(int[] arr1, int[] arr2) {
		ArrayList<Integer> returnList = new ArrayList<>();
		int i = 0, j = 0;
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i] == arr2[j]) {
				returnList.add(arr1[i]);
				i++; j++;
			} else if (arr1[i] > arr2[j]) {
				/* current value at index is greater for first array, increment
				 * second index
				 */
				j++;
			} else {
				i++;
			}
		}
		System.out.println(returnList.toString());
		return returnList.stream().mapToInt(Integer::intValue).toArray();
	}

}
