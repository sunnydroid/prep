package com.sunny.leetcode;

import com.sunny.common.Logger;
import com.sunny.trie.Trie;
import com.sunny.trie.Trie.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a dictionary of words, implement possible word suggestions that begin with the input string.
 */
public class DictionarySuggestions {

    /**
     * Approach:
     *  The best data structure suited for this problem is a Trie. Tries are built to capture the
     *  relationship between words. A Trie node stores a single character and an array of children node that
     *  can be constructed from the current node. For example to store the words: test, tester, testing, phone, phony
     *  and hello, the trie will look like:
     *
     *      h        p       t
     *      e        h       e
     *      l        o       s
     *      l        n       t
     *      o      e   y   e    i
     *                     r    n
     *                          g
     *
     *  Given a prefix, words from children nodes can be built. Each end of word is marked by an end of word flag. We
     *  can recursively traverse all non null children with characters till now and return when we have recursive
     *  explored all paths of the children.
     *
     * Algorithm:
     *  build trie with dictionary words
     *  currentNode = root node of trie
     *  prefix -> ""
     *  for each character in the input string:
     *      childCharacterIndex = character - 'a'
     *      if child node is null at the childCharacterIndex:
     *          // no paths to follow, return empty list
     *          return empty list
     *      append current nodes character to prefix
     *      currentNode = currentNode.children[childCharacterIndex]
     *
     *  recursively build remaining words from currentNode and prefix, see below for recursive
     *
     * @param input
     * @return
     */
    public static List<String> wordSuggestions(String input, String[] dictionaryWords) {
        /** if input is empty or no words in dictionary, return empty list */
        if (input == null || input.isEmpty() || dictionaryWords == null || dictionaryWords.length == 0) {
            return Collections.emptyList();
        }
        /** Create a trie and add all dictionary words */
        Trie trie = new Trie();
        /** add words in the dictionary to the trie */
        for (String word : dictionaryWords) {
            trie.insert(word);
        }

        Node currentNode = trie.getRoot();
        StringBuilder prefixBuilder = new StringBuilder();

        /** check if input string is prefix to any words in the trie */
        for(int i = 0; i < input.length(); i++) {
            int childIndex = input.charAt(i) - 'a';
            if (currentNode.getChildren()[childIndex] == null) {
                /** no suggestions possible for the given input */
                return Collections.emptyList();
            }
            prefixBuilder.append(currentNode.getCharacter());
            currentNode = currentNode.getChildren()[childIndex];
        }



        return getWordsFromPrefix(currentNode, prefixBuilder.toString());
    }

    /**
     * Approach:
     *  Recursively traverse children node till no non children are found. For each node traversed, append the
     *  character at node to the carryover string and build on that.
     *  This is a recursive algorithm which should have the following 3 cases:
     *      1) Base case when to return or stop recursion
     *      2) Each successive iteration should reduce the problem further
     *      3) Results from each sub call should be used to return final result/decision
     *
     * Algorithm:
     *  list of strings constructed -> new ArrayList<String>
     *  list of non null children -> new ArrayList<Node>
     *  for each child node of currentNode:
     *      if child node is not null:
     *          add to list of non null children
     *  // base case
     *  if current node is end of word:
     *      append character at current node to string till now
     *      add string to list of strings constructed
     *
     *  for each non null child:
     *      add to string list (recursive result from call with child node and string till now + character at current node)
     *
     *  return list of strings constructed;
     *
     * @param rootNode
     * @param stringTillNow
     * @return
     */
    public static List<String> getWordsFromPrefix(Node rootNode, String stringTillNow) {
        List<Node> nonNullChildren = new ArrayList<>();
        List<String> wordsFound = new ArrayList<>();

        /** add all non null children to be recursed */
        for (Node node : rootNode.getChildren()) {
            if (node != null) {
                nonNullChildren.add(node);
            }
        }

        /** if end of word, add stringTillNow + character at current node to words found */
        if (rootNode.isFullWord()) {
            wordsFound.add(stringTillNow + rootNode.getCharacter());
        }

        for (Node node : nonNullChildren) {
            wordsFound.addAll(getWordsFromPrefix(node, stringTillNow + rootNode.getCharacter()));
        }

        return wordsFound;
    }

    public static void main(String[] args) {
        String[] dictionary = new String[] {"test", "tester", "testing", "phony", "phone", "hello"};
        String input = "te";
        List<String> suggestions = wordSuggestions("te", dictionary);

        Logger.log("Word suggestions for the input " + input);
        Logger.log(suggestions.toString());
    }

}
