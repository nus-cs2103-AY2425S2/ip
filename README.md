# Dudu :bear:

Dudu is a simple command-line task manager that helps you organize your tasks efficiently. With Dudu, you can add different types of tasks, list them, search for specific ones, and manage their completion status easily.

## Features

- **To-Do Tasks**: Add simple to-do tasks.
- **Deadlines**: Add tasks with deadlines.
- **Events**: Add tasks with a start and end time.
- **Task Listing**: View all current tasks.
- **Search**: Find tasks by keyword.
- **Schedule**: View tasks scheduled for a specific date.
- **Task Completion Management**: Mark and unmark tasks as completed.
- **Help Command**: View available commands.
- **Exit Command**: Close the program.

## Commands

### Add a To-Do Task
```sh
todo <title>
```
Adds a to-do task to your list.

### Add a Deadline Task
```sh
deadline <title> /by <date/time>
```
- `<date/time>` format: `dd/mm/yyyy hhmm` (24-hour format) or `dd/mm/yyyy`.
- Adds a task with a deadline.

### Add an Event Task
```sh
event <title> /from <date/time> /to <date/time>
```
- `<date/time>` format: `dd/mm/yyyy hhmm` (24-hour format) or `dd/mm/yyyy`.
- Adds an event task with a start and end time.

### List All Tasks
```sh
list
```
Displays all current tasks.

### Find a Task
```sh
find <keyword>
```
Shows tasks that match the given keyword.

### View Schedule for a Specific Date
```sh
schedule <date>
```
- `<date>` format: `dd/mm/yyyy`.
- Shows tasks scheduled on the given date.

### Mark a Task as Done
```sh
mark <taskIndex>
```
Marks the indicated task as completed.

### Unmark a Task as Not Done
```sh
unmark <taskIndex>
```
Marks the indicated task as not completed.

### Show Help
```sh
help
```
Displays a list of available commands.

### Exit the Program
```sh
bye
```
Closes the Dudu task manager.

## Usage Example
```
> todo CS2109S Tutorial
Alright! I've added this task:
[T][ ] CS2109S Tutorial
Now you have one task in the list.

> deadline Finish SPH3107 Quiz /by 20/2/2025 2359
Alright! I've added this task:
[D][ ] Finish SPH3107 Quiz (by: Feb 20 2025, 11:59pm)
Now you have 2 tasks in the list.

> list
1. [T][ ] CS2109S Tutorial
2. [D][ ] Finish SPH3107 Quiz (by: Feb 20 2025, 11:59pm)

> mark 1
Nice! I've marked this task as done:
[T][X] CS2109S Tutorial
```

## Installation & Setup
### Download the JAR File
1. Go to the [Releases](https://github.com/vikeedough/ip/releases) page of this repository.
2. Download the latest `dudu.jar` file.
3. Place the JAR file in any folder of your choosing.
4. Double click on the JAR file to get started!

---
Enjoy using **Dudu** to stay organized and productive!
