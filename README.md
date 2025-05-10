# <span style="color:red">Kaji</span> :fire:

![Mochi On Fire](src/main/resources/images/DaMochiOnFire.png)
This project is a greenfield Java application inspired by Duke, the Java mascot. 
The project template was provided as a base, and further inspiration was drawn from <span style="color:red">Kaji</span> 
(which means "passion" in Japanese). The goal is to manage and complete tasks with enthusiasm and dedication.
## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click ```File``` > ```Close Project``` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click ```Open```.
   2. Select the project directory, and click ```OK```.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use JDK 17 (not other versions) as explained in here.
   In the same dialog, set the Project language level field to the ```SDK default``` option.
4. After that, locate the ```src/main/java/kaji/Launcher.java``` file, right-click it, and choose ```Run Launcher.main()``` (if the code editor is showing compile errors, try restarting the IDE).
   
Warning: Keep the ```src\main\java``` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

