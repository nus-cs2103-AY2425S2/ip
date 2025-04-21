# 🐱 Nyanko - Task Manager

> **"Nyaaa welcome to Nyanko Chatbot!"**

Nyanko is a **simple, text-based** task manager that helps you keep track of **ToDos, Deadlines, and Events**. It supports **keyword searching**, **task persistence**, and a **friendly GUI built with JavaFX**.

🚀 Built with **Java 17** and **JavaFX**, Nyanko is designed to be lightweight and fun to use!

---

![Nyanko Screenshot](./docs/images/nyanko-ui.png)  
*Screenshot of the GUI version of Nyanko*

---

## ✨ **Features**

- ✅ **Manage Tasks Efficiently**
- 📌 **Supports Three Types of Tasks** (ToDos, Deadlines, Events)
- 😴 **Snooze Tasks**
- 🔍 **Find Tasks Using Keywords**
- 💾 **Persistent Storage** - Saves tasks to a file
- 🎭 **Intuitive Command-Based Interface**
- 🎨 **Graphical User Interface (GUI) with JavaFX**

see user guide for more info

---

## 📥 **Installation & Setup**

1. **Download the latest JAR file** from the [Releases](https://github.com/rannn367/ip/releases) page.
2. **Run the application** using the terminal:
   ```sh
   java -jar Nyanko.jar

---

## 📖 **User Guide**

Nyanko supports a variety of commands to manage your tasks. Below is a detailed guide for each command, including syntax and examples.

---

## 📋 **Table of Commands**

| Command               | Description                     | Link to Section                                |
|-----------------------|---------------------------------|-----------------------------------------------|
| `todo`               | Add a ToDo task                | [Add a ToDo Task](#1-add-a-todo-task)         |
| `deadline`           | Add a Deadline task            | [Add a Deadline Task](#2-add-a-deadline-task) |
| `event`              | Add an Event task              | [Add an Event Task](#3-add-an-event-task)     |
| `list`               | List all tasks                 | [List All Tasks](#4-list-all-tasks)           |
| `mark`               | Mark a task as done            | [Mark a Task as Done](#5-mark-a-task-as-done) |
| `unmark`             | Unmark a task as done          | [Unmark a Task](#6-unmark-a-task)             |
| `delete`             | Delete a task                  | [Delete a Task](#7-delete-a-task)             |
| `find`               | Find tasks by keyword          | [Find Tasks by Keyword](#8-find-tasks-by-keyword) |
| `snoozeDeadline`     | Snooze a deadline task          | [Snooze a Deadline Task](#9-snooze-a-deadline-task) |
| `snoozeEvent`        | Snooze an event task            | [Snooze an Event Task](#10-snooze-an-event-task) |
| `bye`                | Exit the application           | [Exit the Application](#11-exit-the-application) |

---

### 1. **Add a ToDo Task**
- **Syntax**:  
  `todo <description>`
- **Example**:  
  ```sh
  todo Buy groceries
  ```

---

### 2. **Add a Deadline Task**
- **Syntax**:  
  `deadline <description>\<due_date>`
  - `<due_date>` must be in the format `yyyy-MM-dd HHmm`.
- **Example**:  
  ```sh
  deadline Submit report\2025-04-30 2359
  ```

---

### 3. **Add an Event Task**
- **Syntax**:  
  `event <description>\<start_time>\<end_time>`
  - `<start_time>` and `<end_time>` must be in the format `yyyy-MM-dd HHmm`.
- **Example**:  
  ```sh
  event Team meeting\2025-05-01 0900\2025-05-01 1100
  ```

---

### 4. **List All tasks**
- **Syntax**:
  `list`
- **Example**:
  ```sh
  list
  ```

---

### 5. **Mark a Task as Done**
- **Syntax**:
  `mark <task_number>`
  - `<task_number>` is the 1-based index of the task in the list.
- **Example**:
  ```sh
  mark 1
  ```

---

### 6. **Unmark a Task as Done**
- **Syntax**:
  `unmark <task_number>`
  - `<task_number>` is the 1-based index of the task in the list.
- **Example**:
  ```sh
  unmark 1
  ```

---

### 7. **Delete a Task**
- **Syntax**:
  `delete <task_number>`
  - `<task_number>` is the 1-based index of the task in the list.
- **Example**:
  ```sh
  delete 1
  ```

---

### 8. **Find Tasks by Keyword**
- **Syntax**:
  `find <keyword>`
- **Example**:
  ```sh
  find meeting
  ```

---

### 9. **Snooze a Deadline Task**
- **Syntax**:
  `snoozeDeadline <task_number> <new_due_date>`
  - `<task_number>` is the 1-based index of the task in the list.
  - `<new_due_date>` must be in the format yyyy-MM-dd HHmm.
- **Example**:
  ```sh
  snoozeDeadline 3 2025-05-01 1200
  ```

---

### 10. **Snooze an Event Task**
- **Syntax**:
  `snoozeEvent <task_number> <new_start_time> <new_end_time>`
  - `<task_number>` is the 1-based index of the task in the list.
  - `<new_due_date>` and `<new_end_time>` must be in the format yyyy-MM-dd HHmm.
- **Example**:
  ```sh
  snoozeEvent 4 2025-05-01 1000 2025-05-01 1200
  ```

---

### 11. **Exit the Application**
- **Syntax**:
  `bye`
- **Example**:
  ```sh
  bye
  ```

---

### Notes:
- Ensure all date and time inputs follow the format `yyyy-MM-dd HHmm`.
- Task numbers are based on the current list of tasks displayed using the `list` command.
- Commands are case-insensitive (e.g., `TODO` and `todo` are equivalent).

Happy task managing with Nyanko! 🐱
