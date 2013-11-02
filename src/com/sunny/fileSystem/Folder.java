package com.sunny.fileSystem;

import java.util.HashSet;
import java.util.Set;

public class Folder implements StorageEntry {
	
	private String name;
	private String path;
	private StorageEntry parent;
	private Set<StorageEntry> children;
	
	public Folder(String name, String path) {
		this.name = name;
		this.path = path;
		children = new HashSet<StorageEntry>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public StorageEntry getParent() {
		return parent;
	}

	public void setParent(StorageEntry parent) {
		this.parent = parent;
	}

	public Set<StorageEntry> getChildren() {
		return children;
	}
}
