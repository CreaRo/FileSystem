package OperatingSystem;

public class MainMemory {

	public int SIZE_OF_BLOCK = 4;

	private String arrayMain[];

	public MainMemory(int size) {
		arrayMain = new String[size];

		for (int i = 0; i < size; i++)
			arrayMain[i] = generateRandomString();

	}

	public void writeToMemory(int block, int position, String stringToWrite) {
		String content = readFromMemory(block);
		StringBuilder builder = new StringBuilder(content);
		builder.insert(position, stringToWrite);
		arrayMain[block] = builder.substring(0, SIZE_OF_BLOCK);
	}

	public String readFromMemory(int block) {
		return arrayMain[block];
	}

	private String generateRandomString() {
		String word = "";
		// for (int i = 0; i < SIZE_OF_BLOCK; i++) {
		// word += (char) ('A' + new Random().nextInt(26));
		// }
		word = "\0\0\0\0";
		return word;
	}

}