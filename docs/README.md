# Lemon User Guide

![](D:\Ui.png)

Lemon is a chatbot for you to efficiently **manage your tasks via a Graphical User Interface (GUI)**.
Besides managing tasks, it also provides the user with **emotional support** when they feel stressed about completing their tasks. 
Try typing **"emotional"** and get some inspirational quotes!

Here are the features and the command format:

## Ask for help: `help`
Type `help` to how a help page like this:

```angular2html
I'm here! What kind of help do you need?
- Technical information: type 'technical'
- Emotional support: type 'emotional'
```
Type `technical` to show the help page containing the use of the commands

Type `emotional` to give you an encouraging quote!
## Adding a task: `todo`, `deadline`, `event`
Add a task of either type todo, deadline or event

**Format:**

`todo`: todo {task description}

Eg. `todo math assignment`

`deadline`: {task description} /by {deadline in YYYY-MM-DD}

Eg. `deadline coding assignment /by 22-02-2025`

`event`: {task description} /from {time} /to {time}

Eg. `event career fair /from 18 feb /to 20 fed`

**Here are the expected reply from lemon:**

```
👌 Got it. I've added this task:
[T][] math assignment
Now you have 1 task in the list.
```
```angular2html
👌Got it. I've added this task:
[D][] coding assignment /22 FEBRUARY 2025
Now you have 2 task in the list.
```
```angular2html
👌Got it. I've added this task:
[E][] career fair /18 feb /20 feb
Now you have 3 task in the list.
```


## Deleting a task: `delete`

Delete a task by specifying its index

**Format**

delete {task index}

Eg. `delete 3`

**Here is the expected reply:**
```angular2html
👌 Noted. I've removed this task:
[E][] career fair /18 feb /20 feb
Now you have 2 task in the list.
```

## Marking a task as done / not done: `mark`, `unmark`

Mark a task as done / not done by specifying its index

**Format**

mark {task index} , unmark {task index}

Eg. `mark 1`, `unmark 1`

**Here is the expected reply:**

```angular2html
Nice! I've marked this task as done:
[T][X] math assignment
```
```angular2html
👌 OK, I've marked this task as not done yet
[T][] math assignment
```
## List all tasks: `list`
List all the current tasks stored in the chatbot

Type `list` to list all the tasks

## Finding tasks: `find`
Find tasks with a specified keyword

**Format**

find {keyword}

Eg. `find assignment`

If there are tasks with the matching keyword, it will reply:
```angular2html
1. [T][] math assignment
2. [D][] coding assignment /22 FEBRUARY 2025
```

If not, it will reply:
```angular2html
🤔 Oh no! There is no task with this keyword.
```
## Exiting the chatbot: `bye`
Type `bye` to exit the chatbot

**Important Note**: Only when you entred `bye` the changes you made to the task list can be saved!
