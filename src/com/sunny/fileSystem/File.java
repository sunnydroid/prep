package com.sunny.fileSystem;

public class File implements StorageEntry {
	
	private String name;
	private String path;
	private StorageEntry parent;
	private StringBuilder data;
	
	public File(String name, String path) {
		this.name = name;
		this.path = path;
		data = new StringBuilder();
	}
	
	public void appendData(byte[] data) {
		synchronized(this) {
			this.data.append(data);
		}
	}
	
	public String getPath() {
		return path;
	}
	
	public StorageEntry getParent() {
		return parent;
	}

	public void setParent(StorageEntry parent) {
		this.parent = parent;
	}
}
