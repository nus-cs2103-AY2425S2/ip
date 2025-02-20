# Pixel User Guide

Pixel is a **personal chatbot assistant** who **keeps tracks of your tasks**,  
allowing you to maximize your productivity.

* [Features](#features)
  * [Adding a ToDo: `todo`](#adding-a-todo)
  * [Adding a Deadline: `deadline`](#adding-a-deadline)
  * [Adding an Event: `event`](#adding-an-event)
  * [Listing all tasks: `list`](#listing-all-tasks)
  * [Deleting a task: `delete`](#deleting-a-task)
  * [Marking task as complete: `mark`](#marking-task-as-complete)
  * [Marking task as incomplete: `unmark`](#marking-task-as-incomplete)
  * [Finding tasks by description: `find`](#finding-tasks-by-description)
  * [Clearing all completed tasks: `clear`](#clearing-all-completed-tasks)
  * [Exiting the program: `bye`](#exiting-the-program)

<img src="Ui.png">


# Features


> **Notes about the command format**:  
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.  
    e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo clean room`.  
> * Command keywords are case-insensitive  
>   e.g. `dEleTE 1` will be interpreted as `delete 1`
> * Extraneous parameters for commands will be ignored.  
>   e.g. `mark 1 2 3` will be interpreted as `mark 1`.

## Adding a Todo

Adds a to-do task to the task list.

Usage: `todo DESCRIPTION`

Example: `todo clean room`

```
Got it. I've added this task:
  [T][ ] clean room
Now you have 1 tasks in the list
```

## Adding a Deadline

Adds a task with a deadline to the task list.

Usage: `deadline DESCRIPTION /by DEADLINE`
> `DEADLINE` must be in the format `YYYY-MM-DD HH:mm`, or `YYYY-MM-DD`


Example: `deadline assignment /by 2025-02-25 23:59`

```
Got it. I've added this task:
  [D][ ] assignment (by: Feb 25 2025 23:59)
Now you have 2 tasks in the list
```


## Adding an Event

Adds a task with a start and end date/time to the task list.

Usage: `event DESCRIPTION /from START /to END`
> `START` and `END` must be in the format `YYYY-MM-DD HH:mm`, or `YYYY-MM-DD`


Example: `event Band practice /from 2025-02-28 20:00 /to 2025-02-28 22:00`

```
Got it. I've added this task:
  [E][ ] Band practice (from: Feb 28 2025 20:00 to: Feb 28 2025 22:00)
Now you have 3 tasks in the list
```

## Listing all tasks

Displays details of all tasks in the task list.

Usage: `list`


Example: `list`

```
Here are the tasks in your list:
1. [T][ ] clean room
2. [D][ ] assignment (by: Feb 25 2025 23:59)
3. [E][ ] Band practice (from: Feb 28 2025 20:00 to: Feb 28 2025 22:00)
```


## Deleting a task

Deletes a task from the task list.

Usage: `delete TASK_NUMBER`
> The task number is the number beside the task when `list` is used


Example: `delete 2`

```
No problem, I've removed the task from the list:
[D][ ] assignment (by: Feb 25 2025 23:59)
Now you have 2 tasks in the list
```


## Marking task as complete

Marks a task in the task list as complete.

Usage: `mark TASK_NUMBER`
> The task number is the number beside the task when `list` is used


Example: `mark 1`

```
Excellent, I've marked this task as done:
  [T][X] clean room
```


## Marking task as incomplete

Marks a task in the task list as incomplete.

Usage: `unmark TASK_NUMBER`
> The task number is the number beside the task when `list` is used


Example: `unmark 1`

```
Alright, I've marked this task as not done yet:
  [T][ ] clean room
```


## Finding tasks by description

Finds all tasks in the task list whose descriptions match the keyword.

Usage: `find KEYWORD`


Example: `find practice`

```
Here are the tasks matching your description:
1. [E][ ] Band practice (from: Feb 28 2025 20:00 to: Feb 28 2025 22:00)
```


## Clearing all completed tasks

Deletes all completed tasks from the task list.

Usage: `clear`


Example: `clear`

```
Well done! I've removed the following completed tasks:
1. [T][X] clean room

Now you have 1 tasks in the list
```


## Exiting the program

Exits the program.

Usage: `bye`