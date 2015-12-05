package com.sunny.trie;

import java.util.HashMap;
import java.util.Map;


public class Trie {

    /* Used to find index of node */
    private static final int ASCII_A = 97;
    private Node root;

    public Trie() {
        root = initializeTrie();
    }

    public void insert(Node root, String word) {
        Node currentNode = root;
        /* Iterate through the length of the word */
        for(int i = 0; i < word.length(); i++){
            /* calculate index at which word should be inserted */
            int index = word.charAt(i) - ASCII_A;
            Node currentChild = currentNode.links.get(index);


            if(currentChild == null) {
               currentChild = new Node(word.charAt(i));
            }
            /* if currentChild is not null, the char at current index already exists */
            currentNode = currentChild;
        }
        /* last node should be marked as a complete word */
        currentNode.fullWord = true;
    }

    public boolean find(String word) {
        if(word == null || word.isEmpty()) {
            return false;
        }

        return false;
    }
    
    private Node initializeTrie() {
        /* Root node is set to null */
        Node root = null;
        return root;
    }
    
    private static class Node {
        char character;
        Map<Character, Node> links;
        boolean fullWord;
        
        public Node(char character) {
            this.character = character;
            links = new HashMap<>();
            this.fullWord = false;
        }
    }

    public static void main(String[] args) {
        Trie testTrie = new Trie();
    }

}
