package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class ConcatenationOfAllWords {

    public static List<Integer> findSubstringIndexes(String input, String[] words) {
        List<Integer> indexes = new ArrayList<>();
        if(words == null || words.length == 0) {
            return indexes;
        }
        int wordLength = words[0].length();

        for(int i = 0; i < input.length();i++) {
            // need to create a new instance of wordset for each search
            List<String> wordsSet = new ArrayList<>(Arrays.asList(words));
            // check if matching word is found from every index
            if(allWordsInString(input.substring(i), wordsSet, wordLength)) {
                Logger.log("Found substring index at " + i);
                indexes.add(i);
            }
        }

        return indexes;

    }

    public static boolean allWordsInString(String input, List<String> words, int wordLength){
        // base case all words in the words set have been found
        if(words.isEmpty()) {
            return true;
        }

        // we ran out of string but there are words in the word set that need matching
        if(input.isEmpty() || input.length() < wordLength) {
            return false;
        }

        Logger.log("search for " + input.substring(0, wordLength));

        if(words.contains(input.substring(0, wordLength))) {
            // remove word currently found in word set and recurse
            Logger.log("removing " + input.substring(0, wordLength) + " from word set");
            words.remove(input.substring(0, wordLength));
            // shorten input by wordLength
            input = input.substring(wordLength);
            return allWordsInString(input, words, wordLength);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        String input = "barfoothefoobarman";
//        String[] words = new String[]{"foo", "bar"};

//        String input = "wordgoodgoodgoodbestword";
//        String[] words = new String[] {"word","good","best","good"};
        String input = "a";
        String[] words = new String[] {"a"};

        Logger.log(findSubstringIndexes(input, words).toString());
    }
}
