# NickiMinaj User Guide

// Product screenshot goes here

## Introduction

NickiMinaj is the ultimate task management queen, helping you slay your to-dos, deadlines, and events effortlessly. With a sleek command-line interface, managing tasks has never been more fabulous!

## Adding Deadlines

To set a deadline, command NickiMinaj like a boss:

Example: `deadline <task description> /by <dd/MM/yyyy HHmm>`

### Expected Outcome:
NickiMinaj acknowledges your deadline with:

```
Ooo, a new task? Slay! Adding it to the list 📝✨
  [D][ ] <task description> (by: <formatted date>)
Now you have X tasks in the list.
```

## Adding To-Do Tasks

Example: `todo <task description>`

### Expected Outcome:
```
Ooo, a new task? Slay! Adding it to the list 📝✨
  [T][ ] <task description>
Now you have X tasks in the list.
```

## Adding Events

Example: `event <task description> /at <dd/MM/yyyy HHmm>`

### Expected Outcome:
```
Ooo, a new task? Slay! Adding it to the list 📝✨
  [E][ ] <task description> (from: <formatted start date> to: <formatted end date>)
Now you have X tasks in the list.
```

## Listing Tasks

Example: `list`

### Expected Outcome:
```
Chile, let’s see what’s giving today. Here's your Tasks:
1. [T][ ] Task A
2. [D][ ] Task B (by: dd/MM/yyyy HHmm)
3. [E][ ] Task C (from: dd/MM/yyyy HHmm to: dd/MM/yyyy HHmm)
```

## Marking Tasks as Done

Example: `mark <task number>`

### Expected Outcome:
```
Okay queen, I gotchu! Task marked ✅
  [✓] <task description>
```

## Unmarking Tasks 

Example: `unmark <task number>`

### Expected Outcome:
```
Unmarked! Ain’t nobody got time for that. ⏳
  [ ] <task description>
```

## Deleting Tasks

Example: `delete <task number>`

### Expected Outcome:
```
Deleted! We don’t need that negativity. ❌💅
  [T][ ] <task description>
Now you have X tasks in the list.
```

## Getting Inspired

Example: `inspire`

### Expected Outcome:
```
You wanna know what scares people? Success. You wanna know what scares me? Running out of lashes before a big event.
```

## Exiting the Application

Example: `bye`

### Expected Outcome:
```
Imma run away Imma run away aye aye
```

---

This guide ensures you and NickiMinaj stay on top of every task with style, sass, and supreme efficiency!

