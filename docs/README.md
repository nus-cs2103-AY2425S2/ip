# Viktor Chatbot User Guide

## Getting Started
### Installation
To use Viktor, ensure you have Java 17. Follow these steps:
1. Download `viktor.jar` from the release page.
2. Open a terminal and navigate to the folder containing `viktor.jar`.
3. Run Viktor with:
   ```sh
   java -jar viktor.jar
   ```

### Basic Usage
Once Viktor starts, type a command and press Enter. Viktor will respond accordingly.

For a visual aid, peruse the UI [here](UI.png).

## Features
### Task Management
Viktor helps you manage tasks efficiently. You can add, list, mark as done, delete, and search tasks.

#### Commands:
- **Add a Todo**:
  ```sh
  add todo Read a book
  ```
- **Add a Deadline**:
  ```sh
  add deadline Submit report /by 2/2/2025 1800
  ```
- **Add an Event**:
  ```sh
  add event Team meeting /from 1/3/2025 1800 /to 1/3/2025 1800
  ```
- **List all tasks**:
  ```sh
  list
  ```
- **Mark a task as done**:
  ```sh
  mark 1
  ```
- **Unmark a task as done**:
  ```sh
  unmark 1
  ```
- **Delete a task**:
  ```sh
  delete 2
  ```
- **Find all tasks containing a keyword**:
  ```sh
  find meeting
  ```
- **Find all tasks occuring on a specific date**:
  ```sh
  time 2/3/2025
  ```
- **Clear all tasks**:
  ```sh
  reset
  ```
- **View available commands**:
  ```sh
  help
  ```
- **Exit Viktor**:
  ```sh
  bye
  ```


### Persistent Storage
Viktor saves tasks automatically in `viktor.txt`. If the file is missing or corrupted, Viktor will handle it gracefully.

### Updating Viktor
To update, replace your `viktor.jar` file with the latest version from the release page.

### Troubleshooting
- **Viktor doesn’t start**: Ensure Java 17 is installed.
- **Command not recognized**: Verify the syntax or use `help`.
- **Trouble saving tasks**: Ensure you run viktor.jar with the correct command as seen [here](#installation).

This guide helps you quickly start using Viktor and make the most of its features. Hope you enjoy your productive time with Viktor! 

