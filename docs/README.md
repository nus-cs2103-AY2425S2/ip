# Nguyen Chatbot User Guide

Welcome to the Nguyen Chatbot User Guide. This guide will help you understand and utilize all the features of the Nguyen Chatbot effectively.

## Introduction

Nguyen is a personal task management chatbot designed to help you keep track of your tasks efficiently. With Nguyen, you can add, delete, mark, unmark, find, and sort tasks seamlessly through simple commands.

## Features

Nguyen supports the following features:

1. **List Tasks**: Display all current tasks.
2. **Add Tasks**: Add different types of tasks:
    - Todo
    - Deadline
    - Event
3. **Delete Tasks**: Remove tasks from the list.
4. **Mark Tasks**: Mark tasks as completed.
5. **Unmark Tasks**: Mark tasks as not completed.
6. **Find Tasks**: Search for tasks containing specific keywords.
7. **Sort Tasks**: Sort tasks by type.

## Command Summary

Here's a quick overview of the commands you can use:

- `list`: Display all tasks.
- `todo <description>`: Add a Todo task.
- `deadline <description> /by <date/time>`: Add a Deadline task.
- `event <description> /from <date/time> /to <date/time>`: Add an Event task.
- `delete <index>`: Delete the task at the specified index.
- `mark <index>`: Mark the task at the specified index as completed.
- `unmark <index>`: Mark the task at the specified index as not completed.
- `find <keyword>`: Find tasks containing the keyword.
- `sort <taskType>`: Sort tasks by the specified type (`todo`, `deadline`, `event`).
- `bye`: Exit the chatbot.

## Usage Examples

### 1. Listing All Tasks

**Command**: `list`

**Description**: Displays all tasks in your list with their current status.

**Example**:

```
> list
Alright, there are tasks in your list:
1. [T][ ] Meet Friends
2. [D][X] Do Competitive Programming (by: Dec 2 2019)
3. [E][ ] Learn CS2103T (from: Dec 2 2019 to: Dec 2 2019)
```

### 2. Adding a Todo Task

**Command**: `todo <description>`

**Description**: Adds a new Todo task with the given description.

**Example**:

```
> todo Buy groceries
Cool, I added this to your list:
[T][ ] Buy groceries
Now you have 1 thing to do
```

### 3. Adding a Deadline Task

**Command**: `deadline <description> /by <date/time>`

**Description**: Adds a new task with a deadline.

**Example**:

```
> deadline Finish assignment /by Dec 5 2019
Cool, I added this to your list:
[D][ ] Finish assignment (by: Dec 5 2019)
Now you have 1 thing to do
```

### 4. Adding an Event Task

**Command**: `event <description> /from <date/time> /to <date/time>`

**Description**: Adds a new event that spans a specific time period.

**Example**:

```
> event Attend meeting /from Dec 3 2019 /to Dec 3 2019
Cool, I added this to your list:
[E][ ] Attend meeting (from: Dec 3 2019 to: Dec 3 2019)
Now you have 1 thing to do
```

### 5. Deleting a Task

**Command**: `delete <index>`

**Description**: Removes the task at the specified index.

**Example**:

```
> delete 2
Chill, I got rid of this for you:
[D][ ] Do Competitive Programming (by: Dec 2 2019)
You have got 1 task left No rush
```

### 6. Marking a Task as Completed

**Command**: `mark <index>`

**Description**: Marks the task at the specified index as completed.

**Example**:

```
> mark 1
Nice, that is one less thing to worry about:
[T][X] Buy groceries
```

### 7. Unmarking a Task

**Command**: `unmark <index>`

**Description**: Marks the task at the specified index as not completed.

**Example**:

```
> unmark 1
Alright, I will pretend that did not happen:
[T][ ] Buy groceries
```

### 8. Finding Tasks

**Command**: `find <keyword>`

**Description**: Searches for tasks that contain the specified keyword.

**Example**:

```
> find meeting
Here is what I found for meeting:
1. [E][ ] Attend meeting (from: Dec 3 2019 to: Dec 3 2019)
```

### 9. Sorting Tasks

**Command**: `sort <taskType>`

**Description**: Sorts tasks by the specified type (`todo`, `deadline`, `event`).

**Example**:

```
> sort deadline
Alright, here is your sorted deadline list:
1. [D][ ] Do Competitive Programming (by: Dec 2 2019)
2. [D][ ] Finish assignment (by: Dec 5 2019)
```

### 10. Exiting the Chatbot

**Command**: `bye`

**Description**: Exits the chatbot.

**Example**:

```
> bye
Nguyen Chat Bot has been terminated.
```

## Notes

- Ensure that task descriptions and commands are entered as shown to avoid errors.

By following this guide, you should be able to utilize all the features of the Nguyen Chatbot effectively. If you encounter any issues or have questions, feel free to refer to this guide or seek further assistance.

