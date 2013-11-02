package com.sunny.fileSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryFileSystem {

	private static final String SEPERATOR ="/";
	private static final String ERROR_ENTRY_EXISTS = "File with name already exists";
	
	private Map<String, StorageEntry> memDisk;
	private StorageEntry root;
	
	public MemoryFileSystem() {
		/* default load factor of 0.75 */
		memDisk = new ConcurrentHashMap<String,StorageEntry>();
		root = new Folder("root", "root");
	}
	
	public StorageEntry getRoot() {
		return root;
	}
	
	public void createFolder(StorageEntry folder, String name) throws MemoryFileSystemException {
		synchronized(folder) {
			String path = folder + MemoryFileSystem.SEPERATOR + name;
			if (memDisk.get(path) != null) {
				throw new MemoryFileSystemException(MemoryFileSystem.ERROR_ENTRY_EXISTS);
			}
			
			StorageEntry newFolder = new Folder(name, path);
			newFolder.setParent(folder);
		}
	}
	
	public void createFile(StorageEntry folder, String name) {
		
	}
	
	public void open(String name) {
		
	}
	
	public void closeFile(String name) {
		
	}
}
