<link rel="stylesheet" href="style.css">

# Botato guide

This document outlines the commands available for managing tasks, including adding, marking, unmarking, deleting, and finding tasks. Supported date formats are also listed.

---

## Commands

### Basic Commands
- **`bye`**: Exits the application.
    - Example:
      ```
      bye
      ```

- **`list`**: Lists all tasks.
    - Example:
      ```
      list
      ```

- **`help`**: Displays the list of available commands.
    - Example:
      ```
      help
      ```

### Task Modification Commands
- **`mark [task number]`**: Marks a task as completed.
    - Example:
      ```
      mark 2
      ```

- **`unmark [task number]`**: Unmarks a completed task.
    - Example:
      ```
      unmark 2
      ```

- **`delete [task number]`**: Deletes a task.
    - Example:
      ```
      delete 3
      ```

### Task Creation Commands
- **`todo [description]`**: Adds a todo task with the specified description.
    - Example:
      ```
      todo Read a book
      ```

- **`deadline [description] /by [date]`**: Adds a deadline task with the specified description and due date.
    - Example:
      ```
      deadline Finish project /by 2023-12-15 18:00
      ```

- **`event [description] /from [date] /to [date]`**: Adds an event task with the specified description, start date, and end date.
    - Example:
      ```
      event Team workshop /from 2023-11-20 09:00 /to 2023-11-20 17:00
      ```

### Search Command
- **`find [keyword(s)]`**: Searches for tasks containing the specified keyword(s).
    - Example:
      ```
      find workshop
      ```

---

## Supported Date Formats

The following date formats are supported for deadlines and events:

### ISO Formats
- `yyyy-MM-dd HH:mm:ss`
    - Example: `2023-12-31 23:59:59`
- `yyyy-MM-dd HH:mm`
    - Example: `2023-12-31 23:59`
- `yyyy-MM-dd`
    - Example: `2023-12-31`

### Slash Formats
- `dd/MM/yyyy HH:mm:ss`
    - Example: `31/12/2023 23:59:59`
- `dd/MM/yyyy HH:mm`
    - Example: `31/12/2023 23:59`
- `dd/MM/yyyy`
    - Example: `31/12/2023`
- `dd/MM/yy HH:mm:ss`
    - Example: `31/12/23 23:59:59`
- `dd/MM/yy HH:mm`
    - Example: `31/12/23 23:59`
- `dd/MM/yy`
    - Example: `31/12/23`

### Text Formats
- `dd MMM yyyy HH:mm:ss`
    - Example: `31 Dec 2023 23:59:59`
- `dd MMM yyyy HH:mm`
    - Example: `31 Dec 2023 23:59`
- `dd MMM yyyy`
    - Example: `31 Dec 2023`

---
