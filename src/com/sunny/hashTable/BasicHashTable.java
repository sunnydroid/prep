package com.sunny.hashTable;

import com.sunny.common.Logger;

public class BasicHashTable {

	String[] hashTable;
	
	public BasicHashTable(int size) {
		this.hashTable = new String[size];
	}
	
	public static void main(String[] args) {
		BasicHashTable hashTable = new BasicHashTable(10);
		hashTable.put("key1", "value1");
		hashTable.put("key1", "value1");
		hashTable.put("key4", "value2");
		
		hashTable.get("key1");
		hashTable.get("key2");

	}
	
	public void put(String key, String value) {
		int hash = simpleHash(key);
		int tableIndex = hash % hashTable.length;
		if(hashTable[tableIndex] != null) {
			Logger.log("Collision: Index " + tableIndex + " already has value " + hashTable[tableIndex] +
					". cannot insert " + value);
		} else {
			hashTable[tableIndex] = value;
			Logger.log("Inserted value " + value + " at index " + tableIndex);
		}
	}
	
	public String get(String key) {
		String value = null;
		int hash = simpleHash(key);
		int index = hash % hashTable.length;
		if(hashTable[index] == null) {
			Logger.log("Key '" + key + "' not found. Empty slot at index " + index );
		} else {
			value = hashTable[index];
			Logger.log("Value : " + value + " found for key '" + key + "'");
		}
		
		return value;
	}
	
	private int simpleHash(String key) {
		/* simple hash function 
		 * weighted sum of string values % table size
		 */
		int hash = 7;
		for(int i = 0; i < key.length(); i++) {
			hash = hash * 31 + key.charAt(i);
		}
		
		return hash;
	}
}
