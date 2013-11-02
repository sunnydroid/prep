package com.sunny.sort;

public class MergeSort {
	/*
	 * Algorithm:
	 *  given A[0....n]
	 *  	i <- 0
	 *  	j <- A.lenght
	 *  
	 * 		while(j > i) 
	 *  		split(A from i -> (i+j)/2 and (i+j)/2+1 -> j)
	 *  		merge subarrays
	 *  Running time = O(nlogn)
	 */	
	
	public static void mergeSortChars(char[] input) {
		int i = 0; 
		int j = input.length;
		
		while(i < j) {
			
		}
	}
	
	public static int split(int[] array, int start, int end) {
			return (int) Math.ceil((start + end)/2);
	}
	
	public static char[] mergeCharArrays(char[] array1, int size1, int size2) {
		char[] mergedArray = new char[size1 + size2];
		int i = 0;
		int j = 0;
		int k = 0;
		
		for(int j ;j < array1.length && k < array2.length; ) {
			if(array1[j] <= array2[k]) {
				mergedArray[i] = array1[j];
				i++;
				j++;
			} else {
				mergedArray[i] = array2[k];
				i++;
				k++;
			}
		}
		/* copy remaining elements, if any, from both sub-arrays to merged array */ 
		while(j < array1.length) {
			mergedArray[i] = array1[j];
			i++;
			j++;
		}
		while(k < array2.length) {
			mergedArray[i] = array2[k];
			i++;
			k++;
		}
		
		return mergedArray;
	}
}
