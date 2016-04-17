package OperatingSystem;

import java.util.Scanner;

public class Terminal {

	public static void main(String[] args) {

		int size = 20;

		MainMemory mainMemory = new MainMemory(size);
		FAT fat = new FAT(size);
		FileSystem fileSystem = new FileSystem(fat, mainMemory);

		System.out.println("File Operations on root/ directory");
		System.out.println("create fileName");
		System.out.println("del fileName");
		System.out.println("rename fileName");
		System.out.println("ls");

		System.out.print("~root/ $ ");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			switch (line.split(" ")[0]) {
			case "create":
				File newFile = fileSystem.createFile(line.split(" ")[1]);
				log("created " + newFile.getName());
				break;
			case "ls":
				String toPrint = "";
				for (File allFiles : fileSystem.root)
					toPrint += allFiles.getName() + " ";
				log(toPrint);
				break;
			case "del":
				File toDelete = fileSystem.getFileFromName(line.split(" ")[1]);
				fileSystem.deleteFile(toDelete);
				log("deleted " + toDelete.getName());
				break;
			case "rename":
				break;
			case "read":
				File toReadFile = fileSystem.getFileFromName(line.split(" ")[1]);
				log(fileSystem.read(toReadFile));
				break;
			case "write":
				if (line.split(" ").length == 3) {
					File toWriteFile = fileSystem.getFileFromName(line.split(" ")[1]);
					fileSystem.write(toWriteFile, line.split(" ")[2]);
					log("written to file " + toWriteFile.getName());
				} else if (line.split(" ").length == 4) {
					File toWriteFile = fileSystem.getFileFromName(line.split(" ")[1]);
					fileSystem.write(toWriteFile, Integer.parseInt(line.split(" ")[2]), line.split(" ")[3]);
					log("written to file " + toWriteFile.getName());
				}
				break;
			case "append":
				File toAppendFile = fileSystem.getFileFromName(line.split(" ")[1]);
				fileSystem.append(toAppendFile, line.split(" ")[2]);
				log("Appended to file " + toAppendFile.getName());
				break;
			default:
				System.out.println("wrong command mate");
			}

			System.out.println();
			System.out.print("~root/ $ ");
		}

	}

	public static void log(String text) {
		System.out.print("\t " + text);
	}

}