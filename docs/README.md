# **Baymax User Guide**
📌 **Version: 1.0**  
📆 **Last Updated: February 2025**

> "Hello! I'm Baymax, your personal emotional companion 😊. How may I assist you today?"

---

## **Table of Contents**
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
    - [List Tasks](#1-list-tasks)
    - [Add Tasks](#2-add-task-) 
      - [Add To-do](#add-to-do-)
      - [Add Deadline](#add-deadline)  
      - [Add Event](#add-event)
    - [Mark Task](#3-mark-task)
    - [Unmark Task](#4-unmark-task)
    - [Delete Task](#5-delete-task)
    - [Finding Tasks](#6-find-keyword)
    - [Help Command](#7-help)

---

## **Introduction**
Baymax is a **task manager chatbot** designed to help users organize their tasks efficiently. It allows users to add, manage, and search for tasks through a simple command-line interface or graphical user interface (GUI).

---

## **Quick Start**
1. **Download** the latest `baymax.jar` file from [here](https://github.com/yourusername/baymax/releases).
2. Ensure you have **Java 11 or above** installed on your system. Check your Java version using:
   ```sh
   java -version
3. Open a terminal or command prompt and navigate to

---

## **Features**
Baymax supports the following features:

### **1. List Tasks**
Displays all tasks currently saved.  
📌 **Command:** `list`

### **2. Add Task** 
Add a `todo`, `deadline` or `event` to the list.  
#### **Add To-do**  
📌 **Command:** `todo <task>`  
🚀 **Example:**
````
todo Finish chemistry homework
todo task1
````

#### **Add Deadline**
📌 **Command:** `deadline <task> /by <date: YYYY-MM-DD> or <YYYY-MM-DD HHmm>`  
🚀 **Example:**
````
deadline Finish chemistry homework /by 2025-11-12
deadline Write Notes /by 2025-11-12 2300
DEADLINE complete assignment /BY 2025-08-01
````  
#### **Add Event**
📌 **Command:** `event <task> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>`  
🚀 **Example:**
````
event birthday party /from 2025-06-06 1800 /to 2025-06-06 2200
Event Summer trip /fRoM 2026-01-03 /TO 2026-01-17 1800
EVENT HACKATHON /FROM 2025-05-27 /TO 2025-05-29
````

### **3. Mark Task**
Marks a task as completed  
📌 **Command:** `mark <task number>`  
🚀 **Example:** `mark 1`  

### **4. Unmark Task**
Unmarks a task as completed  
📌 **Command:** `unmark <task number>`  
🚀 **Example:** `unmark 1`  

### **5. Delete Task**
Deletes a task from the list 
📌 **Command:** `delete <task number>`  
🚀 **Example:** `delete 1` 

### **6. Find Keyword**
Searches for tasks containing keyword
📌 **Command:** `find <keyword>`  
🚀 **Example:** `find meeting` 

### **7. Help**
Displays the list of commands available   
📌 **Command:** `help`  
🚀 **Output:**  
````
"Here's how you can use Baymax:
1. list - View all tasks
2. todo <task> - Add a to-do task
3. deadline <task> /by <yyyy-MM-dd optional: HHmm> - Add a deadline
4. event <task> /from <yyyy-MM-dd optional: HHmm> /to <yyyy-MM-dd optional: HHmm> - Add an event
5. mark <task number> - Mark a task as completed
6. unmark <task number> - Unmark a completed task
7. delete <task number> - Delete a task
8. find <keyword> - Search for tasks containing keyword
9. help - Display this help message
````


