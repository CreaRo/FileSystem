package OperatingSystem;

public class Main {

	public static void main(String arg[]) {

		int size = 20;

		MainMemory mainMemory = new MainMemory(size);
		FAT fat = new FAT(size);
		FileSystem fileSystem = new FileSystem(fat);

		fat.drawFAT();
		File a = fileSystem.create("A");
		fat.drawFAT();
		File b = fileSystem.create("B");
		fat.drawFAT();
		File c = fileSystem.create("C");
		fat.drawFAT();
		fileSystem.delete(b);
		fat.drawFAT();
		fileSystem.delete(a);
		fat.drawFAT();
		fileSystem.delete(c);
		fat.drawFAT();

	}

}
