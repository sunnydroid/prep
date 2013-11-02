package com.sunny.stacks;

import java.util.Random;

import com.sunny.common.Logger;

public class ThreeStacksInAnArray {
	private int ARRAY_SIZE;
	private int NUM_STACKS;
	private int[] buffer;
	private int[] topStackPointers;
	
	public ThreeStacksInAnArray() {
		ARRAY_SIZE = 100;
		NUM_STACKS = 3;
	}
	
	public void testVer1() {
		Ver1 testStack = new Ver1(ARRAY_SIZE, NUM_STACKS);
		Random randomNumGenarator = new Random();
		
		for(int i = 0; i < 100; i++) {
			int randomNum = randomNumGenarator.nextInt(100);
			testStack.push(randomNum%3, randomNum);
		}
		
		for(int i = 0; i < 100; i++) {
			int randomNum = randomNumGenarator.nextInt(100);
			testStack.peek(randomNum%3);
		}
		
		for(int i = 0; i < 100; i++) {
			int randomNum = randomNumGenarator.nextInt(100);
			testStack.pop(randomNum%3);
		}
	}
	
	public static void main(String[] args) {
		ThreeStacksInAnArray test3StacksInArray = new ThreeStacksInAnArray();
		test3StacksInArray.testVer1();
	}
	
	/**
	 * This version of 3 stacks in an array implementation simply divides up an array 
	 * in 3 equal parts and 3 reference points keep track of each division. 
	 * 
	 * pros: fairly simple to implement and maintain
	 * cons: if one or more of the stacks are more utilized than others, there will be
	 * empty slots in the array that other stacks cannot use
	 * @author sunnshah
	 *
	 */
	private static class Ver1 {
		private int ARRAY_SIZE;
		private int NUM_STACKS;
		private int STACK_SIZE;
		private int[] buffer;
		private int[] topStackPointers;
		
		public Ver1(int arraySize, int numStacks) {
			buffer = new int[arraySize];
			NUM_STACKS = numStacks;
			STACK_SIZE = (int) Math.floor(arraySize/numStacks);
			
			/* initialize all stack pointers to -1 */
			for(int i = 0; i < numStacks; i++) {
				topStackPointers[i] = -1;
				/* Each pointer will begin at the following locations
				 * 0 => 0
				 * 1 => 32
				 * 2 => 65
				 */
			}
		}
		
		public void push(int stackNum, int pushValue) {
			if(isFull(stackNum)) {
				Logger.log("Stack " + stackNum + "is full, cannot insert value " + pushValue);
			}
			/* find insert location by off-setting 
			int index = STACK_SIZE *
			buffer[topStackPointers[stackNum]] = pushValue;
			Logger.log("Pushed " + pushValue + " in stack " + stackNum + " at buffer index " +topStackPointers[stackNum]);
			topStackPointers[stackNum]++;
		}
		
		public int pop(int stackNum) {
			int popValue = -1;
			if(isEmpty(stackNum)) {
				Logger.log("Stack " + stackNum + " is empty, cannot pop");
				return popValue;
			}
			popValue = buffer[topStackPointers[stackNum]];
			Logger.log("Poped " + popValue + "from stack " + stackNum + " at buffer index " +topStackPointers[stackNum]);
			topStackPointers[stackNum]--;
			
			return popValue;
		}
		
		public int peek(int stackNum) {
			int peakValue = buffer[topStackPointers[stackNum]];
			Logger.log("Peeking value in stack " + stackNum + " = " + peakValue);
			return peakValue;
		}
		
		public boolean isEmpty(int stackNum) {
			if(topStackPointers[stackNum] == ARRAY_SIZE/NUM_STACKS * stackNum) {
				return true;
			}
			
			return false;
		}
		
		public boolean isFull(int stackNum) {
			/*
			 * stack i is full when it reaches STACK_SIZE * (i+1), i.e. 
			 * beginning of the next stack
			 */
			if(topStackPointers[stackNum] == (STACK_SIZE * (stackNum + 1))) {
				return true;
			}
			return false;
		}
	}
	
	private static class Ver2 {
		
	}

	
	

}
