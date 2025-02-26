# Krypto

Krypto is a task management chatbot designed for users who prefer typing commands. It supports three types of tasks: `todo`, `deadline`, and `event`.


![Gallery](./Ui.png)


## Installation

1. Download the latest jar from [Krypto](https://github.com/ToxOptimism/ip/releases/) releases.
2. Double-click the jar file or open a terminal, navigate to the directory where Krypto is downloaded, and run:

```bash
java -jar krypto.jar
```

## Features and User Guide

Krypto supports the following commands:

`todo`, `deadline`, `event`, `reschedule`, `list`, `find`, `delete`, `mark`, `unmark`, `show`, `bye`

### Adding Todos

Adds a todo with a specified description.

**Command format:**
```text
todo <description>
```

**Example:**
```text
todo return book
```

**Response:**
```
I've added this task:
[T][ ] return book
Now you have 1 task in the list!
```

### Adding Deadlines

Adds a deadline with a specified description and due date.

**Command format:**
```text
deadline <description> /<DateTime>
```

* `DateTime` must be in `yyyy-MM-dd HH:mm` format.

**Example:**
```text
deadline return book /2019-12-02 18:00
```

**Response:**
```
Got it. I've added this task:
[D][ ] return book (by: Dec 2 2019 6pm)
Now you have 1 task in the list.
```

### Adding Events

Adds an event with a specified description, start time, and end time.

**Command format:**
```text
event <description> /<fromDateTime> /<toDateTime>
```

* `fromDateTime` and `toDateTime` must be in `yyyy-MM-dd HH:mm` format.

**Example:**
```text
event project meeting /2019-12-02 18:00 /2019-12-04 08:00
```

**Response:**
```
Got it.I've added this task:
[E][ ] project meeting (from Dec 2 2019 6pm - to Dec 4 2019 8am)
Now you have 2 tasks in the list.
```

### Rescheduling a task (Deadline or Event)

Reschedules a task that must be completed within a specified period.

**Command format:**
```text
reschedule <index> -><startDateTime>,<endDateTime>
```

* `startDateTime` and `endDateTime` must be in `yyyy-MM-dd HH:mm` format.

**Example:**
```text
reschedule 3 ->2019-12-02 18:00,2019-12-04 08:00
```

**Response:**
```
Great! I've rescheduled this task:
[E][ ] project meeting(from Dec 2 2019 6pm - to Dec 4 2019 8am)
```

### Listing All Tasks

Displays all tasks in the task list.

**Command format:**
```text
list
```

**Response:**
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by Dec 2 2019 6pm)
3. [E][ ] project meeting (from Dec 2 2019 6pm - to Dec 4 2019 8am)
```

### Deleting a Task

Removes a task using its `index` from the `list` command.

**Command format:**
```text
delete <index>
```

**Example:**
```text
delete 1
```

**Response:**
```
Got it. I've removed this task:
[T][X] borrow book
Now you have 2 tasks in the list.
```

### Marking a Task as Complete

Marks a task as completed using its `index`.

**Command format:**
```text
mark <index>
```

**Example:**
```text
mark 1
```

**Response:**
```
Nice! I've marked this task as done:
[T][X] borrow book
```

### Unmarking a Task

Marks a task as not done using its `index`.
**Command format:**
```text
unmark <index>
```

**Example:**
```text
unmark 1
```

**Response:**
```
Ok! I've marked this task as not done yet:
[T][ ] borrow book
```

### Finding a Task

Finds all tasks containing a specified keyword in their descriptions.

**Command format:**
```text
find <keyword>
```

**Example:**
```text
find book
```

**Response:**
```
Looking for tasks with book
1. [T][ ] read book
2. [D][ ] return book (by Feb 03 2024 12pm)
```

### Showing Tasks for a Specific Date

Displays all tasks scheduled for a given date.

**Command format:**
```text
show <yyyy-MM-dd>
```

**Example:**
```text
show 2019-08-06
```

**Response:**
```
Fetching your tasks on Aug 06 2019
3. [E][ ] project meeting (from Aug 06 2019 2pm - to Aug 06 2019 4pm)
```

### Exiting

Closes the program.

**Command format:**
```text
bye
```

**Response:**
```
Great talking to you!
