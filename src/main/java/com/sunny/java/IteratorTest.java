package com.sunny.java;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sunshah on 8/8/16.
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<Integer> testList =new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        Iterator<Integer> iterator = testList.iterator();

        while(iterator.hasNext()) {
            Integer value = iterator.next();
            Logger.log("Current integer:");
            Logger.log(value);
            Logger.log("");

            if(value == 3) {
                iterator.remove();
                Logger.log("removed element : " + value);
            }
        }

        for(Integer i : testList) {
            Logger.log("Value present: " + i);
        }
    }
}
