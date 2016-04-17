package OperatingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class FAT {

	private String DIVIDER = "~";

	private int[] arrayFAT;

	private int FREE = -2;

	private java.io.File file;

	public FAT(int size) {
		arrayFAT = new int[size];
		for (int i = 0; i < arrayFAT.length; i++)
			arrayFAT[i] = FREE;

		file = new java.io.File("FILE_SYSTEM/persistentFAT.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				String content = FileUtils.readFileToString(file);
				for (int i = 0; i < content.split(DIVIDER).length; i++)
					arrayFAT[i] = Integer.parseInt(content.split(DIVIDER)[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/* Get the first free block in our mainMemory */
	public int getFreeBlock() {
		for (int i = 0; i < arrayFAT.length; i++) {
			if (arrayFAT[i] == FREE) {
				return i;
			}
		}
		return -1;
	}

	/* Get the second free block in our mainMemory */
	public int getNextFreeBlock() {
		boolean found = false;
		for (int i = 0; i < arrayFAT.length; i++) {
			if ((arrayFAT[i] == FREE) && (found)) {
				return i;
			} else if (arrayFAT[i] == FREE && (!found)) {
				found = true;
			}
		}
		return -1;
	}

	public void setFree(int position) {
		setElement(position, FREE);
	}

	public void setElement(int position, int pointer) {
		arrayFAT[position] = pointer;

		StringBuilder builder = new StringBuilder();
		for (int s : arrayFAT)
			builder.append(s + DIVIDER);
		builder.deleteCharAt(builder.length() - 1); /* delete last DIVIDER */
		try {
			FileUtils.write(file, builder.toString(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getElement(int position) {
		return arrayFAT[position];
	}

	public int getTotalFreeSpace() {
		int count = 0;
		for (int i = 0; i < arrayFAT.length; i++) {
			if (arrayFAT[i] == FREE)
				count++;
		}
		return count;
	}

	public void drawFAT() {
		for (int i = 0; i < arrayFAT.length; i++) {
			System.out.print("[" + arrayFAT[i] + "] ");
		}
		System.out.println();
	}

	public void drawFAT(ArrayList<File> files) {
		String print[] = new String[arrayFAT.length];
		for (int i = 0; i < print.length; i++)
			print[i] = "-";
		for (File file : files) {
			int next = file.getStartingAddress();
			while (next != -1) {
				print[next] = file.getName();
				next = getElement(next);
			}
		}
		System.out.println(Arrays.toString(print));
	}
}