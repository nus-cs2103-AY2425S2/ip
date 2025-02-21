# AquaDem User Guide

// Product screenshot goes here

Aquadem is your chatbot for all your needs!
It keeps track of your tasks for you and can store various 
types of Tasks!

You can view your task list!

- List out tasks 🗂️

These are the tasks it can add for you!

- Add Deadlines 🎯
- Add Events 🗓️
- Add Todos 📋
- Add Doafters 👋


There are some other features as well!


- Find a task 🔎
- Mark and unmark a Task ✅
- Delete a Task ␡


## Viewing tasks

### Listing tasks

You can list our your current tasks using the `list` command

An example using `list`
```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
3: [T][ ] homework3
4: [A][ ] homework4  (after: 20th January 2026)
```

## Adding tasks
Add tasks to the task list and keep updating it using the
below commands. The names are self-explanatory and the 
commands are even easier to follow.

### Adding deadlines

Add a deadline to the task list. 

Format: `deadline (your task) /by (dodate)`

Note: The date should be in the `yyyy-MM-dd HH:mm` format.
If not the date will be in a weeks time by default.

An example using `list` (now the list is empty)
```

```

followed by `deadline homework1 /by 2025-12-12 12:10`

```
Okay : ), added: [D][ ] homework1  (by: 2025-12-12 12:10)
You have 1 tasks in the list ;)
```

followed by `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
```

### Adding events

Add an event to the task list.

Format: `event (your task)  /from (startdate) /to (finishdate)`

Note: Date formatting is not yet supported here , so any text
will work.

An example using `list`
```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
```
followed by `event homework2 /from today /to tommorow`

```
Okay : ), added: [E][ ] homework2  (from: today to: tommorow)
You have 2 tasks in the list ;)
```

followed by `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
```



### Adding todos

Add a todo to the task list.

Format: `todo (your task)`

An example using `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
```

followed by `todo homework3`

```
Okay : ), added: [T][ ] homework3
You have 3 tasks in the list ;)

```

followed by `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
3: [T][ ] homework3
```


### Adding doafters

Add a doafter to the task list.

Format: `doafter (your task) /after (afterdate)`

Note: Date formatting is not yet supported here , so any text
will work.

An example using `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
3: [T][ ] homework3
```

followed by `doafter homework4 /after 20th January 2026`

```
Okay : ), added: [A][ ] homework4  (after: 20th January 2026)
You have 4 tasks in the list ;)
```

followed by `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
3: [T][ ] homework3
4: [A][ ] homework4  (after: 20th January 2026)
```


## More features

### Find a task

Search for a task in the task list.

Format: `find (string)`

An example using `find homework2`

```
Here are the tasks matching your search: 
1: [E][ ] homework2  (from: today to: tommorow)
```

### Mark and Unmark tasks

Mark/Unmark a task in the task list.

Format: `unmark/mark (tasknumber)`

An example using `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][ ] homework2  (from: today to: tommorow)
3: [T][ ] homework3
4: [A][ ] homework4  (after: 20th January 2026)
```
followed by `mark 2`

```
Task marked: [E][X] homework2  (from: today to: tommorow)
```

followed by `list`
```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][X] homework2  (from: today to: tommorow)
3: [T][ ] homework3
4: [A][ ] homework4  (after: 20th January 2026)
```

Unmark removes the mark if a task is marked or does nothing otherwise.

### Deleting a task

Delete a task from the task list.

Format: `delete (tasknumber)`

An example using `list`

```
1: [D][ ] homework1  (by: 2025-12-12 12:10)
2: [E][X] homework2  (from: today to: tommorow)
3: [T][ ] homework3
4: [A][ ] homework4  (after: 20th January 2026)
```

followed by `delete 1`

```
Okay : ), deleted: [D][ ] homework1  (by: 2025-12-12 12:10)
You have 3 tasks in the list ;)
```

followed by `list`

```
1: [E][X] homework2  (from: today to: tommorow)
2: [T][ ] homework3
3: [A][ ] homework4  (after: 20th January 2026)
```
