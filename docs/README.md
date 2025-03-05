# G User Guide

<img width="400" alt="Ui.png" src="./Ui.png" />

## **📝 Introduction**

G is a **text-based chatbot** designed to help you manage tasks quickly and efficiently.  
It supports **to-dos**, **deadlines**, and **events**. G is **simple**, **fast**, and **free**!

## **📌 Features**

### **1. Adding Deadlines**

Easily add tasks with deadlines using the `deadline` command.

#### **Example:**

```
deadline Submit report /by 2025-02-28
```

Expected output:

```
Got it. I've added this task:
[D][ ] Submit report (by: Feb 28 2025)
```

### **2. Adding To-Do Tasks**

Create simple tasks without a specific deadline using the todo command.

#### **Example:**

```
todo Read book
```

Expected output:

```
Got it. I've added this task:
[T][ ] Read book
```

### **3. Adding Events**

Add events with specific start and end dates using the event command.

#### **Example:**

```
event Conference /from 2025-03-01 /to 2025-03-02
```

Expected output:

```
Got it. I've added this task:
[E][ ] Conference (from: Mar 01 2025 to: Mar 02 2025)
```

### **4. Listing All Tasks**

Display all tasks in your task list using the list command.

#### **Example:**

```
list
```

Expected output:

```
Here are the tasks in your list:
1. [T][ ] Read book
2. [D][ ] Submit report (by: Feb 28 2025)
3. [E][ ] Conference (from: Mar 01 2025 to: Mar 02 2025)
```

### **5. Marking Tasks as Completed**

Mark a task as completed using its index number with the mark command.

#### **Example:**

```
mark 1
```

Expected output:

```
Nice! I've marked this task as done:
[T][X] Read book
```

### **6. Unmarking Tasks as Not Done**

Revert a task's status to not completed using the unmark command.

#### **Example:**

```
unmark 1
```

Expected output:

```
OK, I've marked this task as not done yet:
[T][ ] Read book
```

### **7. Deleting Tasks**

Remove unnecessary tasks from your task list using the delete command.

#### **Example:**

```
delete 2
```

Expected output:

```
Noted. I've removed this task:
[D][ ] Submit report (by: Feb 28 2025)
Now you have 2 tasks in the list.
```

### **8. Finding Tasks by Keyword**

Search for tasks that contain a specific keyword using the find command.

#### **Example:**

```
find Conference
```

Expected output:

```
Here are the matching tasks in your list:
1. [E][ ] Conference (from: Mar 01 2025 to: Mar 02 2025)
```

### **9. Exiting the Chatbot**

Close the G chatbot safely using the bye command.

#### **Example:**

```
bye
```

Expected output:

```
Goodbye! See you soon! 👋
```

### **10. Automatic Saving**

G automatically saves your tasks after every command, ensuring no data is lost even if you close the application unexpectedly.

## Key Highlights:

-   Supports three types of tasks: To-dos, Deadlines, and Events.
-   Allows you to:
    -   Add tasks: Create to-dos, deadlines, and events.
    -   List all tasks: View all pending and completed tasks.
    -   Mark tasks as completed: Keep track of finished tasks.
    -   Unmark tasks: Revert completed tasks to pending status.
    -   Delete tasks: Remove unnecessary tasks from your list.
    -   Search for tasks: Quickly find tasks using keywords.
    -   Exit the application: Safely close the chatbot.
    -   Automatic saving ensures your progress is never lost.
