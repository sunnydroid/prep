package com.sunny.java;

import com.sunny.common.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sunshah on 8/18/16.
 */
public class LoopTest {

    public static void main(String[] args) {
       testWhileWithIf();
    }

    public static void testWhileWithIf() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6};

        List<Integer> numbersList = Arrays.asList(numbers);

        int count = 0;

        while (numbersList.iterator().hasNext()) {
            if(count >= 3) {
                break;
            }
            Integer num = numbersList.iterator().next();
            count++;
            Logger.log("current value = " + num);
        }
    }
}
