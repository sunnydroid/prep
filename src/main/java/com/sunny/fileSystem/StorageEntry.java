package com.sunny.fileSystem;

/**
 * Use the composite design pattern to treat files and folders in a similar manner. 
 * This should facilitate in computations on top level folders that need to trickle
 * down to children 
 * 
 * @author sunnshah
 *
 */
public interface StorageEntry {
	
	public String getPath();
	public StorageEntry getParent();
	public void setParent(StorageEntry parent);

}
