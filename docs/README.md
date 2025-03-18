# 👾  Clank: Chatbot x Task Manager

---

Clank is an intelligent **task management chatbot** that helps you stay organized and productive.
With simple and easy-to-understand commands, track your todos, homework deadlines, or even 
upcoming event easily and never lose track and forget about them again!

> Clank **auto-saves** your tasks, so you won’t lose progress even if you close the app!

---

## 📥  Installation
1. Ensure Java 11 or later is installed on your machine.
2. Download Clank.jar from the latest release.
3. Open your terminal and run:
```
java -jar Clank.jar
```
4. You should see Clank’s greeting message. Start entering commands!

---

## 📌  Features
### 📝  1. Adding Tasks
Clank supports **three** types of tasks:
- To-Do → Simple tasks with no date.
```
todo Read a book
```
✓ Adds "Read a book" to your list.

- Deadline → Tasks with a due date.
```
deadline Submit assignment /by 21/02/2025 2359
```
✓ Adds "Submit assignment" due on Feb 21, 2025.

- Event → Tasks with a specific date and time.
```
event Team meeting /from 22/2/2025 1500 /to 22/2/2025 1600
```
✓ Schedules "Team meeting" from 3pm to 4pm on Feb 22, 2025.

### ✅  2. Managing Tasks
- Save the current task list:
```
save
```
✓ Saves all the tasks in task list.

- Mark a task as done:
```
mark 1
```
✓ Marks task #1 as completed.

- Unmark a task:
```
unmark 1
```
✓ Marks task #1 as not done.

- Delete a task:
```
delete 2
```
✓ Removes task #2.

- Delete all tasks:
```
delete all
```
✓ Removes all available tasks.

### 🔍  3. Viewing & Searching Tasks
- List all tasks:
```
list
```
✓ Displays all your tasks.

- Find tasks by keyword:
```
find book
```
✓ Searches for tasks containing "book".

- Find tasks by multiple keywords:
```
find book study
```
✓ Searches for tasks containing "book" **AND** ""study".

### ⏰ 4. Showing Reminders
To show upcoming tasks:
```
reminder 3
```
✓ Show upcoming tasks in 3 days.

### 🚪  5. Exiting Clank
To close the chatbot:
```
bye
```
✓ Saves tasks and exits.

🚀 Stay productive with Clank!