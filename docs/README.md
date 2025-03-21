# Tom

> Hello! I am Tom!

Tom is a to do list bot to help you remember what you need to do!

## Using Tom

1. Ensure you have Java 17 installed on your computer
2. Download the latest `Tom.jar` from the release page
3. Open a command terminal and navigate to the folder containing the JAR file
4. Run the application with `java -jar Tom.jar`
5. Start managing your tasks with the commands listed in the Features section

## Features:

### Command Summary
1. list - Shows all tasks.
2. todo `<description>` - Adds a new todo task.
3. deadline `<description>` /by `<yyyy-MM-dd>` - Adds a deadline task.
4. event `<description>` /from `<yyyy-MM-dd>` /to `<yyyy-MM-dd>` - Adds an event.
5. mark `<task number>` - Marks a task as completed.
6. unmark `<task number>` - Marks a task as not done.
7. delete `<task number>` - Removes a task.
8. find `<keyword>` - Finds all tasks containing `<keyword>`.
9. help - Displays this help message.
10. bye - exits the program. 

### 1. Listing all tasks: `list`

Displays all tasks currently stored in Tom.

Format: `list`

Expected outcome:
```
Here are the tasks in your list:
  1.[T][X] Buy groceries
  2.[D][] Finish homework (by: Jan 15 2025)
```

### 2. Adding a todo task: 'todo'

Creates a simple task without a deadline 

Format: todo `<description>`

Example: 
* `todo Finish homework`
* `todo Group Project`

Expected outcome: 
```
Task added:
[T][] finish homework
There are now 1 tasks in the list.
```

### 3. Adding a deadline task: `deadline`

Creates a task with a specific deadline.

Format: deadline `<description>` /by `<yyyy-MM-dd>`

Acceptable date formats:
* `yyyy-MM-dd` (e.g., "2025-02-05")

Examples:
* `deadline Finish homework /by 2025-05-12`
* `deadline Group Project /by 2025-04-15`

Expected outcome:
```
Task added: 
  [D][] Finish homework (by: Jan 15 2025)
There are now 2 tasks in the list.
```

### 4. Adding an event: `event`

Creates a scheduled event with start and end times.

Format: event `<description>` /from `<yyyy-MM-dd>` /to `<yyyy-MM-dd>` 

Examples:
* `event Team meeting /from Jan 20 2025 /to Jan 20 2025 `

Expected outcome:
```
Task added: 
  [E][] Team meeting (from: Jan 20 2025 to: Jan 20 2025)
There are now 2 tasks in the list.
```

### 5. Marking a task as done: `mark`

Changes the status of a task to completed.

Format: mark `<task number>`

Example:
* `mark 1`

Expected outcome:
```
Well done, this task is completed!
  [T][X] Buy groceries
```

### 6. Unmarking a task: `unmark`

Changes the status of a completed task back to not done.

Format: unmark `<task number>`

Example:
* `unmark 1`

Expected outcome:
```
Let's work on this task! It is not done yet
  [T][] Buy groceries
```

### 7. Deleting a task: `delete`

Removes a task from your list.

Format: delete `<task number>`

Examples:
* `delete 2` (deletes the second task)

Expected outcome:
```
This task has been removed: [T][] Finish homework
There are 4 remaining tasks in the list.
```

### 8. Finding tasks: `find`

Searches for tasks containing specific keywords.

Format: find `<keyword>`

Examples:
* `find mee`

Expected outcome:
```
Here are the matching tasks found in your list:
  1.[T][X] Buy groceries
```

### 9. Help command: `help`

Displays all available commands with given usage 

Format: `help`

Expected outcome:
```
1. list - Shows all tasks.
2. todo `<description>` - Adds a new todo task.
3. deadline `<description>` /by `<yyyy-MM-dd>` - Adds a deadline task.
4. event `<description>` /from `<yyyy-MM-dd>` /to `<yyyy-MM-dd>` - Adds an event.
5. mark `<task number>` - Marks a task as completed.
6. unmark `<task number>` - Marks a task as not done.
7. delete `<task number>` - Removes a task.
8. find `<keyword>` - Finds all tasks containing `<keyword>`.
9. help - Displays this help message.
10. bye - exits the program. 
```

### 10. Exiting the program: `bye`

Saves all tasks and exits Tom.

Format: `bye`

Expected outcome:
```
[PROGRAM WILL TERMINATE]
```

## FAQ

**Q: Will Tom work on my operating system?**  
A: Tom is compatible with Windows, macOS, and Linux systems that have Java 17.

**Q: Are my tasks saved automatically?**  
A: Yes, tasks are automatically saved after each command. You can safely exit using the `bye` command, knowing your data is secure.
