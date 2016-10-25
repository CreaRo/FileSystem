# FileSystem 
--- 
A project for my Operating Systems course in 6th semester. Created two modes to interact with our file system implementation. 

**Terminal/CLI** - Where you can type commands to operate on the file system. Running the code will give you the specific commands that can be run.

*Note*, the data is saved in persistence. Hence, even after stopping the code, your files will still be available. 

**FAT Visualizer** - An iteration of 1000 random create/delete commands to show you how our FAT table performs and how the linked list structure is implemented.

**List of available commands.** 
File Operations on root/ directory only.

1. create fileNameWithoutSpaces  
2. del fileNameWithoutSpaces  
3. rename fileNameWithoutSpaces  
4. ls  
5. read fileNameWithoutSpaces  
6. write fileName dataToWriteWithoutSpaces  
7. append fileName dataToWriteWithoutSpaces  
8. viz - displays the current state of the FAT in the form of a table.

---------------------------  
