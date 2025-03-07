# Daiyan User Guide

## What is Daiyan

Daiyan is a beautiful character from the franchise Girl's Frontline. Now coded as your personal task organiser secretary!.
She can help add different types tasks, log their completion and sort them for you, etc...
Whats better, this light-weight app runs purely on CLI, with added GUI for aesthetic.

## Start

1. **Ensure you have Java 17 or above installed** on your computer.
    - **Mac users**: Ensure you have the exact JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
    - Windows & Linux users: Download Java from
   [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
   or install it using your package manager.

2. **Download the latest `.jar` file** from [here](https://github.com/lyhthaddeus/ip/releases).

3. **Copy the file** to the folder you want to use as the home folder for your Chatty bot.

4. **Open a command terminal**:
    - `cd` into the folder where you placed the `Daiyan.jar` file.

5. **Run the application** using the following command:
   ```java -jar Daiyan.jar```

6. The GUI should appear in a few seconds similar to the picture below.
   ![Screenshot of the GUI](https://github.com/lyhthaddeus/ip/blob/master/docs/Ui.png)

7. Type your commands into the text field and press Send to execute them. 
## Available Commands.

### 1. `todo [task]`
Adds a todo task to the list.  
**Example**: `todo Gun Cleaning`

### 2. `deadline [task] /by [dd/mm/yyyy hhmm]`
Adds a task with a due date.  
**Example**: `deadline Restock Ammo /by 20/02/2025 2359`

### 3. `event [task] /from [start date/time] /to [end date/time]`
Adds an event with a start and end date.  
**Example**: `event Operation RavenFall /from 05/03/2025 1000 /to 06/03/2025 1100`

### 4. `list` or `ls`
Displays all tasks.  
**Example**: `list`

### 5. `mark [task number]`
Marks a task (located by id) as completed.  
**Example**: `mark 2`

### 6. `unmark [task number]`
Marks a task (located by id) as incompleted.  
**Example**: `unmark 2`

### 7. `find [keyword]`
Finds all tasks containing the specified keyword (case-sensitive) in the description.  
**Example**: `find Gun`

### 8. `delete [task number]`
Deletes a selected task.  
**Example**: `delete 3`

### 9. `sort`
Sorts all the task according to time.  
**Example**: `sort`

### 10. `bye` or `q`
Exits the application.  
**Example**: `bye`


## Command Summary

| Command                                                    | Description                                           |
|------------------------------------------------------------|-------------------------------------------------------|
| `todo [task]`                                              | Adds a new task without a deadline.                   |
| `deadline [task] /by [dd/mm/yyyy hhmm]`                    | Adds a task with a specific deadline.                 |
| `event [task] /from [start date/time] /to [end date/time]` | Adds an event with a specific date.                   |
| `list`                                                     | Displays all tasks.                                  |
| `mark [task number]`                                       | Marks a specific task as completed.                  |
| `unmark [task number]`                                     | Marks a specific task as not completed.              |
| `delete [task number]`                                     | Deletes a specific task.                             |
| `sort`                                                     | Sorts time according to time.                        |
| `find [keyword]`                                           | Finds all tasks containing the specified keyword.    |
| `bye`                                                      | Exits the application.                               |

## Editing the data file

Data are saved automatically as a txt file [JAR file location]/data/storage.txt. Advanced users are welcome to update data directly by editing that data file.
> [!Caution]
> If your changes to the data file makes its format invalid, Daiyan will throw an Error Message. Hence, it is recommended to take a backup of the file before editing it.
> Furthermore, certain edits can cause the Daiyan to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly. 

## FAQ
Q: How do I transfer my data to another Computer? </br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Daiyan file.
