package com.sunny.sort;

import java.util.Random;

import com.sunny.common.Logger;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = {2,3,4,5,1,6};
		quickSort(a, 0, a.length - 1);
	}
	
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
	
	public static int partition(int[] a, int start, int end) {
		
		Logger.logArray(a);
		Logger.log("");
	
		int pivot = a[(start + end) / 2];
		int i = start;
		int j = end;
		
		while(i < j) {
			/* increment right until find element greater than pivot */
			while(a[i] < pivot) {
				i++;
			}
			/* decremtn left until you find element less than greater than pivot */
			while(a[j] > pivot) {
				j--;
			}
			/* now swap the elements at the two indices */
			swap(a, i, j);
		}
		
		return i; 
	}
	
	public static void swap(int[] a, int b, int c) {
		int tmp = a[b];
		a[b] = a[c];
		a[c] = tmp;
	}
}
