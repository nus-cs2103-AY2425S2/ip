# Koji Chatbot User Guide

![Koji Screenshot](https://meglwhy.github.io/ip/Ui.png)  
*Screenshot of Koji Chatbot in action.*

Koji Chatbot is your personal task manager and assistant, helping you track tasks, deadlines, events, and more. With an intuitive interface, you can effortlessly add, update, edit, and find tasks using simple commands.

---

## 🚀 Features Overview
- **Add Deadlines, Todos, and Events**
- **Mark and Unmark Tasks**
- **Edit and Find Tasks**
- **Delete Tasks**
- **List All Tasks**
- **Exit the Program**

---

## 📌 Adding Deadlines
To add a deadline, use the `deadline` command followed by the task description and deadline date/time in the format `yyyy-MM-dd HHmm`.

**Example:**
```sh
deadline return book /by 2023-12-31 1800
```
**Expected Output:**
```
Got it. I've added this task:
  [D][ ] return book (by: Dec 31 2023, 6:00 PM)
Now you have X tasks in the list.
```

---

## 📌 Adding a Todo
To add a todo, use the `todo` command followed by the task description.

**Example:**
```sh
todo read book
```
**Expected Output:**
```
Got it. I've added this task:
  [T][ ] read book
Now you have X tasks in the list.
```

---

## 📌 Adding Events
To add an event with a start and end time, use the `event` command:

**Example:**
```sh
event project meeting /from 2023-10-15 1400 /to 2023-10-15 1600
```
**Expected Output:**
```
Got it. I've added this task:
  [E][ ] project meeting (from: Oct 15 2023, 2:00 PM to: Oct 15 2023, 4:00 PM)
Now you have X tasks in the list.
```

---

## ✅ Marking and Unmarking Tasks
### Mark a Task as Done
```sh
mark TASK_NUMBER
```
**Example:**
```sh
mark 2
```
**Expected Output:**
```
Nice! I've marked this task as done:
  [E][X] Study session (from: Feb 01 2019, 12:00 AM to: Dec 12 2020, 6:00 PM)
Now you have X tasks in the list.
```

### Unmark a Task (Mark as Not Done)
```sh
unmark TASK_NUMBER
```
**Example:**
```sh
unmark 2
```
**Expected Output:**
```
OK, I've marked this task as not done yet:
  [E][ ] study sesh (from: Feb 01 2019, 12:00 AM to: Dec 12 2020, 6:00 PM)
Now you have X tasks in the list.
```

---

## ✏️ Editing Tasks
To update a task's description without deleting it, use:
```sh
edit TASK_NUMBER NEW_DESCRIPTION
```
**Example:**
```sh
edit 3 Submit IP report
```
This command updates the description of the task at position 3.

---

## 📋 Listing Tasks
To view all tasks, use:
```sh
list
```
**Expected Output:**
```
Here are the tasks in your list:
1. [T][ ] study lac4202
2. [D][X] return book (by: Dec 31 2023, 6:00 PM)
```

---

## 🔍 Finding Tasks
To search for tasks containing a specific keyword, use:
```sh
find KEYWORD
```
**Example:**
```sh
find book
```
**Expected Output:**
```
Here are the matching tasks in your list:
1. [T][ ] study lac4202
2. [D][X] return book (by: Dec 31 2023, 6:00 PM)
```

---

## 🗑️ Deleting Tasks
To delete a task, use:
```sh
delete TASK_NUMBER
```
**Example:**
```sh
delete 2
```
**Expected Output:**
```
Noted. I've removed this task:
  [D][X] return book (by: Dec 31 2023, 6:00 PM)
Now you have X tasks in the list.
```

---

## ❌ Exiting the Program
To exit, use:
```sh
bye
```

---

## ℹ️ Summary
This User Guide provides an overview of **Koji Chatbot’s** key features, including:
- Adding deadlines, todos, and events.
- Marking and unmarking tasks.
- Editing and finding tasks.
- Listing and deleting tasks.
- Exiting the program.

Follow the examples for correct command usage, and refer back to this guide whenever needed. Happy task managing! 🎯
