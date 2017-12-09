package com.sunny.leetcode;

/**
 * Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character. 
 * Find all possible words that can be formed by a sequence of adjacent characters. Note that we can move to any of 
 * 8 adjacent characters, but a word should not have multiple instances of same cell.
 * Example :
 * 
 * Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
 *       boggle[][]   = {{'G','I','Z'},
 *                       {'U','E','K'},
 *                       {'Q','S','E'}};
 *                       
 *    isWord(str): returns true if str is present in dictionary
 *                 else false.
 *                 
 * Output:  Following words of dictionary are present
 * 
 * GEEKS
 * QUIZ
 * 
 * @author shah
 */
public class WordsFromCharBoard {

	/**
	 * Approach: We have to start from every cell and fan out in all directions:
	 * 	up, down, left, right, diagUpLeft, diagUpRight, diagDownLeft, diagDownRight.
	 * 	base case: 
	 * 		when all cells have been visited
	 * 	default case:
	 * 		check if words till now + character at current board location make up a valid word. 
	 * 		If so, add to new list and recurse in all possible directions and mark that cell in visited array
	 * 		For each recursion, add results of words found to list from the caller. 
	 * 		
	 * Algorithm: Driver program
	 * 	list -> new list of string
	 * 	for each row:
	 * 		for each col:
	 * 			mark row, col as visited
	 * 			call find words with char at row, col appended to charsTillNow
	 * 			add words returned by find words to list
	 * 
	 * Algorithm: findWords program
	 * 	list -> new list of string
	 * 		if charsTillNow + char at row, col in dictionary:
	 * 			add charsTillNow + char to list
	 * 		mark visited array 
	 * 		recurse with charsTillNow, visited array and row, col in all possible directions
	 * 		add words from recursion to the list 
	 * 
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * @param charsTillNow
	 * @param visited
	 * @return
	 */
	public List<String> findWords(char[][] board, int row, int col, String charsTillNow, boolean[][] visited, String[] dict) {
		
	}
	
	public static boolean canRecurse(int col, int row, String direction, boolean[][] visited) {
		int rows = visited.length;
		int cols = visited[0].length;
		
		
		switch(direction)
	}
}
