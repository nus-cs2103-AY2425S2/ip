# SirDuke User Guide

The title of the product is named SirDuke.

![SirDuke in action](Ui.png?raw=true "Screenshot")

SirDuke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SirDuke can get your task management done faster than traditional GUI apps.

## SirDuke is packed with various features:
- Listing all tasks: `list`
- Addition of tasks
    - Todos: `todo`
    - Deadlines: `deadline`
    - Events: `event`
- Deletion of tasks: `delete`
- Mark tasks as done: `mark`
- Unmark tasks: `unmark`
- Search for task using keyword: `find`
- Editing tasks: `edit`
- Exiting the programme: `bye`

## `list`
Shows a list of your tasks.

Usage:

```dtd
> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by 21 Jan 2025 23:59)
3. [E][ ] CS2103T final exam (from 30 Apr 2025 17:00 to 15 Nov 2024 19:00)
```
## `todo`
Create a Todo.

Usage

`todo <todo name>`

```dtd
> todo CS2101 slides
I have added the following todo to your list: CS2101 slides

> list
1. [T][ ] CS2101 slides
```

## `deadline`
Create a Deadline.

Usage

`deadline <deadline name> /by <datetime>`

`<datetime> should be dd/MM/yyyy HHmm`.

```dtd
> deadline CS2109S quiz /by 21/01/2025 2359
I have added the following deadline to your list: CS2101 slides

> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
```

## `event`
Create an Event.

Usage

`event <event name> /start <start time> /end <end time>`

`<start time> and <end time> should be dd/MM/yyyy HHmm`.

```dtd
> event CS2103T final exam /start 30/04/2025 1700 /end 30/04/2025 1900

I have added the following event to your list: CS2103T final exam

> list
Here are the tasks in your list:
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
3. [E][ ] CS2103T final exam (from 30 Apr 2025 0500 pm to 15 Nov 2024 0700 pm)
```

## `delete`
Delete a specific task.

Usage

`delete <index>`

```dtd
> delete 3
Very well, I have deleted this task: CS2103T final exam

> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
```

## `mark`
Mark a task as completed.

Usage

`mark <index>`

```dtd
> mark 1
Well done, I have marked CS2101 slides as done.

> list
1. [T][X] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
```

## `unmark`
Un-mark a task as completed.

Usage

`unmark <index>`

```dtd
> unmark 1
Very well, I have unmarked this task as done.

> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
```

## `find`
Search a task based on keyword provided.

Usage

`find <keyword>`

```dtd
> find 2101
1. [T][ ] CS2101 slides

> find nonExistentTask
Unfortunately, I do not see any tasks with that keyword in my list

> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 21 2025 1159 pm)
```

## `edit`
Edit a field of a task. 
Only works if the task type has the field you are trying to edit.

Usage

`edit <fieldToBeEdited> <index> <newField>`

`<fieldToBeEdited>` can be `descripion`, `start`, `end`, `by`.

`<newField>` should follow the same format of the field as when you created the task.

```dtd
> edit by 2 22/01/2025 2359
I have successfully updated the requested task.

> list
1. [T][ ] CS2101 slides
2. [D][ ] CS2103T quiz (by: Jan 22 2025 1159 pm)
```


## `bye`
Exits SirDuke GUI and stores tasks.

Usage

`bye`

```dtd
> bye
Godspeed. I will see you soon.
> exits
```