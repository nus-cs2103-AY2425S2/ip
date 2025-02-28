# GPTZeroFive Task Manager

GPTZeroFive is a task management application that helps you keep track of your tasks, deadlines, events, and notes. It is a command-line interface (CLI) application written in Java.

## Features

- Add tasks: todo, deadline, event
- Mark tasks as done
- Delete tasks
- Find tasks by keyword
- Add, show, edit, and delete notes for tasks
- List all tasks
 
## Usage

Here are some example commands you can use:

- `list`: Lists all tasks.
  Example:
  ```
  Input: list
  Output:
  1. [T][ ] read book
  2. [D][X] submit assignment (by: 15/10/2023 2359)
  ```

- `todo <description>`: Adds a todo task.
  Example:
  ```
  Input: todo finish homework
  Output: 
  Got it. I've added this task:
    [T][ ] finish homework
  Now you have 1 tasks in the list.
  ```

- `deadline <description> /by <deadline>`: Adds a deadline task.
  Example:
  ```
  Input: deadline submit report /by 16/10/2023 2359
  Output:
  Got it. I've added this task:
    [D][ ] submit report (by: 16/10/2023 2359)
  Now you have 2 tasks in the list.
  ```

- `event <description> /from <start time> /to <end time>`: Adds an event task.
  Example:
  ```
  Input: event team meeting /from 17/10/2023 1000 /to 17/10/2023 1200
  Output:
  Got it. I've added this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  Now you have 3 tasks in the list.
  ```

- `mark <index>`: Marks the task at the specified index as done.
  Example:
  ```
  Input: mark 1
  Output:
  Nice! I've marked this task as done:
    [T][X] finish homework
  ```

- `delete <index>`: Deletes the task at the specified index.
  Example:
  ```
  Input: delete 2
  Output:
  Noted. I've removed this task:
    [D][ ] submit report (by: 16/10/2023 2359)
  Now you have 2 tasks in the list.
  ```

- `find <keyword>`: Finds tasks that contain the keyword.
  Example:
  ```
  Input: find meeting
  Output:
  1. [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- `newNote <index> <note>`: Adds a note to the task at the specified index.
  Example:
  ```
  Input: newNote 3 Remember to prepare slides
  Output:
  Got it. I've added a note to this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- `showNote <index>`: Shows the note for the task at the specified index.
  Example:
  ```
  Input: showNote 3
  Output:
  Remember to prepare slides
  ```

- `editNote <index> <note>`: Edits the note for the task at the specified index.
  Example:
  ```
  Input: editNote 3 Prepare slides for the meeting
  Output:
  Got it. I've edited the note for this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- `deleteNote <index>`: Deletes the note for the task at the specified index.
  Example:
  ```
  Input: deleteNote 3
  Output:
  Got it. I've removed the note from this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

**Warning:** Keep the `src/main/java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
