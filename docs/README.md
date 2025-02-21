# 🐱 Ekko - Your Personal Task Manager

(...and emotional support cat <3 Try typing in meow! )

Ekko is a simple, interactive task manager built with JavaFX. It helps users keep track of tasks, deadlines, and notes efficiently.

> To ekko the black cat, you're an orange, which ekko mistook for one of his orange wool balls. 
"Those balls don't tend to be too intellectual, " and ekko is a helpful cat, "guess I would
help them remember stuff...only if they ask first! "

Notice all commands are capitalised? Don't worry, ekko is considerate of your intelligence! All commands are made __*case-insensitive*__, 
so you can type in anyhow you want! 

## Features

### ⌨️ Get In-App User Manual by hitting __*ENTER*__

- Just send in a blank message, and Ekko would respond you with a guide!

### 📝 Task Management
- **TODO**: Add a general task.
  ```
  TODO <description>
  e.g. todo running
  ```
- **DEADLINE**: Add a task with a due date and time. For deadlines, **_time is optional_**, and its omission would result 
in the time being automatically set to **_2359_**.
  ```
  DEADLINE <description> /by DD/MM/YYYY (HH:MM)
  e.g. deadline homework /by 10/02/2025
    or deadline homework /by 10/02/2025 23:59
  ```
- **EVENT**: Add an event with a start and end time. For events, time is compulsory, 
but you may omit the minute if it's a whole hour. 
  ```
  EVENT <description> /from DD/MM/YYYY HH(:MM) /to DD/MM/YYYY HH(:MM)
  e.g. event dinner /from 10/09/2025 12 /to 10/09/2025 13
  ```

### ✅ Task Updates
- **Mark Task as Done**:
  ```
  MARK <index>
  e.g. mark 1
  ```
- **Unmark Task as Not Done**:
  ```
  UNMARK <index>
  e.g. unmark 1
  ```

### 🔍 Searching & Listing
- **Find Tasks by Keyword**:
  ```
  FIND <keyword>
  e.g. find reading
  ```
- **List All Tasks and Notes**:
  ```
  LIST
  ```

### 🗑️ Deletion
- **Delete a Task**:
  ```
  DELETE <index>
  e.g. delete 0
  ```

### 📝 Notes
- **Add a Note**:
  ```
  NOTE /t <title> /d <description>
  e.g. note /t height /d 185cm
  ```
- **Remove a Note**:
  ```
  RMNOTE <title>
  e.g. rmnote height
  ```

## 💾 Storage
Ekko automatically saves tasks and notes to a local file (`ekko.txt`) in a `data` directory. Your tasks persist even after exiting the program.

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- JavaFX 17

### Running Ekko
1. Download the JAR file from the latest release v2.0
  
2. Navigate to the Downloads directory:
   ```sh
   cd Downloads/
   ```
3. Run the program:
   ```sh
   java -jar ekko.jar
   ```
---
Enjoy using Ekko! 🎉 If you have any feature requests or issues, feel free to open an issue or contribute to the project!

