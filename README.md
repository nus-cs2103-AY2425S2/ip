# Carolyn Chatbot

<img width="399" alt="Ui" src="https://github.com/user-attachments/assets/fa5405fa-057b-4feb-bd2f-97dfa599a9ab" />



This chatbot can add 3 different types of tasks: todo, event and deadline, and for each task, we can mark and unmark, delete and tag it.
In addition, we can list all the tasks in the list and find a task using keyword in the description.

## Adding todos

Add a task that needs to be done.

The command should be of the form: todo {task_content}

For Example: `todo sayHi`

Suppose the list initially has no task, then you will get output from the bot:

```
Got it. I've added this task:
[T][] sayHi
Now you have 1 tasks in the list.
```

## Adding deadlines

Add a task that has deadline.

The command should be of the form: deadline {task_content} /by {yyyy-mm-dd}
The month and day must be of two digits.

For Example: `deadline eatCake /by 2025-03-03`

Suppose the list initially has only 1 task, then you will get output from the bot:

```
Got it. I've added this task:
[D][] eatCake (by Mar 3 2025)
Now you have 2 tasks in the list.
```

## Adding events

Add a task(event) that happens on a specific duration.

The command should be of the form: event {task_content} /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM

For Example: `event meeting /from 2025-11-10 13:10 /to 2025-11-10 14:10`

Suppose the list initially has 2 tasks, then you will get output from the bot:

```
Got it. I've added this task:
[E][] meeting  (from Nov 10 2025 01:10 PM to: Nov 10 2025 02:10 PM)
Now you have 3 tasks in the list.
```

## Mark/Unmark a task

Mark a task as done or not done.

The command should be of the form: mark/unmark {task_index}

For Example: `mark 1`

Suppose the first task is sayHi and of type todo, then you will get output from the bot:

```
Nice! I've marked this task as done:
[T][X] sayHi
```

For Example: `unmark 1`

```
Ok! I've marked this task as not done yet:
[T][] sayHi
```

## Tag a task

Add a tag to a task

The command should be of the form: tag {task_index} {tag_string}

For Example: `tag 1 love`

Suppose the first task is sayHi and of type todo, then you will get output from the bot:

```
Got it. I've added this tag "love" to the task:
[T][] sayHi #love
```

## Delete a task

Mark a task as done or not done.

The command should be of the form: delete {task_index}

For Example: `delete 1`

Suppose the first task is sayHi and of type todo, then you will get output from the bot:

```
Noted. I've removed this task:
[T][X] sayHi
```

## List all tasks

List all tasks in the list.

The command should be of this form: list

For Example: `list`

Suppose there are two tasks in the list, as the result of executing all the commands from above, 
then you will get output from the bot:

```
1.[D][] eatCake (by Mar 3 2025)
2.[E][] meeting  (from Nov 10 2025 01:10 PM to: Nov 10 2025 02:10 PM)
```

## Find a task

Find a tag using keyword in the description.

The command should be of this form: find {keyword}
The keyword is case-sensitive.

For example: `find meeting`

Suppose the list content is same as above, then you will get output from the bot:

```
1.[E][] meeting  (from Nov 10 2025 01:10 PM to: Nov 10 2025 02:10 PM)
```
