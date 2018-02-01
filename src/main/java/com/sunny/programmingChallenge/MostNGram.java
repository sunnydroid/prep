package com.sunny.programmingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 *
 * From long block of text -> most common n-gram
 *
 * Given:
 * text = "She sells seashells down by the seashore. She sells, she sells he sells"
 * n = 2
 *
 * Print (n-gram + count)
 * "she sells", 3
 */
public class MostNGram {

    public static void mostNgram(String input, int nGramLength) {

        // Validate input for null, exception can be thrown or simple print message and exit, depending on requirements
        if (input == null || input.isEmpty() || nGramLength < 1) {
            System.out.println("Invalid input");
            return;
        }
        // Remove punctuation using regex. Regex says, replace newline, CR with space and
        // remove anything that is not a letter or space. This will not be able to tokenize numbers
        input = input.replaceAll("[\\r\\n]", " ");
        input = input.replaceAll("[^a-zA-Z\\s]", "");
        // Convert to lower case
        input = input.toLowerCase();
        // Tokenize using space. There could be more than one space, this regex handles that as well
        String[] tokens = input.split("\\s+");
        // if number of tokens is less than nGramLength, can't construct nGram
        if (nGramLength > tokens.length) {
            System.out.printf("Not enough tokens to construct nGrams");
            return;
        }
        // Map to hold count of how many times nGrams appear in the input
        Map<String, Integer> nGramCountMap = new HashMap<>();

        // iterate through each token as starting location and construct nGram of the specified length
        for(int i = 0; i < tokens.length; i++) {
            StringJoiner currentNgramBuilder = new StringJoiner(" ");
            currentNgramBuilder.add(tokens[i]);
            // begin building from the next token
            int j = i+1;
            int tokensAdded = 0;
            // check to ensure that array index accessed by j is not out of bounds
            while(tokensAdded < nGramLength - 1 && j < tokens.length) {
                currentNgramBuilder.add(tokens[j]);
                j++;
                tokensAdded++;
            }

            // Store ngram constructed in map. Increment count if string already exists
            String nGram = currentNgramBuilder.toString();
            if (nGramCountMap.containsKey(nGram)){
                int updatedNGramCount = nGramCountMap.get(nGram) + 1;
                nGramCountMap.put(nGram, updatedNGramCount);
            } else {
                nGramCountMap.put(nGram, 1);
            }
        }

        // iterate through the map and find nGram with most count
        int maxCount = 0;
        String maxCountNgram = "";
        for (Map.Entry<String, Integer> entry : nGramCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountNgram = entry.getKey();
            }
        }

        System.out.println("nGram with most count is " + maxCountNgram + " with count = " + maxCount);
    }

    public static void main(String[] args) {
        String testString = "She sells seashells down by the seashore. \n \r She sells, she sells he sells";
//        String testString = "";
//        String testString = "                  ";
//        String testString = "111 222 333 555 111 222 333 555 22324 23424";
//        String testString = "#%$% #$%# #%$^$% #$%#";
        mostNgram(testString, 2);
//        mostNgram(testString, 100);
//        mostNgram(testString, 0);
//        mostNgram(testString, -1);
    }
}
