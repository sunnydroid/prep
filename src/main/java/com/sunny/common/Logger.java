package com.sunny.common;

import java.util.List;

public class Logger {
	
	public static void log(String message) {
		System.out.println(message);
	}

	public static void logSameLine(String message) {
		System.out.print(message);
	}

	public static void log(String message, Throwable thrown) {
		System.out.print(message);
		thrown.printStackTrace();
	}
	
	public static void log(int i) {
		System.out.println(i);
	}

    public static void log(char c) {
        System.out.println(c);
    }
	
	public static void logArray(char[] array) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i] + ", ");
		}
		System.out.println(sb.toString());
	}
	
	public static void logArray(int[] array) {
		if(array == null) {
			log("null array");
            return;
		}
		if(array.length == 0) {
			log("empty array");
            return;
		}
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
	
	public static void logList(List<?> list) {
		for(int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			System.out.print(o.toString() + ", ");
		}
		log("");
	}

    public static void log(List<Integer> integers) {
		for (Integer i : integers) {
			System.out.println(i);
		}
	}

    public static void log(String[] words) {
		for (String word : words) {
			System.out.println(word);
		}
	}

    public static void log(int[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
