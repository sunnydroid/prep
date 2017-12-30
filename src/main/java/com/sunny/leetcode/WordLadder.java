package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words ( start and end) , and a dictionary , find the length of shortest transformation sequence from start to end, such that:
 * Only one letter can be changed at a time
 *
 * Each intermediate word must exist in the dictionary For example ,
 * Given :
 * start = "hit"
 * end = "cog"
 * dict = ["hot" ,"dot" ,"dog" ,"lot" ,"log"]
 * As one shortest transformation is " hit " −> "hot" −> "dot" −> "dog" −> "cog" , return its length 5.
 *
 * Note :
 * Return 0 if there is no such transformation sequence. All words have the same length .
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder {

    /**
     * The naive approach is to iterate through each character and vary that character from 'a' to 'z' and for the
     * new variation vary the next character from 'a' to 'z' until end word is found or we reach end of index. For the
     * example given hit -> cog:
     *  vary h from 'a' to 'z'
     *  for each valid variation (i.e. the word is present in the dictionary):
     *      remove word from dict so we don't get loops
     *      increment secondary index and vary character at that index from 'a' to 'z'
     *
     *  This has a complexity of O(26^n). Character at each index is altered 26 times (i.e. 'a' to 'z'.
     *
     * The dynamic approach would be do a breath first search as characters are varied
     * Algorithm:
     *  add end word to dict
     *  maintain a queue of words to process
     *  add start word to the queue
     *  while queue has elements:
     *      current word -> queue.pop()
     *      for int i = 0 -> current word length:
     *          word array = current word to word array
     *          from char = 'a' to 'z'
     *              if word array[i] == char:
     *                  continue;
     *              else:
     *                  word array[i] = char
     *                  if word array -> to_string equals end string:
     *                      return current word.distance + 1
     *                  if word array -> to_string() is present in dict
     *                      add word array -> to_string() to queue
     *                      remove word from dictionary so as to not process it again
     *
     *  return 0
     *
     *
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public static int[] ladderLength(String start, String end, List<String> dict) {
        /** add end word to the list if not already present */
        if (!dict.contains(end)) {
            dict.add(end);
        }

        Queue<WordNode> processQueue = new LinkedList<>();
        processQueue.add(new WordNode(start, 0));

        int iterations = 0;
        int[] returnArray = new int[2];

        /** process all paths possible */
        while (!processQueue.isEmpty()) {
            WordNode currentWordNode = processQueue.remove();
            for(int i = 0; i < currentWordNode.word.length(); i++) {
                char[] wordChars = currentWordNode.word.toCharArray();
                for(char c = 'a'; c <= 'z'; c++) {
                    iterations++;
                    /** no need to check for this char change if it is the same as the original character */
                    if (wordChars[i] == c) {
                        continue;
                    }
                    /** flip the character at current index to current char c and
                     * check if it forms the end word, if not
                     * check if it forms a word in the dict
                     */
                    wordChars[i] = c;
                    String newString = new String(wordChars);
                    if (end.equals(newString)) {
                        returnArray[0] = currentWordNode.distance + 1;
                        returnArray[1] = iterations;
                    }
                    if (dict.contains(newString)) {
                        processQueue.add(new WordNode(newString, currentWordNode.distance + 1));
                        /** remove word matched from dict so we don't have loops */
                        dict.remove(newString);
                    }
                }
            }
        }

        /** no valid path found */
        returnArray[0] = 0;
        returnArray[1] = iterations;

        return returnArray;
    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("hot");
        dictionary.add("dot");
        dictionary.add("dog");
        dictionary.add("lot");
        dictionary.add("log");

//        dictionary.add("log");
//        dictionary.add("fog");
//        dictionary.add("hog");

        String start = "hit";
        String end = "cog";

        Logger.log("Dictionary words " + dictionary.toString());
        int[] result = ladderLength(start, end, dictionary);
        Logger.log("Distance between "  + start + " and "  + end + " = " + result[0] + ", iterations = " + result[1]);
    }

    private static class WordNode {
        String word;
        int distance;

        public WordNode(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }
}
