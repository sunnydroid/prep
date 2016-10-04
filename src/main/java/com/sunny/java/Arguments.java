package com.sunny.java;

import com.sunny.common.Logger;

/**
 * Created by sunshah on 8/23/16.
 */
public class Arguments {

    public static void main(String[] args) {
        Integer num = new Integer(5);
        String str = "Hey dot params";
        int primitiveNum = 2;

        testDotArgs(num, str, primitiveNum);
    }

    public static void testDotArgs(Object... params) {
        for (Object object : params) {
            Logger.log(object.toString());
        }
    }
}
