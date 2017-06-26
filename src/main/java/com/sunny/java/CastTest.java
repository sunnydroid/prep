package com.sunny.java;

/**
 * Created by sunshah on 8/10/16.
 */
public class CastTest {

    public static void main(String[] args) {
        testCastNPE();
        System.out.println("No NPE");
    }

    public static void testCastNPE() {
        Object string = null;
        /* does cast return NPE test */

        String castString = (String) string;
    }
}
