package com.sunny.common;

public class Logger {
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(String message, Throwable thrown) {
		System.out.print(message);
		thrown.printStackTrace();
	}
	
	public static void log(int i) {
		System.out.println(i);
	}
	
	public static void logArray(char[] array) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i] + ", ");
		}
		System.out.println(sb.toString());
	}
	
	public static void logArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
	}
	
	public static void log2DArray(int[][] array) {
		for(int i = 0; i < array.length; i++) {
			logArray(array[i]);
			System.out.println();
		}
	}
}
