# Helix User Guide

## **Table of Content**
## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
   - [Installation](#installation)
   - [Basic Usage](#basic-usage)
- [Features](#features)
   - [Adding a TODO Task](#adding-a-todo-task)
   - [Adding a DEADLINE Task](#adding-a-deadline-task)
   - [Adding an Event Task](#adding-an-event-task)
   - [Listing Tasks](#listing-tasks)
   - [Marking Tasks as Completed](#marking-tasks-as-completed)
   - [Unmarking Tasks](#unmarking-tasks)
   - [Deleting a Task](#deleting-a-task)
   - [Finding a Task](#finding-a-task)
   - [Updating a Task](#updating-a-task)
   - [Exiting Helix](#exiting-helix)
- [File Storage](#file-storage)
- [Command Summary](#command-summary)
- [FAQ](#faq)
- [UI Overview](#ui-overview)
- [Feedback and Support](#feedback-and-support)
--- 
## **Introduction**
\
*A Smart Task Manager with a Simple and Efficient Graphical User Interface (GUI).*

Helix is a **personal task management assistant** that allows users to create, manage, and organize their tasks efficiently using a **Graphical User Interface (GUI)**. It supports different types of tasks, such as to-dos, deadlines, and events, with file-based storage to ensure persistence across sessions.

---

## **Getting Started**

### Prerequisites
- **For users**: Install **Java 17 JRE** to run Helix.
- **For developers**: Install **Java 17 JDK** for development.

### **Installation**

1. Download the latest **Helix.jar** from the [Releases](https://github.com/twhjames/ip/releases) page.
2. Open a terminal and navigate to the folder containing `Helix.jar`.
3. Run the application using:
   ```bash
   java -jar Helix.jar
   ```

### **Basic Usage**
Helix operates entirely through its **GUI interface**, where users can interact with the assistant via a chat-style interface.

---

## **Featues**

### **Adding a TODO task**

**Purpose:**
Adds a simple to-do task.

**Syntax:**
```bash
todo <task description>
```

**Example:**
```bash
todo Buy groceries
```

**Expected Output:**
```bash
Task Added!
  📋 Type: TODO
  📝 Description: Buy groceries
You now have X tasks in your list.
```

---

### **Adding a DEADLINE Task**

**Purpose:**
Adds a task with a deadline.

**Syntax:**

```bash
deadline <task description> /by <YYYY-MM-DD HHmm>
```

**Example:**

```bash
deadline Submit assignment /by 2024-12-31 1800
```

**Expected Output:**

```bash
Task Added!
  📋 Type: DEADLINE
  📝 Description: Submit assignment
  📅 Due: Dec 31 2024, 6:00 PM
You now have X tasks in your list.
```

---

### **Adding Event Tasks**

**Purpose:**
Adds an event with a start and end time.

**Syntax:**

```bash
event <task description> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>
```

**Example:**

```bash
event Team meeting /from 2024-12-31 0900 /to 2024-12-31 1100
```

**Expected Output:**

```bash
Task Added!
  📋 Type: EVENT
  📝 Description: Team meeting
  🕒 From: Dec 31 2024, 9:00 AM
  🕒 To: Dec 31 2024, 11:00 AM
You now have X tasks in your list.
```

---

### **Listing Tasks**

**Purpose:**
Displays all tasks in the list.

**Syntax:**

```bash
list
```

**Expected Output:**

```bash
📋 Task List:
  1. [T] [ ] Buy groceries
  2. [D] [✔] Submit assignment (by: Dec 31 2024, 6:00 PM)
  3. [E] [ ] Team meeting (from: Dec 31 2024, 9:00 AM to: Dec 31 2024, 11:00 AM)
```

---

### **Marking Tasks as Completed**

**Purpose:**
Marks a task as done.

**Syntax:**

```bash
mark <task number>
```

**Example:**

```bash
mark 2
```

**Expected Output:**

```bash
✔ Task marked as complete!
  [D] [✔] Submit assignment (by: Dec 31 2024, 6:00 PM)
```

---

### **Unmarking Tasks**

**Purpose:**
Marks a task as **not done**.

**Syntax:**

```bash
unmark <task number>
```

**Example:**

```bash
unmark 2
```

**Expected Output:**

```bash
❌ Task marked as incomplete!
  [D] [ ] Submit assignment (by: Dec 31 2024, 6:00 PM)
```

---

### **Deleting a Task**

**Purpose:**
Removes a task from the list.

**Syntax:**

```bash
delete <task number>
```

**Purpose:**

```bash
delete 3
```

**Expected Output:**

```bash
🗑️ Task Removed!
  [E] [ ] Team meeting (from: Dec 31 2024, 9:00 AM to: Dec 31 2024, 11:00 AM)
You now have X tasks in your list.
```

---

### **Finding a Task**

**Purpose:**
Searches for tasks containing a specific keyword.

**Syntax:**

```bash
find <keyword>
```

**Example:**

```bash
find groceries
```

**Expected Output:**

```bash
🔍 Tasks with matching keywords...
  1. [T] [ ] Buy groceries
```

---

### **Updating a Task**

**Purpose:**
Updates a task’s description or details.

**Syntax:**

```bash
update <task number> <task type> <new details>
```

**Example:**

```bash
update 2 deadline Submit final report /by 2025-01-01 1200
```

**Expected Output:**

```bash
✔ Task updated successfully!
  [D] [ ] Submit final report (by: Jan 1 2025, 12:00 PM)
```

---

### **Exiting Helix**

**Purpose:**
Closes the application.

**Syntax:**

```bash
bye
```

---

## **File Storage**

### **Automatic Task Storage**
- Helix automatically **saves tasks** in `data/helix_tasklist.txt`.
- Data **persists across sessions**, so tasks are not lost when restarting.

### **Format in Which Tasks Are Saved**
Each task is stored as a single line in the file, with its attributes separated by a `|`. The attributes are structured in the following order:
1.	Task Type: `TODO`, `DEADLINE`, or `EVENT`.
2.	Completion Status: `COMPLETED` or `PENDING`.
3.	Task Description: The name or details of the task.
4.	Date/Time:
       - For deadline tasks: The due date and time (`MMM DD YYYY, hh:mm a`). 
       - For event tasks: The start and end date/time (`MMM DD YYYY, hh:mm a - MMM DD YYYY, hh:mm a`).

**Example Entries**
```text
TODO | COMPLETED | Hit the gym
DEADLINE | PENDING | Submit iP Final Version | Feb 21 2025, 11:59 pm
EVENT | PENDING | CS2103 Team meeting | Feb 25 2025, 6:00 pm - Feb 25 2025, 7:00 pm
```

### **Editing the Data File**
You can manually edit the `helix_tasklist.txt` file using any text editor. Ensure that you follow the format specified above when modifying or adding new tasks. However, direct editing is not recommended as incorrect formatting may cause errors when loading tasks.

---

## **Command Summary**

| Command       | Description                                                                 |
|--------------|-----------------------------------------------------------------------------|
| `add todo <description>` | Adds a simple to-do task. Example: `add todo Read a book`                   |
| `add deadline <description> /by <due date>` | Adds a task with a deadline. Example: `add deadline Submit report /by 2025-01-30` |
| `add event <description> /from <start time> /to <end time>` | Adds an event. Example: `add event Team meeting /from 10am /to 12pm` |
| `list`        | Displays all the tasks in your list.                                        |
| `mark <task number>` | Marks the specified task as done. Example: `mark 1`                      |
| `unmark <task number>` | Marks the specified task as not done. Example: `unmark 1`              |
| `delete <task number>` | Deletes the specified task. Example: `delete 1`                       |
| `find <keyword>` | Finds all tasks that contain the keyword. Example: `find groceries`        |
| `update <task number> <task type> <new details>` | Updates an existing task. Example: `update 2 deadline Submit final report /by 2025-01-01 1200` |
| `bye`         | Exits the chatbot.                                                         |

---

## **FAQ**

### Q: Where are my tasks saved?
- A: Your tasks are saved in the `data/helix_tasklist.txt` file within the application directory.

### Q: Can I add multiple deadlines or events at once?
- A: Currently, Helix supports adding one task at a time. You can repeat the command to add more tasks.

### Q: What happens if I close Helix without saving?
- A: Your tasks are saved automatically, ensuring no data loss between sessions.

### Q: Can I edit a task once it is added?
- A: Yes, you can use the `update` command as detailed in the [Command Summary](#command-summary).

### Q: What are the system requirements for Helix?
- A: Helix requires **Java (version 17 or higher)** to run. Ensure that Java is properly installed on your system before running Helix.

---

## **UI Overview**
Below is a snapshot of the Helix user interface:
![Helix GUI](Ui.png)
- **Chat-Based Interaction** – User and bot messages are distinctly styled for clarity.
- **Effortless Task Addition** – Tasks are added via natural language with instant confirmation.
- **Clear Task Visualization** – Tasks are categorized and displayed for easy tracking.

---

## **Feedback and Support**
If you encounter any issues or have suggestions for new features, feel free to:
- Open an issue on the [GitHub repository](https://github.com/twhjames/ip/issues).
- Contact us via email at **jamesteo2701@gmail.com**.

---

## **🎉 Enjoy using Helix 🎉**
