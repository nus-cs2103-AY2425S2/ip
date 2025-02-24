# MIKI User Guide

# Contents

- [Introduction](#introduction)
- [Commands](#commands)
- [Adding todos](#adding-todos)
- [Adding deadlines](#adding-deadlines)
- [Adding events](#adding-events)
- [Listing tasks](#listing-tasks)
- [Finding tasks](#finding-tasks)
- [Deleting tasks](#deleting-tasks)
- [Marking tasks as done](#marking-tasks-as-done)
- [Unmarking tasks](#unmarking-tasks)
- [Exit the chatbot](#exit-the-chatbot)
- [Saved tasks between sessions](#saved-tasks-between-sessions)
- [FAQ](#faq)

# Introduction

![MIKI logo](images/DaDog.png)
_Your DAWG Chatbot_

Get yourself a real dawg that helps you settle all your tasks and what not.

- GUI-based
- Easy to learn
- No fuss

All you need to do is,

1. Download.
2. Open.
3. Say your tasks.
4. Let MIKI do the job for you.
   Settle Kettle bro.

Everyone loves MIKI. 🐕

# Commands

The format for commands is as follows:

```
COMMAND {ARGS}
```

The detailed breakdown for each one is as follows below.

Things to note:

- Follow the order of the parameters.

## Adding todos `todo`

Add a todo task to let Miki keep track of your tasks. Dawg's got your back.

Format: `todo {DESCRIPTION} /tags {TAG}`

Example: `todo Buy groceries /tags shopping`

Adds a todo task with the given description and any additional tags provided (with /tag {TAG}).
You can put as many tags as you like.

![MIKI todo](images/todo.png)

## Adding deadlines `deadline`

Add a deadline task to let Miki remind you that you have a deadline to complete. Dawg won't let you down.

Format: `deadline {DESCRIPTION} /by {DEADLINE DATE} /tags {TAG}`

Example: `deadline Finish my homework /by 25-03-2025 18:00 /tags school`

Adds a deadline task with the given description and deadline, as well as any additional tags provided (with /tag {TAG}).
You can put as many tags as you like.

![MIKI deadline](images/deadline.png)

## Adding events `event`

Add an event task to let Miki remind you of your events. Dawg won't let you miss it.

Format: `event {DESCRIPTION} /from {START DATE} /to {END DATE} /tags {TAG}`

Example: `event Attend meeting /from 25-03-2025 14:00 /to 25-03-2025 16:00 /tags work`

Adds an event task with the given description and event date, as well as any additional tags provided (with /tag {TAG}).
You can put as many tags as you like.

![MIKI event](images/event.png)

## Listing tasks `list`

List all the tasks that Miki is currently keeping track of. Dawg's got everything under control.

Format: `list`

Displays all the tasks in the order they were added.

![MIKI list](images/list.png)

## Finding tasks `find`

Find tasks that match a given keyword. Dawg will find it for you.

Format: `find {KEYWORD}`

Example: `find groceries`

Displays all tasks that contain the given keyword in their description.

![MIKI find](images/find.png)

## Deleting tasks `delete`

Delete a task that Miki is keeping track of. Dawg will let it go.

Format: `delete {TASK NUMBER}`

Example: `delete 2`

Deletes the task with the given task number.

![MIKI delete](images/delete.png)

## Marking tasks as done `mark`

Mark a task as done to let Miki know you've completed it. Dawg will be proud.

Format: `mark {TASK NUMBER}`

Example: `mark 1`

Marks the task with the given task number as done.

![MIKI mark](images/mark.png)

## Unmarking tasks `unmark`

Unmark a task to let Miki know it's not done yet. Dawg will understand.

Format: `unmark {TASK NUMBER}`

Example: `unmark 1`

Unmarks the task with the given task number.

![MIKI unmark](images/unmark.png)

## Exit the chatbot `bye`

Say goodbye to Miki and close the chatbot. Dawg will be sad but he understands.

Format: `bye`

Closes the chatbot.

![MIKI bye](images/bye.png)

## Saved tasks between sessions

Your tasks are automatically saved between sessions, so you don't have to worry about losing your data. Dawg's got you covered.

Whenever you start MIKI, it will load all your previously saved tasks, ensuring you can pick up right where you left off.

### FAQ

#### How do I install MIKI?

To install MIKI, simply download the latest release from the [GitHub repository](https://github.com/your-repo/miki/releases) and run the executable file.

#### What are the system requirements for MIKI?

MIKI requires Java 11 or later to be installed on your system. It is compatible with Windows, macOS, and Linux.

#### How do I update MIKI?

To update MIKI, download the latest version from the [GitHub repository](https://github.com/your-repo/miki/releases) and replace the old executable file with the new one.

#### Can I use MIKI offline?

Yes, MIKI can be used offline. All your tasks are stored locally on your device.

#### How do I report a bug or request a feature?

You can report bugs or request features by opening an issue on the [GitHub repository](https://github.com/your-repo/miki/issues).

#### Is my data safe with MIKI?

Yes, your data is stored locally on your device and is not shared with any third parties.

#### Can I customize the tags for my tasks?

Yes, you can add any tags you like to your tasks using the `/tags` option in the command format.

#### How do I delete all tasks?

Currently, MIKI does not support a command to delete all tasks at once. You will need to delete each task individually using the `delete` command.

#### What should I do if MIKI is not responding?

If MIKI is not responding, try restarting the application. If the issue persists, please report it on the [GitHub repository](https://github.com/your-repo/miki/issues).

#### How do I backup my tasks?

To backup your tasks, simply copy the data file located in the same directory as the MIKI executable to a safe location.
