# Nguyen project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Nguyen_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Nguyen.java` file, right-click it, and choose `Run Nguyen.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    H   H  EEEEE  L      L       OOO        FFFFF  RRRR    OOO   M   M
    H   H  E      L      L      O   O       F      R   R  O   O  MM MM
    HHHHH  EEEE   L      L      O   O       FFFF   RRRR   O   O  M M M
    H   H  E      L      L      O   O       F      R  R   O   O  M   M
    H   H  EEEEE  LLLLL  LLLLL   OOO        F      R   R   OOO   M   M
    
    N   N  GGGG   U   U  Y   Y  EEEEE  N   N
    NN  N  G      U   U   Y Y   E      NN  N
    N N N  G  GG  U   U    Y    EEEE   N N N
    N  NN  G   G  U   U    Y    E      N  NN
    N   N  GGGG   UUUUU    Y    EEEEE  N   N 
   
    PPPPP  RRRR   OOO
    P   P  R   R O   O
    PPPPP  RRRR  O   O
    P      R  R  O   O
    P      R   R  OOO
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
