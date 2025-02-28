# GPTZeroFive User Guide

GPTZeroFive is a command-line task manager that helps you efficiently manage tasks, deadlines, events, and notes through simple commands.

![alt text](Ui.png)

## Command Examples

Below are some example commands and their expected outputs:
 
- Adding a todo:
  ```
  Input: todo finish homework
  Output:
  Got it. I've added this task:
    [T][ ] finish homework
  Now you have 1 tasks in the list.
  ```

- Adding a deadline:
  ```
  Input: deadline submit report /by 16/10/2023 2359
  Output:
  Got it. I've added this task:
    [D][ ] submit report (by: 16/10/2023 2359)
  Now you have 2 tasks in the list.
  ```

- Adding an event:
  ```
  Input: event team meeting /from 17/10/2023 1000 /to 17/10/2023 1200
  Output:
  Got it. I've added this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  Now you have 3 tasks in the list.
  ```

- Marking a task as done:
  ```
  Input: mark 1
  Output:
  Nice! I've marked this task as done:
    [T][X] finish homework
  ```

- Deleting a task:
  ```
  Input: delete 2
  Output:
  Noted. I've removed this task:
    [D][ ] submit report (by: 16/10/2023 2359)
  Now you have 2 tasks in the list.
  ```

- Finding tasks by keyword:
  ```
  Input: find meeting
  Output:
  1. [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- Adding a note:
  ```
  Input: newNote 3 Remember to prepare slides
  Output:
  Got it. I've added a note to this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- Showing a note:
  ```
  Input: showNote 3
  Output:
  Remember to prepare slides
  ```

- Editing a note:
  ```
  Input: editNote 3 Prepare slides for the meeting
  Output:
  Got it. I've edited the note for this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```

- Deleting a note:
  ```
  Input: deleteNote 3
  Output:
  Got it. I've removed the note from this task:
    [E][ ] team meeting (from: 17/10/2023 1000 to: 17/10/2023 1200)
  ```
