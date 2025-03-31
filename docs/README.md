# Clovis User Guide

// Product screenshot goes here

Clovis is a comprehensive chatbot designed to simplify task management,
Whether you need to keep track of your daily tasks, meet important deadlines, or schedule events, Clovis has you covered. 

With Clovis, you can add, delete, mark, and search for tasks efficiently using simple text commands.
Yes, it uses a Command Line Interface (CLI) while still having benefits of a Graphical User Interface (GUI).

## Adding Tasks

There are 3 types of tasks: Todos, Deadlines, and Events.

Note that you cannot enter a date of the past.

### Adding a Todo: _todo_

Adds a todo with the specified description to your task list.

Usage: `todo <description of task>`

```
Example input: todo Buy groceries
```
```
Expected output: Got it. I've added this task:
                 [T][ ] Buy groceries
                 Now you have 1 task in the list.
```
### Adding a Deadline: _deadline_
Adds a deadline with the specified description and end date to the your task list.

Usage: `deadline <description of task> /by <date and time>`
(Note that the input <ins>date and time</ins> have to be in the format of <ins>DD/MM/YYYY HHmm</ins>).

```
Example input: deadline Submit report /by 10/10/2025 2359
```
```
Expected output: Got it. I've added this task:
                 [D][ ] Submit report (by: 10 Oct 2024, 23:59)
                 Now you have 2 tasks in the list.
```
### Adding an Event: _event_
Adds an event with the specified description, and start date to the end date to your task list.

Usage: `event (description of task) /from (date and time) /to (date and time)`
```
Example input: event Team meeting /from 12/10/2024 1400 /to 12/10/2024 1600
```
```
Expected output: Got it. I've added this task:
                 [E][ ] Team meeting (from: 12 Oct 2024, 14:00 to: 16:00)
                 Now you have 3 tasks in the list.
```
## Listing tasks: _list_
Shows a list of all the tasks in your task list.

Usage: list
```
Expected output: Here are the tasks in your list:
                 1. [T][ ] Buy groceries
                 2. [D][ ] Submit report (by: 10 Oct 2024, 23:59)
                 3. [E][ ] Team meeting (from: 12 Oct 2024, 14:00 to: 16:00)
```
## Marking a task (completed): _mark_
Mark the specified task in the task list as completed.

Usage: mark <index>
```
Example input: mark 2
```
```
Expected output: Nice! I've marked this task as done:
                 [D][X] Submit report (by: 10 Oct 2024, 23:59)
```
## Unmarking a task (uncompleted): _unmark_
Unmark the specified task in the task list.

Usage: unmark <index>
```
Example input: mark 2
```
```
Expected output: OK, I've marked this task as not done yet:
                 [D][ ] Submit report (by: 10 Oct 2024, 23:59)
```
## Deleting a task: _delete_
Delete the specified task from the task list.

Usage: delete <index>
```
Example input: delete 1
```
```
Expected output: Noted. I've removed this task:
                        [T][ ] Buy groceries
                        Now you have 2 tasks in the list.
```
## Finding a task: _find_
Finds a task that contains the specified keyword.

Usage: find <keyword>
```
Example input: find report
```
```
Expected output: Here are the matching tasks in your list:
                 1. [D][ ] Submit report (by: 10 Oct 2024, 23:59)
```
## Exiting the application: _bye_
Exits the application.

Usage: bye
## Other Features
In addition to basic task management, Clovis includes other features that enhance user experience 
by ensuring task uniqueness and preventing schedule conflicts.
### Preventing Duplicates
Clovis automatically detects and prevents duplicate tasks from being added to the list. 
This ensures that your task list remains clean and free from redundancy.
### Detecting Schedule Conflicts
Clovis also helps you avoid scheduling conflicts by preventing overlapping deadlines and events. 
This ensures that tasks with time constraints do not clash with existing ones.

## With Clovis, your tasks stay organized!
Start using Clovis to simplify your task management.