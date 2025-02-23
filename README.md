# Julie the Chatbot

> Need help planning and remembering all your tasks? 
> Julie is here to help!

![Ui.png](Ui.png)

Julie is your friendly chatbot who helps you keep track
of all your tasks, from simple to-do's to deadlines you have to
meet, and even important events!

## Adding To-do's 

This feature helps you add simple to-do's that don't have a 
set deadline.

Be it collecting a parcel or meeting up with a dear friend, 
you can add todo's to help you remember these small tasks.

Format: `todo <description>`

Example: `todo Buy milk`

The todo is added to your list in the format:
```
[L] [T] [ ] Buy milk
```

## Adding Deadlines

This feature helps you add tasks with set deadlines, like homework and
project submissions.

Format: `deadline <description> /by DD-MM-YYYY HHMM`

Example: `deadline Quiz 1 /by 10-03-2025 2359`

The deadline-task is added to your list in the format:
```
[L] [D] [ ] Quiz 1 (by: Mar 10 2025, 11:59 pm)
```


## Adding Events

This feature helps you add important events with a start and end time. 

Career Fair to attend? Or maybe a conference? It could even be your 
orientation event that lasts several days. Use the `event` command to
note them down.

Format: `event <description> /from DD-MM-YYYY HHMM 
/to DD-MM-YYYY HHMM`

Example: `event UTR Floor-Bonding event /from 18-02-2025 1800 
/to 18-02-2025 2200`

The event is added to your list in the format: 
```
[L] [E] [ ] UTR Floor-Bonding event (from: Feb 18 2025, 06:00 pm to: 
Feb 18 2025 10:00 pm)
```

## Viewing All Your Tasks

The `list` feature helps you view all the tasks that you have added 
to your list so far. 

Format: `list`

Your list is shown in the format: 
```
Here are your tasks: 
1. [L] [T] [ ] Buy milk
2. [L] [D] [ ] Quiz 1 (by: Mar 10 2025, 11:59 pm)
3. [L] [E] [ ] UTR Floor-Bonding event (from: Feb 18 2025, 06:00 pm to: 
Feb 18 2025 10:00 pm)
```

## Deleting Tasks

The `delete` feature allows you to remove any unwanted tasks from your
list.

Format: `delete <index-number of task>`

Example: `delete 2`

The deletion is confirmed in the format:
```
Noted, the following task has been removed:
[L] [D] [ ] Quiz 1 (by: Mar 10 2025, 11:59 pm)
Now you have 2 tasks in the list.
```

## Marking Tasks as Done

The `mark` feature allows you to mark completed tasks as such. 

Format: `mark <index-number of task`

Example: `mark 1`

The marking of the task is confirmed in the format:
```
Nice! I've marked this task as done!
1. [L] [T] [X] Buy milk
```

## Marking Tasks as Undone

Marked an incorrect task as done? The `unmark` feature allows you to mark a 
task as undone to rectify the error. 

Format: `unmark <index-number of task>`

Example: `unmark 1`

The unmarking of the task is confirmed in the format:
```
Okay, I have marked this task as undone!
1. [L] [T] [ ] Buy milk
```

## Finding tasks

When your list is too long, and you want to find a particular task, you can
search for it using keywords or phrases.

Format: `find <key phrase>`

Example: `find UTR`

A list of all the tasks containing the word or phrase will be displayed in the
format:
```
Here are the matching tasks in your list: 
3. [L] [E] [ ] UTR Floor-Bonding event (from: Feb 18 2025, 06:00 pm to: 
Feb 18 2025 10:00 pm)
```

## Prioritising tasks 

Tasks of High, Medium or Low priority can be marked as such.

Format: `priority <index-number of task> <H/M/L>`

Example: `priority 2 H`

The priority change will be confirmed in the format:
```
Okay! Priority for task 2 set to H.
```

## Exiting the conversation 

Once you're done managing your tasks, you can exit the conversation using the
`bye` command. 
