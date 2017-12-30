package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prints all possible words in a dictionary that can be constructed from characters present in an input string
 */
public class WordsPossible {
    /**
     * Approach: word construction is dependent on the number of characters available in the input string. If the
     * minimum number of characters to build word in the dictionary are present in the input string, we can construct
     * the word. Therefore we need a breakdown of the number of characters in the input string and compare it to the
     * breakdown of each word in the dictionary.
     *
     * Algorithm:
     *  word list -> new list
     *  input character map -> map of character to integer
     *  for each character in input:
     *      if character exists in input character map:
     *          increment count by one
     *      else
     *          create new entry for character and set count to 1
     *
     *   for each word in dictionary:
     *      get map of character to integer count
     *      can construct = true
     *      for each character entry:
     *          if entry in input character map is not satisfied:
     *              can construct = false
     *              break
     *      if can construct:
     *          add word to word list
     *
     *  return return list
     * @param input
     * @param dict
     * @return
     */
    public static List<String> getWordsPossible(String input, List<String> dict) {
        List<String> wordsPossible = new ArrayList<>();
        /** get character map of the input */
        Map<Character, Integer> inputCharMap = getCharacterMap(input);

        for (String dictWord : dict) {
            /** get character map of words in the dictionary and see if breakdown of entry is present in the input character
             * map
             */
            Map<Character, Integer> dictWordMap = getCharacterMap(dictWord);
            boolean wordPossible = true;
            for(Map.Entry<Character, Integer> entry : dictWordMap.entrySet()) {
                /** if input character map does not contain key or the number of characters required is higher
                 * that what is available from input map, break and set word possible flag to false.
                 */
                if (!inputCharMap.containsKey(entry.getKey()) || entry.getValue() > inputCharMap.get(entry.getKey())) {
                    wordPossible = false;
                    break;
                }
            }

            if (wordPossible) {
                wordsPossible.add(dictWord);
            }
        }

        return wordsPossible;
    }

    public static Map<Character, Integer> getCharacterMap(String input) {
        Map<Character, Integer> charMap = new HashMap<>();
        for(int i = 0; i < input.length(); i++) {
            if (charMap.containsKey(input.charAt(i))) {
                charMap.put(input.charAt(i), charMap.get(input.charAt(i)) + 1);
            } else {
                charMap.put(input.charAt(i), 1);
            }
        }

        return charMap;
    }

    public static void main(String[] args) {
        String[] dictionary = new String[]{"test", "tester", "testing", "notininput"};
        List<String> wordsPossible = getWordsPossible("testinegr", Arrays.asList(dictionary));
        Logger.log("Words possible are : " + wordsPossible.toString());
    }
}
