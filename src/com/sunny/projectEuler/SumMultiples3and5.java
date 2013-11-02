package com.sunny.projectEuler;

import com.sunny.common.Logger;

public class SumMultiples3and5 {
	private int till;
	
	public SumMultiples3and5(int max) {
		till = max;
	}
	/**
	 * Run time =  O(n), linear
	 * Space requirement = O(1), no extra space required 
	 * @return
	 */
	public int findSumOfMultiples() {
		int sum = 8;
		for(int i = 6; i < till; i++) {
			if(i % 3 == 0) {
				sum += i;
				continue;
			}
			if(i % 5 == 0) {
				sum += i;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		int till = 10;
		SumMultiples3and5 test = new SumMultiples3and5(till);
		int sum = test.findSumOfMultiples();
		Logger.log("Sum of mulitples of 3, 5 till " + till + " = " + sum);
	}

}
