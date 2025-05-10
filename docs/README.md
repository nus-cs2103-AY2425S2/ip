# Phil User Guide

![Image of Interface](Ui.png)

Phil is a chatbot that helps you track your tasks and notes.

The user types a command into the input text-box

## Managing Tasks

### Adding todo tasks

Add a Todo task that contains a description (with at least one word)
to the list of tasks.
Input format: `todo <description>`

Example Valid Input: `todo read`

```
Got it! I've added this task:
[T][ ] read
Now you have 5 tasks in the list.
```

Example Invalid Input: `todo`

```
Invalid arguments passed for creating tasks.
Details:
A Todo task requires a description of minimally one word.
For example, 'todo read' creates the task 'read'.
```

### Adding event tasks

Add an Event task that contains a description (with at least one word),
a "from" date (with at least one word) and a "to" date (with at least one word)
to the list of tasks.
Input format: `event <description> /from <start-date> /to <end-date>`

Note that the `<start-date>` and `<end-date>` specifically
accept dates in the format `M/d/yy HHmm`. If your input matches this format,
the date will be displayed in the format `MMM d, yyyy h.mma`.

Example Valid Input: `event tutorial /from today afternoon /to tonight`

```
Got it! I've added this task:
[E][ ] tutorial (from: today afternoon to: tonight)
Now you have 5 tasks in the list.
```

Example Valid Input: `event break /from 2/7/25 1000 /to 2/10/25 1100`

```
Got it! I've added this task:
[E][ ] break (from: Feb 7, 2025 10.00AM to: Feb 10, 2025 11.00AM)
Now you have 5 tasks in the list.
```

Example Invalid Input: `event tutorial /from today`

```
Invalid arguments passed for creating tasks.
Details:
An Event task requires a description AND a from date, 
specified as a string after '/by'.
And a to date, specified as a string after '/to'.
For example, 'event reading /from Monday /to Tuesday' 
creates the event 'reading'\n from 'Monday' to 'Tuesday'.
```

### Adding deadline tasks

Add a Deadline task that contains a description (with at least one word),
a "by" date (with at least one word).

Input format: `deadline <description> /by <deadline-date>`

Note that the `<deadline-date>`
accept dates in the format `M/d/yy HHmm`. If your input matches this format,
the date will be displayed in the format `MMM d, yyyy h.mma`.

Example Valid Input: `deadline assigment /by tomorrow`

```
Got it! I've added this task:
[D][ ] assignment (by: tomorrow)
Now you have 5 tasks in the list.
```

Example Valid Input: `deadline assignment /by 2/10/25 2200`

```
Got it! I've added this task:
[D][ ] assignment (by: Feb 10, 2025 10.00PM)
Now you have 5 tasks in the list.
```

Example Invalid Input: `deadline assignment`

```
Invalid arguments passed for creating tasks.
Details:
A Deadline task requires a description AND a deadline, specified as a string after '/by'.
For example, 'deadline read /by Tuesday' creates the task 'read' with a deadline of 'Tuesday'.
```

### List tasks in list

List all tasks created that have not been removed.

Input format: `list`

Example Valid Input: `list`

```
Here are the tasks in your list:
1. [T][ ] read
2. [E][ ] tutorial (from: today afternoon to: tonight)
3. [D][ ] assignment (by: Feb 10, 2025 10.00AM)
```

### Mark tasks as done

Mark tasks as done using their index, starting from 1.
You can check the index of the task, using the `list` command.

Input format: `mark <index-of-task>`

Example Valid Input: `mark 2`

```
Nice! I've marked the task as done:
[E][X] tutorial (from: today afternoon to: tonight)
```

Example Invalid Input: `mark 1000` where the list has 5 tasks.

```
Invalid arguments passed for marking or deleting tasks.
Details:
To mark a task as done, say 'mark X' where X is the task to mark as done.
Make sure X is a valid positive integer from 1 to 5 (number of tasks). 
For example, calling 'mark 2' marks the second task as done.
Say 'list' to see the tasks you have stored.
```

### Mark tasks as not done

Mark tasks as not done using their index, starting from 1.
You can check the index of the task, using the `list` command.

Input format: `unmark <index-of-task>`

Example Valid Input: `unmark 2`

```
OK, I've marked the task as not done:
[E][ ] tutorial (from: today afternoon to: tonight)
```

Example Invalid Input: `unmark 1000` where the list has 5 tasks.

```
Invalid arguments passed for marking or deleting tasks.
Details:
To mark a task as not done, say 'unmark X' where X is the task to mark as not done.
Make sure X is a valid positive integer from 1 to 5.
For example, calling 'unmark 2' marks the second task as not done.
Say 'list' to see the tasks you have stored.
```

### Delete tasks

Delete tasks using their index, starting from 1.
You can check the index of the task, using the `list` command.

Input format: `delete <index-of-task>`

Example Valid Input: `delete 2`

```
Noted, I've removed this task:
[E][ ] tutorial (from: today afternoon to: tonight)
Now you have 5 tasks in the list.
```

Example Invalid Input: `delete 1000` where the list has 5 tasks.

```
Invalid arguments passed for marking or deleting tasks.
Details:
To delete a task, say 'delete X' where X is the task to remove.
Make sure X is a valid positive integer from 1 to 5. 
For example, calling 'delete 2' deletes the second task.
Say 'list' to see the tasks you have stored.
```

### Find tasks

Find tasks using a search term (with at least one word).

Input format: `find <search-term>`

Example Valid Input: `find read`

```
Here are the matching tasks in your list:
1. [T][ ] read
```

Example Invalid Input: `find`
```
Invalid arguments passed for creating tasks.
Details:
Finding a task requires a search term of minimally one word.
For example, 'find hello' finds all tasks with the keyword 'hello'.
```

## Managing Notes

### Create notes

Create a note with a note (with at least one word).

Input format: `new-note <note>`

Example Valid Input: `new-note the sky is blue`

```
Got it! I've added this note:
the sky is blue
Now you have 2 notes in the list.
```

Example Invalid Input: `new-note`
```
Invalid arguments passed for creating tasks.
Details:
A Note requires a description of minimally one word.
For example, 'new-note the sky is blue' creates the note 'the sky is blue'.
```

### List notes

List all notes in the list of notes.

Input format: `list-note`

Example Valid Input: `list-note`

```
Here are the notes in your list:
1. the sky is blue
2. the earth is not flat
```

### Delete notes

Delete a note using its index.
You can check the index of a note using the `list-note` command.

Input format: `delete-note <index-of-note>`

Example Valid Input: `delete-note 2`

```
Noted. I've removed this note:
the earth is not flat
Now you have 2 notes in the list.
```

## Others

### End the conversation

End the conversation and save the tasks and notes.

Input format: `bye`

This closes the GUI window, saving the tasks and notes.
These will be loaded in the future.


## Side Note

The bot saves the details of the tasks and notes in the files `data/phil.txt`
and `data/philNotes.txt` respectively.
If these happen to be deleted or corrupted, the previously
saved tasks and notes cannot be restored.