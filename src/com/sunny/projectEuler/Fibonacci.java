package com.sunny.projectEuler;

import com.sunny.common.Logger;

public class Fibonacci {
	private int seed1 = 0;
	private int seed2 = 1;
	private long sum = 0L;
	
	/**
	 * recursive implementation of the fibonnaci series
	 * called by helper function.
	 * @param till
	 * @param seed1
	 * @param seed2
	 */
	public void fibonacciRecursive(int till, int seed1, int seed2) {
		int nextVal = seed1 + seed2;
		if(nextVal > till) {
			return;
		}
		Logger.log(nextVal + ", ");
		fibonacciRecursive(till, seed2, nextVal);
	}
	
	/**
	 * Helper function
	 * @param max
	 */
	public void findFibonacciTill(int max) {
		Logger.log(seed1 + ", " + seed2 );
		fibonacciRecursive(max, seed1, seed2);
	}
	
	/**
	 * time = O(n), linear
	 * space = O(1)
	 * @param till
	 * @return
	 */
	public long sumEvenFibonacciNums(int till) {
		int nextVal = seed1 + seed2;
		while(nextVal < till) {
			if(nextVal % 2 == 0) {
				sum += nextVal;
			}
			int tmp = seed2;
			seed2 = nextVal;
			nextVal +=tmp;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int till = 4000000;
		Fibonacci test = new Fibonacci();
		long sum = test.sumEvenFibonacciNums(till);
		
		Logger.log("Sum of even fibonnaci numbers till " + till + " = " + sum);
		
	}
}
