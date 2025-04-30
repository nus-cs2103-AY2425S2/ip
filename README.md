# Yuki User Guide

Yuki is a desktop app for managing tasks,
optimized for use via a Command Line Interface (CLI).
If you can type fast, Yuki can help you manage your
tasks faster than traditional GUI apps.


## Listing tasks
You can list all the tasks by using the following command.
```
list
```
Yuki will output all the tasks in the list.
```
Here are the tasks in your list:
1. [TASK_TYPE][ ] TASK_DESCRIPTION
2. [TASK_TYPE][ ] TASK_DESCRIPTION
...
```


## Adding tasks

Yuki supports 3 types of tasks: `todo`, `deadline`, and `event`.

### Todo task
Todo tasks are tasks without any date/time attached to it.
You can use the following command to add a todo task by replacing the
TASK_DESCRIPTION with the description of the task.
```
todo TASK_DESCRIPTION
```
Yuki will output the task that has been added.
```
Got it. I've added this task:
[T][ ] TASK_DESCRIPTION
Now you have X tasks in the list.
```
### Deadline task
Deadline tasks are tasks that need to be done before a specific date/time.
You can use the following command to add a deadline task by replacing the
TASK_DESCRIPTION with the description of the task and the DATE with the date
in the format `yyyy-mm-dd`.
```
deadline TASK_DESCRIPTION /by DATE
```
Yuki will output the task that has been added.
```
Got it. I've added this task:
[D][ ] TASK_DESCRIPTION (by: DATE)
Now you have X tasks in the list.
```
### Event task
Event tasks are tasks that start at a specific time and end at a specific time.
You can use the following command to add an event task by replacing the
TASK_DESCRIPTION with the description of the task and the START with the starting time
and the END with the ending time.
```
event TASK_DESCRIPTION \from START \to END
```
Yuki will output the task that has been added.
```
Got it. I've added this task:
[E][ ] TASK_DESCRIPTION (at: DATE)
Now you have X tasks in the list.
```
## Marking and Unmarking tasks
### Marking tasks
You can mark a task as done by using the following command and replacing the INDEX with the index of the task.
```
mark INDEX
```
Yuki will output the task that has been marked as done.
```
Nice! I've marked this task as done:
[TASK_TYPE][X] TASK_DESCRIPTION
```
### Unmarking tasks
You can unmark a task as done by using the following command and replacing the INDEX with the index of the task.
```
unmark INDEX
```
Yuki will output the task that has been unmarked as done.
```
Nice! I've unmarked this task as done:
[TASK_TYPE][ ] TASK_DESCRIPTION
```
## Deleting tasks
You can delete a task by using the following command and replacing the INDEX with the index of the task.
```
delete INDEX
```
Yuki will output the task that has been deleted.
```
Noted. I've removed this task:
[TASK_TYPE][ ] TASK_DESCRIPTION
Now you have X tasks in the list.
```
## Finding tasks
You can find tasks by using the following command and replacing the KEYWORD with the keyword you want to search for.
```
find KEYWORD
```
Yuki will output the tasks that contain the keyword.
```
Here are the matching tasks in your list:
1. [TASK_TYPE][ ] TASK_DESCRIPTION
2. [TASK_TYPE][ ] TASK_DESCRIPTION
...
```
Note that the search is case-sensitive and can only match full exact words.

## Exiting the application
You can exit the application by using the following command.
```
bye
```
Yuki will exit the application and save the tasks to the hard disk.

## Saving the data
Yuki saves the data automatically after any command that changes the data.
There is no need to save manually. But if you want to save manually, you can go to the `data` folder and replace the `Yuki.txt` file with the new one.
The format of the data is as follows:
```
T/D/E | X | TASK_DESCRIPTION | DATE
```
- Where `T` represents a todo task, `D` represents a deadline task, and `E` represents an event task.
- `X` represents whether the task is done or not. `1` represents done and `0` represents not done.
- `TASK_DESCRIPTION` represents the description of the task.
- `DATE` represents the date of the task. If the task is a todo task, the date will be empty.
  And if it is a event task, there will be two dates separated by a `|`.

## Undoing the previous command
You can undo the previous command by using the following command.
```
undo
```
Yuki will undo the previous command and output the tasks before the command was executed.

Notice that the undo command can only undo the latest command.
Only marking, adding and deleting commands can be undone.
