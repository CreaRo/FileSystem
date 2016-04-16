package OperatingSystem;

public class FileSystem {

	int minimumFileSize = 4;

	private FAT fat;

	public FileSystem(FAT fat) {
		this.fat = fat;
	}

	public File create(String fileName) {
		return create(fileName, minimumFileSize);
	}

	public File create(String fileName, int size) {

		/* If there isn't enough space, return false, else allocate */
		if (!(fat.getTotalFreeSpace() >= size))
			return null;

		/*
		 * Traverse to all but last. For the last, store lastAddress and set it
		 * to -1.
		 */
		File newFile = new File(fileName, size, -1);
		int startingAddress = -1;
		int lastAddress = -1;
		for (int i = 0; i <= size - 1; i++) {
			int currentAddress = fat.getFreeBlock();
			int nextAddress = fat.getNextFreeBlock();
			fat.setElement(currentAddress, nextAddress);

			if (i == 0) {
				startingAddress = currentAddress;
			}
			if (i == (size - 2)) {
				lastAddress = nextAddress;
			}
		}
		fat.setElement(lastAddress, -1);
		newFile.setStartingAddress(startingAddress);

		return newFile;
	}

	public void delete(File file) {
		int currentAddress = file.getStartingAddress();
		int nextAddress = file.getStartingAddress();
		for (int i = 0; i < file.getSize(); i++) {
			nextAddress = fat.getElement(nextAddress);
			fat.setFree(currentAddress);
			currentAddress = nextAddress;
		}
	}
}