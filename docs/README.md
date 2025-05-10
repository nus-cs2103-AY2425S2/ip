# Ben User Guide

Ben is a chatbot that helps you organize your tasks
through the power of the Omnitrix

> “The best way to be prepared for the future is to take control of the present.”
> – Ben Tennyson

## Adding TODO

For a todo task, use: todo [task description]

Example: `todo Buy groceries`


```
Got it.I've added this task:
 [T][ ] Buy groceries
Now you have 6 tasks in the list
```

## Adding Deadline

For a deadline task, use: deadline [task description] /by [yyyy-MM-dd HH:mm]

Example: `deadline Submit report /by 2025-03-15 23:59`

```
Got it.I've added this task:
 [D][ ] Submit report (by: Mar 15 2025 11:59 pm)
Now you have 7 tasks in the list
```

## Adding Event

For an event, use: event [event description]
/from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]

Example: `event Team meeting /from 2025-03-15 14:00 /to 2025-03-15 15:30`

```
Got it.I've added this task:
 [E][ ] Team meeting (from: Mar 15 2025 02:00 pm to: Mar 15 2025 03:30 pm)
Now you have 8 tasks in the list
```

## Mark

To mark a task, use: mark [index]

Example: `mark 6`

```
Nice! I've marked this task as done:
 [T][X] Buy groceries
```

## Unmark

To unmark a task, use: unmark [index]

Example: `unmark 6`

```
OK, I've marked this task as not done yet:
 [T][ ] Buy groceries
```

## Delete

To delete a task, use: delete [index]

Example: `delete 6`

```
Noted. I've removed this task:
 [T][ ] Buy groceries
Now you have 7 tasks in the list.
```

## Find

To find a task, use: find [term]

> Note : find is case-sensitive

Example: `find meeting`

```
Here are the matching tasks in your list:
1.[E][ ] Team meeting (from: Mar 15 2025 02:00 pm to: Mar 15 2025 03:30 pm)
```

## Update

To update a task, use: update [section] of [index] to [new_value]

Example: `update des of 2 to Buy groceries`

```
Task Updated Successfully!
Previous Task:
 [T][ ] return book
Updated Task:
 [T][ ] Buy groceries
```

## Bye

To exit the application, you can either close the GUI directly or use: bye

Example: `bye`

```
Bye. Hope to see you again soon!
```

> Note : do not manually edit ben.txt, it is yet to process invalid inputs from storage