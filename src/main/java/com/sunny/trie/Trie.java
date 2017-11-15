package com.sunny.trie;

import com.sunny.common.Logger;

import java.util.*;


public class Trie {

    /**
     * total possible number of children for each node
     */
    private static final int ALPHABET_SIZE = 26;
    /**
     * a is inserted at index 0, b at 1 and so on
     * ASCII_A - 'char' = index at which to insert character
     */
    private static final int ASCII_A = 97;
    private Node root;

    public Trie() {
        root = new Node('0');
    }

    /**
     * Insert a word in the trie. Create new children nodes as needed
     * Algorithm:
     *  current node -> root
     *  for each character in word:
     *      index of child -> 'a' - 'current character'
     *      if child node at index is null:
     *          create new node with current character there
     *      current node -> child node
     *
     *  mark end of word for current node, i.e. the last node
     *
     * @param word word to insert
     */
    public void insert(String word) {
        Node currentNode = root;
        /**
         *  Iterate through the length of the word and create children nodes
         *  where required
         */
        for(int i = 0; i < word.length(); i++){
            /** calculate index at which character should be inserted */
            int index = word.charAt(i) - ASCII_A;
            Node currentChild = currentNode.children[index];

            if(currentChild == null) {
                Node newChildNode = new Node(word.charAt(i));
                currentNode.children[index] = newChildNode;
                currentNode = newChildNode;
            } else {
                /** if currentChild is not null, the char at current index already exists */
                currentNode = currentChild;
            }
        }
        /** last node should be marked as a complete word */
        currentNode.fullWord = true;
    }

    /**
     * Searches for a word in the trie
     * Algorithm:
     *  current node -> root
     *  for each character in word
     *      index -> 'a' - current character
     *      if children array does not contain node at that index:
     *          return false, word not present
     *      current node -> children array [index]
     *  if last node is not marked as end of word:
     *      return false
     *  return true
     *
     * @param word  word to search for
     * @return true if present, false otherwise
     */
    public boolean find(String word) {
        if(word == null || word.isEmpty()) {
            return false;
        }

        Node currentNode = root;
        for(int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            int index = currentChar - 'a';
            /** if child node at current char is not present, word is not in trie */
            if (currentNode.children[index] == null) {
                return false;
            }
            /** otherwise traverse down children of the character present */
            currentNode = currentNode.children[index];
        }
        /** if last node is not end of word, word is not in trie */
        if (currentNode.fullWord) {
            return true;
        }

        return false;
    }

//    public static void main(String[] args) {
//        Trie trie = new Trie();
//        trie.insert("test");
//        trie.insert("tester");
//        trie.insert("new");
//        trie.insert("newton");
//        System.out.println(trie.toString());
//        String testString = "test";
//        String testString = "tes";
//        Logger.log("word " + testString + " present in trie ? " + trie.find(testString));
//    }


    public static class Node {
        char character;
        Node[] children;

        boolean fullWord;
        public Node(char character) {
            this.character = character;
            this.fullWord = false;
            children = new Node[ALPHABET_SIZE];
            /**
             * initialize all children node to null
             */
            for (Node node : children) {
                node = null;
            }
        }

        public char getCharacter() {
            return character;
        }

        public Node[] getChildren() {
            return children;
        }

        public boolean isFullWord() {
            return fullWord;
        }
    }

    /**
     * Use BFS algorithm to add nodes that are not null to a stack.
     * Pop stack and print node value. If end of word node is reached, print special character
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.empty()) {
            /**
             * Pop node off stack, append value to string builder.
             * Also add special character '\' to indicate end of word node
             */
            Node currentNode = stack.pop();
            stringBuilder.append(currentNode.character);
            if (currentNode.fullWord) {
                stringBuilder.append("\\");
            }
            /** iterate through the children and add non null to the stack */
            boolean allChildrenNull = true;
            for (Node childNode : currentNode.children) {
                if (childNode != null) {
                    stack.push(childNode);
                    allChildrenNull = false;
                }
            }
            if (allChildrenNull) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public Node getRoot() {
        return root;
    }
}
