# Alex User Guide

## Introduction

Alex is a task management application that helps you keep track of your tasks, deadlines, and events. It is designed to be easy to use and efficient, allowing you to manage your tasks quickly and effectively.

## Features


You can manage different types of tasks to your list, including **to-dos, deadlines, and events**. 

### Adding To-Dos
To-do task is any task without other information to keep track of other than if you have completed the task. To add a to-do task, use the following command:

**_todo (task description)_**

This adds a to-do task with the specified description to your list.

Eg. _todo buy shampoo_ --> this will add a to-do task with the description "buy groceries".

### Adding Deadlines

Deadlines are tasks that need to be completed by a certain date. To add a deadline task, use the following command:

**_deadline (task description) /by (date)_**

You can specify the date in the format YYYY-MM-DD as well so that Alex knows when exactly the task is due.

Eg. _deadline submit report /by 2025-02-12_ --> this will add a deadline "submit report" and due date 12 Feb 2025.

### Adding Events
Events are tasks that have a start and end time. To add an event task, use the following command:

**_event (task description) /from (time) /to (time)_**

Eg. _event project meeting /from 2pm /to 4pm_ --> this will add an event "project meeting" with start time 2pm and end time 4pm.

#### Listing Tasks

You can view all the tasks in your list by using the following command:

**_list_**

This will display all the tasks in your list, including the task type, description, completion status, and relevant time stamps.

### Marking and Unmarking Tasks

You can mark any task as complete or unmark any task as not complete. Simply type the task index after the command. 

_**mark (task index)**_

**_unmark (task index)_**

Eg. _mark 2_ marks the task number 2 in the list as complete. _unmark 2_ marks the task number 2 as not complete again. 

When a task is complete, a _X_ will appear when you view the task.

### Deleting Tasks

You can delete any task easily similar to marking their completion status. Use this command:

**_delete (task index)_**

This deletes the task with the index from the list.

Eg. _delete 3_ deletes the task number 3.

### Finding Tasks

When you have too many tasks to keep track of, try the find command!

**_find (keyword)_**

This will give you all the tasks that contain the keyword.

Eg. _find lab_ will find you all the tasks with descriptions containing the keyword lab.

### Mass Operations

Handling tasks one by one is tiring right? You can use mass operations to delete, mark and unmark tasks.

_**(mark/unmark/delete) (starting index)-(ending index)**_

Eg. _mark 3-6_ will mark tasks with indices between 3 and 6 as complete.