# Chitti User Guide

 ![Ui](https://github.com/user-attachments/assets/5b749691-0905-4e87-8b19-98e08bba758d)

Welcome to Chitti, your personal todo list manager. Manage and view all your lists in 1 place with this easy to use text based application! 

## Adding Simple task

Add a simple task using "todo" followed by name of task to be added

Example: `todo project`

This will add a task "project" to your task list. 

## Adding Deadlines

Add a deadline task by specifiying the name and deadline to finish the task by

Example: `deadline return book /by 2019-12-02`

A new task will be created with deadline of 2 Dec 2019

```
Got it. I've added this task:
[D][] return book (by: Dec 2 2019)
```

## Adding Events

Add an event by specifying name and event duration

Example: `event concert /from 9pm /to 11pm`

A new event will be created with specified duration

```
Got it. I've added this task:
[E][] concert (from: 9pm to: 11pm)
```

## List all tasks

List all tasks that has been added into the list using "list" in alphabetical order

Example: `list`

```
Here are the tasks in your list:
1. [D][] return book (by: Dec 2 2019)
2. [E][] concert (from: 9pm to: 11pm)
```
## Complete tasks

Mark tasks as completed using "mark" and index of the task

Example: `mark 1`

```
Nice! I've marked this task as done:
[D][X] return book(by: Dec 2 2019)
```

## Undo completed tasks

Unmark completed tasks using "unmark" and index of the task

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
[D][] return book(by: Dec 2 2019)
```

## Remove tasks

Delete tasks from the list using "delete" and index of the task

Example: `delete 1`

```
Noted. I've removed this task:
[D][] return book(by: Dec 2 2019)
```

## Search for tasks

Search for tasks in the list using find keyword

Example: `find con`

```
Here are the matching tasks:
[E][] concert (from: 9pm to: 11pm)
```
