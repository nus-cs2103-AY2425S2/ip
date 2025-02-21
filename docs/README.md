# Grass User Guide

![Grass sample](./Ui.png)

Grass has the following uses:
- developers get to *touch grass* without leaving their keyboards by developing on this project
- double use as a task manager

## Adding deadlines

You can add a deadline to Grass your deadline by using the deadline command.

Example: `deadline sleep /by 10pm`

```
Got it. I've added this task:
[D][] sleep (by:7pm)
Now you have 12 tasks in the list.
```

## Adding todo

You can add a todo task to Grass by using the todo command.

Example: `todo sleep`

```
Got it. I've added this task:
[T][] sleep
Now you have 12 tasks in the list.
```

## Adding Event

You can add an event to Grass by using the event command.

Example: `event sleep /from now /to 7pm`

```
Got it. I've added this task:
[E][] sleep (from: now to 7pm)
Now you have 12 tasks in the list.
```

## List Tasks

You can list the tasks added to Grass by using the list command.

Example: `list`

```
1. [D][] sleep (by:7pm)
2. [T][] sleep
3. [E][] sleep (from: now to 7pm)
```

## Delete Task

You can delete a task from Grass by using the delete command.

Example: `delete 1`

```
Noted. I've removed this task:
[E][] sleep (from: now to 7pm)
Now you have 11 tasks in the list.
```

## Find Task

You can find a task in Grass by using the find command.

Example: `find sleep`

```
Here are the matching tasks in your list:
1. [D][] sleep (by:7pm)
2. [T][] sleep
3. [E][] sleep (from: now to 7pm)
```

## Mark Task

You can mark a task in Grass by using the mark command.

Example: `mark 1`

```
Nice! I've marked this task as done:
[D][X] sleep (by:7pm)
```

## Unmark Task

You can unmark a task in Grass by using the unamrk command.

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
[D][] sleep (by:7pm)
```

## Goodbye

You can say goodbye to grass using the bye command.

Example: `bye`

```
Bye. Home to see you again soon!
```