package com.sunny.leetcode;

import com.sunny.common.Logger;
import com.sunny.trie.Trie;

import java.util.*;

/**
 * Write a program to find the longest word made of other words
 * Input: test, tester, testertest, testing, testingtester
 * Output (longest word) = testingtester
 *
 * Approach:
 *  Since the longest word would have to be made up of shorter words,
 *  we can sort the words in increasing length and them to a trie.
 *
 *  A trie would be the most suitable data structure since it builds up on
 *  words that are already present.
 *
 *  Searching for the longest joint word can be determined by BFS or
 *  by repeatedly searching for substrings of the words in the input array.
 *
 *  The substring approach is search intensive but tries are designed to
 *  for quick search.
 *
 *  BFS search requires more memory as the function calls are recursive.
 *
 * Algorithm:
 *  Sort words in decreasing length
 *  Add words to trie
 *
 *  Algorithm repeated search:
 *      longest word = first word
 *      for word inserted in trie:
 *          for int i = 0 -> word.length
 *              if word.substring(0, i) and word.substring(i+1, word.length - 1) are both in trie
 *                  current word -> longest word
 *
 *      return longest word
 *
 *  Algorithm DFS search:
 *      params -> node, string till now
 *      if node is end of word AND no non null children:
 *          return string till now
 *      add current node to stack
 *          while stack is not empty:
 *              string till now = string till now + character
 *              longest string = string till now
 *              for each non null node:
 *                  string = recurse with node and longest string till now
 *                  if string > longest string:
 *                      longest string = string
 *
 *      return longest string
 *
 */
public class LongestJoinedWord {

    public static void main(String[] args) {
        String[] words = {"test", "tester", "testertest", "testing", "testingtester"};
        SortStringByLength sortStringByLength = new SortStringByLength();
        Arrays.sort(words, sortStringByLength);
        Logger.log(words);

        /** create a new trie and words in length sorted order */
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Logger.log(trie.toString());
        Logger.log("Longest string in trie using recursion= " + findLongestStringInTrieRecursive(trie.getRoot(), ""));
        Logger.log("Longest string in trie using polling = " + findLongestStringInTriePolling(trie, words));
    }

    /**
     * Find the longest string by using recursion on non null children and
     * bubbling the longest combination.
     * ** NOTE: This only returns the longest string, not longest string made up of other words
     *
     * Algorithm:
     *  determine all non null children
     *  if current node is end of word and all children are null:
     *      add character at current node with string till now and return
     *  longest string = string till now
     *  for all children:
     *      if child is not null:
     *          longest string from child = findLongestStringInTrieRecursive(child, stringTillNow+char)
     *          if longest string from child is longer than longest string:
     *              longest string = longest string from child
     *
     *  return longest string
     *
     * @param currentNode node from which to determine longest string
     * @param stringTillNow characters accumulated till now
     * @return longest string
     */
    public static String findLongestStringInTrieRecursive(Trie.Node currentNode, String stringTillNow) {
        List<Trie.Node> nonNullChildren = new ArrayList<>();

        for (Trie.Node child : currentNode.getChildren()) {
            if (child != null) {
                nonNullChildren.add(child);
            }
        }
        /** if current node is end of word and no non-null children, return string till now + character */
        if (currentNode.isFullWord() && nonNullChildren.isEmpty()) {
            return stringTillNow + currentNode.getCharacter();
        }

        String longestStringTillNow = stringTillNow;
        /** for all non null children node, add character at node and recurse to find longest string from children */
        for (Trie.Node node : nonNullChildren) {
            String longestStringFromChild = findLongestStringInTrieRecursive(node, stringTillNow + currentNode.getCharacter());
            if (longestStringFromChild.length() > longestStringTillNow.length()) {
                longestStringTillNow = longestStringFromChild;
            }
        }

        return longestStringTillNow;
    }

    /**
     * This method uses repeated search for sub strings of each word to determine if it is present in the trie.
     * It returns longest word made up of words existing in the trie else empty string
     * NOTE this will not detect longer words made up of 3 or more smaller words. Only work for 2 joined words
     * Algorithm:
     *  for each string in the length sorted array starting with longest string
     *      for int i = 0 to string length
     *          if string.substring(0, i) and string.substring(i+1, string.length() - 1) are present
     *              return current string as longest string
     *
     * return empty string
     * @param trie
     * @param sortedWords
     * @return
     */
    public static String findLongestStringInTriePolling(Trie trie, String[] sortedWords) {
        /** Start with the longest word first. The hypothesis is that the longest word is
         * a combination of existing smaller words
         */
        String longestWord = "";
        for(int i = sortedWords.length - 1; i >= 0; i--) {
            String currentWord = sortedWords[i];
            for(int j = 0; j < currentWord.length(); j++) {
                if (trie.find(currentWord.substring(0, j)) && trie.find(currentWord.substring(j + 1, currentWord.length() - 1))) {
                    return currentWord;
                }
            }
        }

        return longestWord;
    }

    public static class SortStringByLength implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }
}
