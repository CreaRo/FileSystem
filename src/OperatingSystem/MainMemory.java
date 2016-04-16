package OperatingSystem;

import java.util.Random;

public class MainMemory {

	public int SIZE_OF_BLOCK = 4;

	private String arrayMain[];

	public MainMemory(int size) {
		arrayMain = new String[size];

		for (int i = 0; i < size; i++)
			arrayMain[i] = generateRandomString();

	}

	public void writeToMemory(int block, String stringToWrite) {
		arrayMain[block] = stringToWrite;
	}

	public String readFromMemory(int block) {
		return arrayMain[block];
	}

	private String generateRandomString() {
		String word = "";
		for (int i = 0; i < SIZE_OF_BLOCK; i++) {
			word += (char) ('A' + new Random().nextInt(26));
		}
		return word;
	}

}