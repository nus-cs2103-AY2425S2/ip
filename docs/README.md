# Orim User Guide

![Screenshot of using Orim to track all todo tasks.](/docs/Ui.png)

Orim frees your mind of having to remember things you need to do.

## Adding todos/deadlines/events

It allows you to add todos/deadlines/events to the tasklist

Example: `todo return book`

A todo task with descrption of "return book" will be added to the list.

```
Got it. I've added this task:
 [T][ ] return book
Now you have 1 tasks in the list.
```

## Deleting tasks

It allows you to delete ith task from the current tasklist.

Example: `delete 1`

It will delete the first task in the tasklist and shows you how many tasks do you left.

```
Noted. I've removed this task:
 [T][ ] return book
Now you have 1 tasks in the list.
```

## Marking finished tasks

It allows you to mark ith task as done.

Example: `mark 1`

It will mark the first task which is return book as done. The finished task will still inside the tasklist until you delete it, so you can see your progess.

```
Nice! I've marked this task as done:
 [T][X] return book
```

## Unmarking tasks

It allows you to unmark ith task.

Example: `unmark 1`

It will unmark the first task which is submit homework as not done.

```
OK, I've marked this task as not done yet:
 [T][ ] submit homework
```

## Listing current tasks

It shows all current tasks and their status in the tasklist.

Example: `list`

It will show all current tasks and their status in the tasklist.

```
Here are the tasks in your list:
1.[T][ ] submit homework
2.[T][ ] return book
```

## finding tasks according to descrption

It will filter out all tasks in the tasklist that contains keyword that user input.

Example: `find homework`

It shows a list of task that have "homework" in task descrption.

```
Here are the tasks in your list:
1.[T][ ] submit cs2103 homework
2.[T][ ] submit cs3243 homework
```

## finding tasks that needs to be done

It will show you all tasks that are in the tasklist and does not been marked.

Example: `find unmarks`

It returns the list of unmark tasks.

```
Here are the tasks in your list:
1.[T][ ] submit homework
2.[T][ ] shopping
```