package com.sunny.stringArrays.test;

import com.sunny.stringsArrays.UniqueChars;

import junit.framework.TestCase;

public class ArraysStringsTest extends TestCase {

	public void testUniqueChars() {
		assertEquals(true, UniqueChars.hasUniqueChars("abcde"));
		assertFalse(UniqueChars.hasUniqueChars("abcdee"));
	}
	
	public void testUniqueCharsBruteForce() {
		assertEquals(true, UniqueChars.hasUniqueCharsBruteForce("abcdef"));
		assertFalse(UniqueChars.hasUniqueCharsBruteForce("abcdefaghi"));
	}
}
