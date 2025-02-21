# Ekko - Your Personal Task Manager

Ekko is a simple, interactive task manager built with JavaFX. It helps users keep track of tasks, deadlines, and notes efficiently.

## Features

### 📝 Task Management
- **TODO**: Add a general task.
  ```
  TODO <description>
  ```
- **DEADLINE**: Add a task with a due date and time.
  ```
  DEADLINE <description> /by DD/MM/YYYY HH:MM
  ```
- **EVENT**: Add an event with a start and end time.
  ```
  EVENT <description> /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM
  ```

### ✅ Task Updates
- **Mark Task as Done**:
  ```
  MARK <index>
  ```
- **Unmark Task as Not Done**:
  ```
  UNMARK <index>
  ```

### 🔍 Searching & Listing
- **Find Tasks by Keyword**:
  ```
  FIND <keyword>
  ```
- **List All Tasks and Notes**:
  ```
  LIST
  ```

### 🗑️ Deletion
- **Delete a Task**:
  ```
  DELETE <index>
  ```

### 📝 Notes
- **Add a Note**:
  ```
  NOTE /t <title> /d <description>
  ```
- **Remove a Note**:
  ```
  RMNOTE <title>
  ```

## 💾 Storage
Ekko automatically saves tasks and notes to a local file (`ekko.txt`) in a `data` directory. Your tasks persist even after exiting the program.

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- JavaFX 17

### Running Ekko
1. Download the JAR file ekko.jar from the latest release v2.0.
  
2. Navigate to the directory that contains the downloaded JAR file:
   ```sh
   cd Downloads
   ```
3. Run the program with command:
   ```sh
   java -jar ekko.jar
   ```
---
Enjoy using Ekko! 🎉 If you have any feature requests or issues, feel free to open an issue or contribute to the project!

