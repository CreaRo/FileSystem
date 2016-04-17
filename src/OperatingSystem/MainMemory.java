package OperatingSystem;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class MainMemory {

	private String DIVIDER = "~";

	public int SIZE_OF_BLOCK = 4;

	private String arrayMain[];
	private File file;

	public MainMemory(int size) {
		arrayMain = new String[size];

		for (int i = 0; i < size; i++)
			arrayMain[i] = generateRandomString();

		file = new File("FILE_SYSTEM/persistentMainMemory.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				String content = FileUtils.readFileToString(file);
				for (int i = 0; i < content.split(DIVIDER).length; i++)
					arrayMain[i] = content.split(DIVIDER)[i];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToMemory(int block, int position, String stringToWrite) {
		String content = readFromMemory(block);
		StringBuilder builder = new StringBuilder(content);
		builder.insert(position, stringToWrite);
		arrayMain[block] = builder.substring(0, SIZE_OF_BLOCK);

		builder = new StringBuilder();

		for (String s : arrayMain)
			builder.append(s + DIVIDER);
		builder.deleteCharAt(builder.length() - 1); /* delete last DIVIDER */
		try {
			FileUtils.write(file, builder.toString(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFromMemory(int block) {
		return arrayMain[block];
	}

	private String generateRandomString() {
		String word = "";
		for (int i = 0; i < SIZE_OF_BLOCK; i++) {
			// word += (char) ('A' + new Random().nextInt(26));
			word += "\0";
		}
		return word;
	}

}