# Nikingoda Chatbot 🌟
Welcome to Nikingoda, a powerful chatbot designed to help you managing tasks efficiently.


Nikingoda frees your mind of having to remember things you need to do. It's,
text-based
easy to learn
~~FAST~~ SUPER FAST to use.


## Features of the Project 🛠️
- [x] *Task Management:* Managing tasks (todo, deadline, event)
- [x] *Note done tasks* Mark and unmark done/not done task (mark, unmark)
- [x] *List task:* List all tasks (list)
- [x] *Find tasks easily:* Find tasks with keyword (find)
- [x] *Update task details easily:* Edit task details easily (update)
- [x] *File Storage:* Save and load tasks from a file on disk.
- [ ] Reminders (coming soon)


## How to Run the Project 🚀
1. [Download](https://github.com/nikingoda/ip/releases/tag/A-Release) the latest release in GitHub
2. Open the terminal and run the project:
   java -jar nikingoda.jar, or click the jar file to run the project.

## Supported Commands 📋
Here’s a list of commands Nikingoda recognizes:
- list: Display all tasks.
- todo <description> : Add todo task with description
- deadline <description> /by <deadline> : Add deadline task 
- event <description> /from <begin_time> /to <end_time> : Add event task
- delete <id> : Delete task with given id.
- mark <id>, unmark <id>: mark or unmark the task with id as done.
- find <keyword>: find tasks match with given keyword.
- update <id> /description <new_description>: update description of task with id
- update <id> /by <new_deadline> : update deadline of task with id
- update <id> /from <new_begin> : update begin_time of task with id
- update <id> /to <new_end> : update end_time of task with id
- bye: exit the chatbot.

**Note**: deadline, end_time and begin_time of task should be in form <HHmm dd/mm/yyyy> 
(eg: 0630 25/2/2025 for 6:30AM, 25/2/2025 or 
1830 25/2/2025 for 6:30PM, 25/2/2025)

