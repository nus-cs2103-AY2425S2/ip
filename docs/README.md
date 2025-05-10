# Dynamis - User Guide

Introducing **Dynamis**, a simple and efficient task management chatbot. This guide will help you understand the basic commands and how to use them.

---

##  Getting Started

To start using **Dynamis**, simply run the program and interact with it using the available text commands mentioend below.

---

## Features & Commands

### 1️⃣ Add a To-Do Task
Creates a task without a deadline.

```
todo <task_description>
```
**Example:**
```
todo Laundry
```
📝 **Adds**: `[T][ ] Laundry`

---

### 2️⃣ Add a Deadline Task
Creates a task that must be completed by a certain date.

```
deadline <task_description> /by <yyyy-MM-dd>
```
**Example:**
```
deadline Math homework /by 2024-06-15
```
📝 **Adds**: `[D][ ] Math homework (by: Jun 15 2024)`

---

### 3️⃣ Add an Event Task
Creates a task with a start and end time.

```
event <task_description> /from <start_time> /to <end_time>
```
**Example:**
```
event party /from 10am /to 12pm
```
📝 **Adds**: `[E][ ] party (from: 10am to: 12pm)`

---

### 4️⃣ View All Tasks
Lists all the tasks currently stored.

```
list
```
📋 **Example Output:**
```
1. [T][ ] Laundry
2. [D][ ] Math homework (by: Jun 15 2024)
3. [E][ ] party (from: 10am to: 12pm)
```

---

### 5️⃣ Mark a Task as Done
Marks a task as completed.

```
mark <task_number>
```
**Example:**
```
mark 2
```
✅ **Marks Task #2 as Done** → `[D][X] Math homework (by: Jun 15 2024)`

---

### 6️⃣ Delete a Task
Deletes a task from the list.

```
delete <task_number>
```
**Example:**
```
remove 1
```
🗑 **Deletes Task #1**

---

### 7️⃣ Find Tasks by Keyword
Searches for tasks containing a specific keyword.

```
find <keyword>
```
**Example:**
```
find book
```
🔍 **Matching Tasks:**
```
1. [T][X] Laundry
2. [D][ ] return book (by: Jun 6th)
```

---

### 8️⃣ Get Help
Displays a list of available commands.

```
help
```
📖 **Example Output:**
```
Here are the available commands:
  todo <task>
  deadline <task> /by <yyyy-MM-dd>
  event <task> /from <yyyy-MM-dd> /to <yyyy-MM-dd>
  list
  mark <task_number>
  remove <task_number>
  find <keyword>
  help
  bye
```

---

### 9️⃣ Exit the Program
Closes the chatbot.

```
bye
```
👋 **Example Output:**
```
Bye. Hope to see you again soon!
```

---
