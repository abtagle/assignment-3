Welcome to voxSpell- an application developed by Aimee Tagle and Minha Kim for Softeng 206's Assignment 3.

Last Modified: 22 September. 2016

This assignment should run correcly in the standard Linux environment on lab combuters. Before executing the .jar file, ensure that you have the following two files in the same directory as your .jar file
	-wordlist.txt
	-big_buck_bunny_1_minute.avi

wordlist.txt is a file which users may edit in order to adapt spelling lists to their needs. It needs to follow the format, where each level begins with a title "%Level [number]", followed by a list of words for that level. Levels should have at least 10 words to be suitable (as 9/10 is the score required to level up).

The .jar file should be able to read them from this location. Execute the .jar file  by double clicking- you should have executable premissions.

===================================================================

To recreate this project in Eclipse, you will need to add all the libraries (.jar files) in the "libs" folder provided in the submissions (Project>Properties>Java Build Path>Libraries>Add External JARs). In addition, the files in the Media folder provided must be added as resources (Project>Properties>Java Build Path>Source>Add Folder- then add the files to the folder you create). The media folder contains additional media used to help our GUI be more visually appealing.

Our method of reading in files makes it easy for users to provide their own words in a file when executing via jar file. However, that method is not effective in Eclipse, so the original wordlist and a copy of the video required must be referenced when running the project from Eclipse.
