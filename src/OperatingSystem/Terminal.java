package OperatingSystem;

import java.util.Scanner;

public class Terminal {

	public static void main(String[] args) {

		int size = 20;

		java.io.File mainDirectory = new java.io.File("FILE_SYSTEM");
		mainDirectory.mkdirs();

		MainMemory mainMemory = new MainMemory(size);
		FAT fat = new FAT(size);
		FileSystem fileSystem = new FileSystem(fat, mainMemory);

		System.out.println("File Operations on root/ directory only");
		System.out.println("---------------------------");
		System.out.println("1. create fileNameWithoutSpaces");
		System.out.println("2. del fileNameWithoutSpaces");
		System.out.println("3. rename fileNameWithoutSpaces");
		System.out.println("4. ls");
		System.out.println("5. read fileNameWithoutSpaces");
		System.out.println("6. write fileName dataToWriteWithoutSpaces");
		System.out.println("7. append fileName dataToWriteWithoutSpaces");
		System.out.println("8. viz");
		System.out.println("---------------------------");

		System.out.print("~root/ $ ");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			switch (line.split(" ")[0]) {
			case "create":
				try {
					File newFile = fileSystem.createFile(line.split(" ")[1]);
					if (newFile == null)
						log("unable to create file; out of memory, or same file name");
					else
						log("created " + newFile.getName());
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "ls":
				String toPrint = "";
				for (File allFiles : fileSystem.root)
					toPrint += allFiles.getName() + " ";
				log(toPrint);
				break;
			case "del":
				try {
					File toDelete = fileSystem.getFileFromName(line.split(" ")[1]);
					fileSystem.deleteFile(toDelete);
					log("deleted " + toDelete.getName());
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "rename":
				try {
					File toRename = fileSystem.getFileFromName(line.split(" ")[1]);
					fileSystem.renameFile(toRename, line.split(" ")[2]);
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "read":
				try {
					File toReadFile = fileSystem.getFileFromName(line.split(" ")[1]);
					log(fileSystem.read(toReadFile));
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "write":
				try {
					if (line.split(" ").length == 3) {
						File toWriteFile = fileSystem.getFileFromName(line.split(" ")[1]);
						fileSystem.write(toWriteFile, line.split(" ")[2]);
						log("written to file " + toWriteFile.getName());
					} else if (line.split(" ").length == 4) {
						File toWriteFile = fileSystem.getFileFromName(line.split(" ")[1]);
						fileSystem.write(toWriteFile, Integer.parseInt(line.split(" ")[2]), line.split(" ")[3]);
						log("written to file " + toWriteFile.getName());
					}
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "append":
				try {
					File toAppendFile = fileSystem.getFileFromName(line.split(" ")[1]);
					fileSystem.append(toAppendFile, line.split(" ")[2]);
					log("Appended to file " + toAppendFile.getName());
				} catch (Exception e) {
					log("syntax error");
					e.printStackTrace();
				}
				break;
			case "viz":
				fat.drawFAT(fileSystem.root);
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