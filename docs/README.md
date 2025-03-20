# Ujin User Guide

![Alt text](https://github.com/Bill-1/ip/blob/master/docs/Ui.png)

## Introduction
Ujin is a chatbot for marking your activities.

## Messages you can write
You can add tasks to your tasklist with the commands:
### Adding todo task
Ujin lets you add todo task without any deadline.
#### Usage:
`todo <your task>`
#### Example:
`todo clean the house`
### Adding deadline task
Ujin lets you add deadline task with specific deadline.
#### Usage:
`deadline <your task> /by <time>`
#### Example:
`deadline clean the house /by 03/18`
### Adding event task
Ujin lets you mark an event between specific time period.
#### Usage:
`event <your task> /from <time> /to <time>`
#### Example:
`event clean the house /from 03/12 /to 03/31`

**Notice that &lt;time&gt; has to be in format MM/DD.**

### Listing all the tasks
Ujin lists the all the tasks.
#### Usage:
`list`

### Marking task as done
Ujin checks the task as done.
#### Usage:
`mark <index>`
#### Example:
`mark 1`

### Unmarking task as done
Ujin unchecks the task as not done.
#### Usage:
`unmark <index>`
#### Example:
`unmark 1`
### Delete the task
Ujin deletes the task from the list.
#### Usage:
`delete <index>`
#### Example:
`delete 1`

### Finds the task
Ujin lets you find the tasks with the desired keyword.
#### Usage:
`find <keyword>`
#### Example:
`find clean`

Note that &lt;index&gt; is 1-indexed number and has to be less than or equal to the number of tasks in your tasklist.

Enjoy the app!

