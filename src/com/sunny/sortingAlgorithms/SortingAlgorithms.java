/**
 * All sorting algorithms will sort in ascending order.  
 */
package com.sunny.sortingAlgorithms;

import java.util.Random;

import com.sunny.common.Logger;

public class SortingAlgorithms {
	
	private final int INPUT_SIZE = 20;
	private int[] inputData;
	
	public SortingAlgorithms() {
		inputData = new int[INPUT_SIZE];
		generateRandomInput();
	}
	
	public static void main(String[] args) {
//		SortingAlgorithms sortingAlgorithm = new SortingAlgorithms();
//		sortingAlgorithm.testInsertionSort();
		int size = 8;
		int[] input = new int[size];
		Random randomGenerator = new Random();
		for(int i = 0; i < size; i++) {
			input[i] = randomGenerator.nextInt(100);
		}
		heapSort(input);
	}
	
	public static void mergeSort(int[] input) {
		
	}
	
	public static void merge(int[] input, int start, int mid, int end) {
		int[] temp = new int[end - start];
	}
	
	public static void heapSort(int[] input) {
		Logger.log("Before heap sort ");
		Logger.logArray(input);
		int mid = input.length / 2;
		while(mid >= 0) {
			heapfy(input, mid);
			mid--;
		}
		Logger.log("After heap sort ");
		Logger.logArray(input);
	}
	/*
	 * min heap
	 */
	private static void heapfy(int[] input, int index) {
		int save = input[index];
		int k = index * 2 + 1;
		while(k < input.length) {
			if(k+1 < input.length && input[k] > input[k+1]) {
				/* find smaller of the two children */
				k++;
			}
			
			/* k current points to the child with smallest data, compare it with the parent */
			if(input[index] < input[k]) {
				/* parent has the smallest data, break */
				break;
			}
			/* otherwise swap parent and child recurse down the tree */
			input[index] = input[k];
			index = k;
			k = index * 2 + 1;
		}
		input[index] = save;
	}
	
	private void insertionSort() {
		int numSwaps = 0;
		int numIterations = 0;
		/** Start with index 1 **/
		for(int i = 1; i < INPUT_SIZE; i++) {
			for(int j = i; j > 0; j--) {
				numIterations++;
				if(inputData[j] < inputData[j-1]) {
					swap(j, j-1);
					numSwaps++;
				} else {
					break;
				}
			}
		}
		Logger.log("Insertion sort stats -> iterations = " + numIterations + ", swaps = " + numSwaps);
	}
	
	private void testInsertionSort() {
		Logger.log("Testing Insertion Sort");
		printArray("Pre sort");
		insertionSort();
		printArray("Post sort");
	}
	
	private void swap(int indexA, int indexB) {
		int tmp = inputData[indexA]; 
		inputData[indexA] = inputData[indexB];
		inputData[indexB] = tmp;
	}
	
	private void generateRandomInput() {
		Random randomGenerator = new Random();
		for(int i = 0; i < INPUT_SIZE; i++) {
			inputData[i] = randomGenerator.nextInt(100);
		}
	}
	
	private void printArray(String header) {
		System.out.println(header);
		for(int i = 0; i < INPUT_SIZE; i++) {
			System.out.print(inputData[i] + ", ");
		}
		System.out.println();
	}
}
