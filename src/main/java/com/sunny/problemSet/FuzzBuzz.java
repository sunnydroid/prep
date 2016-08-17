package com.sunny.problemSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunshah on 10/24/14.
 */
public class FuzzBuzz {

    /**
     * If numbers incrementing from start to end (inclusive) are divisible by
     * the key in map, get the corresponding value and append it to the list.
     * @param keyMap
     * @param start
     * @param end
     * @return
     */
    public List<String> findFactors(Map<Integer, String> keyMap, int start, int end) {
        List<String> factorStrings = new ArrayList<>();
        if(keyMap == null || keyMap.isEmpty() || start > end) {
            return factorStrings;
        }

        for(int i = start; i <= end; i++) {
            for(Map.Entry<Integer, String> entry: keyMap.entrySet()) {
                if(entry.getKey() == null || entry.getKey() == 0) {
                    continue;
                }

                if( i % entry.getKey() == 0) {
                    factorStrings.add(keyMap.get(entry.getKey()));
                }
            }
        }

        return factorStrings;
    }

    public static void main(String[] args) {

    }
}
