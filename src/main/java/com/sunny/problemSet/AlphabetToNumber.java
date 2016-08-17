package com.sunny.problemSet;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sunny.common.Logger;

/**
 * Given a string, it gives a number equivalent based on a mapping
 * @author sunshah
 *
 */
public class AlphabetToNumber {
    
    private Map<Character, Integer> alphabetNumberMap;
    
    public AlphabetToNumber() {
        buildMap();
    }

    public static void main(String[] args) {
        AlphabetToNumber aton = new AlphabetToNumber();
//        aton.printMap();
        Logger.log(aton.getStringSum("attitude"));
    }
    
    private void buildMap() {
        alphabetNumberMap = new LinkedHashMap<>();
        char curnChar = 'a';
        for(int i = 1; i < 27; i++) {
            alphabetNumberMap.put(curnChar, i);
            curnChar++;
        }
    }
    
    public int getStringSum(String input) {
        int sum = 0;
        char[] inputArray = input.toCharArray();
        for(int i = 0; i < inputArray.length; i++) {
            sum += alphabetNumberMap.get(inputArray[i]);
        }
        
        return sum;
    }
    
    public void printMap() {
        for(Entry<Character, Integer> entry : alphabetNumberMap.entrySet()) {
            Logger.log(entry.getKey().toString() + "=> " + entry.getValue().toString());
        }
    }
}
