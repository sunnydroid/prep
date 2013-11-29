package com.sunny.intArray;

import java.util.ArrayList;

import com.sunny.common.Logger;

/*
 * Given a set find all subsets of the set
 * Solution: 
 * Each element in the set is/isn't part of a subset resulting 
 * 2^n subsets. This solution can also be implemented using binary
 * representations of numbers from 0 -> 2^n and adding all indexes 
 * of the set that correspond to the locations of 1 bits in the 
 * binary representation
 */
public class AllSubsetsOfSet {
	
	 
	/* Begin with the empty set, and add each element to the 
	 * list of existing subsets.
	 * 
	 * Use recursion: 
	 * Base case returns empty set. 
	 * Else, each recursion takes the list from previous recursions and 
	 * appends new element from the set to existing subsets.
	 */
	public static ArrayList<ArrayList<Integer>> getSubset(ArrayList<Integer> set, int index) {
		
		ArrayList<ArrayList<Integer>> allSubsets;
		
		if(set.size() == index) {
			/* base case, add empty set to allSubsets and return */
			ArrayList<Integer> emptySet = new ArrayList<Integer>();
			allSubsets = new ArrayList<ArrayList<Integer>>();
			allSubsets.add(emptySet);
			
		} else {
			allSubsets = getSubset(set, index+1);
			/* iterate through list of subsets returned from the previous recursion,
			 * add element from the set at current index to each subset and add that
			 * as a new subset 
			 * 
			 * NOTE: you'll get concurrentModificationException if you try to add the new
			 * subset to allSubsets. You'll need to uses and intermediary list
			 */
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> subset : allSubsets) {
				ArrayList<Integer> newSubset = new ArrayList<Integer>();
				newSubset.addAll(subset);
				newSubset.add(set.get(index));
				moreSubsets.add(newSubset);
			}
			
			allSubsets.addAll(moreSubsets);
		}
		
		return allSubsets;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		Logger.log("Set: ");
		Logger.logArrayList(list);
		
		ArrayList<ArrayList<Integer>> subsets = getSubset(list, 0);
		Logger.log("All subsets: ");
		logSubsets(subsets);
	}
	
	private static void logSubsets(ArrayList<ArrayList<Integer>> subsets) {
		for(ArrayList<Integer> subset : subsets) {
			Logger.logArrayList(subset);
		}
	}
	
	

}
