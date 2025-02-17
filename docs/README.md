# Scooby User Guide

## 1. Introduction
Welcome to Scooby, a command-line-based chatbot that helps you manage your tasks efficiently!
Scooby allows you to add, update, delete, and manage tasks such as ToDos, Deadlines, and Events in a simple and intuitive way.

![UI Screenshot](Ui.png)

## 2. Quick Start
1. **Download** the latest release from [GitHub Releases](https://github.com/cyrolite/ip/releases/tag/A-Release).
2. **Navigate** to the directory containing `Scooby.jar`.
3. **Run the application** using:
   ```sh
   java -jar Scooby.jar
   ```

## 3. Commands

### Adding tasks

| Command  | Description  |
|----------|-------------|
| `todo <task_name>`  | Create a new task with `<task_name>`. |
| `deadline <task_name> /by <deadline>`  | Create a Deadline task with `<deadline>`. |
| `event <task_name> /from <start_time> /to <end_time>`  | Create an Event task with `<start_time>` and `<end_time>`. |

Examples:

| Command | Output |
|---------|-------------|
| `todo Task 1` | `[T][ ] Task 1 ` |
| `deadline Task 2 /by 2025-01-01 0000` | `[D][ ] Task 2 (by: Jan 01 2025 0000)` |
| `event Task 3 /from 2025-01-01 0000 /to 2025-01-01 1200` | `[E][ ] Task 3 (from: Jan 01 2025 0000 to: Jan 01 2025 1200)` |


### Modifying Tasks

| Command  | Description  |
|----------|-------------|
| `list`  | Lists all available tasks |
| `mark <task index>`  | Marks tasks at `<task index>`. |
| `unmark <task index>`  | Unmarks tasks at `<task index>`. |
| `delete <task index>`  | Deletes tasks at `<task index>`. |
| `update <task index> <new details>`  | Updates tasks at `<task index>` with new details. |

### Miscellaneous Commands

| Command  | Description  |
|----------|-------------|
| `find <description>`  | Finds all tasks with this exact description. |
| `help`  | Lists all available commands. |
| `bye`  | Exits the bot. |
