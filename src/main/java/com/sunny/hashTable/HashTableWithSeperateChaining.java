package com.sunny.hashTable;

import com.sunny.linkedList.SingleLinkedList;

public class HashTableWithSeperateChaining {
	final int p = 5;
	int m;
	
	SingleLinkedList[] hashTable;
	
	public HashTableWithSeperateChaining() {
		m = (int) Math.pow(2, p);
		this.hashTable = new SingleLinkedList[m];
	}
	
	/*
	 * Using the multiplication method
	 * k = key
	 * m = size of slot, typically a power of 2, 2^p
	 * w = number of bits in a word
	 * 0 < s < 2^w, choose s such that A = s/2^w will be as close to 0.61
	 * 0 < A < 1 (s/2^w), use 0.67
	 * 
	 */
	public int hashMultiplication(String key) {
		int k = 7;
		for(int i = 0; i < key.length(); i++) {
			k = k * 31 + key.charAt(i);
		}

        return k;
	}
}
