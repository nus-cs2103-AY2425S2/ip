# ThoughtBot
![Screenshot of an example use of ThoughtBot.](docs/Ui.png)
> Thought about it? ThoughtBot it.

:rocket: **ThoughtBot** is the all-new CLI-based task management chatbot for all your tracking needs. It currently has the following features:
- :grey_question: Helpful replies with suggestions on typos
- :pencil2: Easy to pick up
- :memo: Memory between multiple sessions

Just follow the below steps to use it right now!
1. Download the release file from [here](https://github.com/deseansoh/ip/releases/tag/A-Jar)
2. Launch the app
3. Explore and use ThoughtBot 👍

Current _planned features_ include:
- [x] 'Find' feature: Finding tasks based on their task descriptions
- [x] Adding a GUI using JavaFX

# Current Available Commands
### list
Lists the current tasks that you are tracking

Usage: `list`
### todo
Add a new normal ToDo task, with no special parameters

Usage: `todo <task description>`
### deadline
Add a new Deadline task, with a specific deadline to be completed by

Usage: `deadline <task description> /by YYYY-MM-DD HH:MM`
### event
Add a new Event task, with specific to and from times for the event

Usage: `event <task description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM`
### mark/unmark
Mark or unmark a task as done

Usage: `mark/unmark <task index number>`
### delete
Remove a task from your list of tasks

Usage: `delete <task index number>`
### find
Find a task that has a part of the search string

Usage: `find <search string>`
### remind
View all the deadline and event tasks that are due/happening within this week

Usage: `remind`
## bye
Exit the application
Usage: `bye`