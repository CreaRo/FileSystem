package OperatingSystem;

public class File {

	private String name;
	private int size; /* Number of block size */
	private int startingAddress; /* The starting address of the file in our FAT */

	public File(String name, int size, int startingAddress) {
		this.name = name;
		this.size = size;
		this.startingAddress = startingAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartingAddress() {
		return startingAddress;
	}

	public void setStartingAddress(int startingAddress) {
		this.startingAddress = startingAddress;
	}

}
