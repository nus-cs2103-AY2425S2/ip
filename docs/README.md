# Tyrese User Guide
![Ui.png](Ui.png)

## Introduction
What's going on, guys? Tyrese Here!
I'm a pretty chill guy that likes to have keep my stuff in order you know?

Anyways! Here's what I can do for you:
- Add tasks without dates (todo)
- Add deadlines with specific due dates (deadline)
- Schedule events with start and end times (event)
- Track and manage your tasks with ease (list, mark, unmark, delete, save, find)

### Note:
- When opening the bot, drag the window slightly and it will auto adjust to its correct width!
- The priority command is optional
  - If no value is provided, it will default to LOW

---

## Features
### Adding Todos
Add a task without a specific date or time.

Usage:
`todo <description> /priority <LOW|MEDIUM|HIGH|URGENT>`

Example:
`todo Buy groceries`

Expected Outcome:
A new task "Buy groceries" will be added to your list.

---

### Adding Deadlines
Add a task with a specific due date.

Usage:
`deadline <description> /by <D/M/YYYY> /priority <LOW|MEDIUM|HIGH|URGENT>`

Example:
`deadline Submit assignment /by 25/1/2025`

Expected Outcome:
A new task "Submit assignment" with a due date of 25th January 2025 will be added to your list.

---

### Adding Events
Add an event with a start and end time.

Usage:
`event <description> /from <D/M/YYYY HHmm> /to <D/M/YYYY HHmm> /priority <LOW|MEDIUM|HIGH|URGENT>`

Example:
`event Project meeting /from 21/1/2025 1000 /to 21/1/2025 1200`

Expected Outcome:
A new event "Project meeting" scheduled from 10:00 to 12:00 on 21st January 2025 will be added to your list.

---

### Deleting Tasks
Delete a task from task list.

Usage:
`delete <task_number>`

Example:
`delete 2`

Expected Outcome:
The task at the specified index will be deleted and the list will be updated accordingly.

---

### Viewing Your Tasks
Display all your tasks in a list format.

Usage:
`list`

Expected Outcome:
A list of all your tasks, deadlines, and events will be displayed, showing their status (done or not done).

---

### Marking Tasks as Done
Mark a specific task as completed.

Usage:
`mark <task number>`

Example:
`mark 2`

Expected Outcome:
Task number 2 will be marked as done and will not be saved to save file.

---

### Unmarking Tasks
Unmark a specific task as incomplete.

Usage:
`unmark <task number>`

Example:
`unmark 2`

Expected Outcome:
Task number 2 will be marked as incomplete.

---

### Finding Tasks
Finds a specific task.

Usage: ```find <description>```

Expected Outcome: Finds all the tasks with a matching description.

---

### Exiting the Application:
Say your goodbyes guys!

Usage:
`exit`

Expected Outcome:
The application will close out.
