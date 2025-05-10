# Steve User Guide
<img width="399" alt="Ui" src="https://github.com/user-attachments/assets/64e19cae-80e3-4b3b-89cd-1fba7b59eb2b" />


## 📌 Introduction
Steve is a task and client management chatbot that helps users keep track of their tasks and contacts efficiently through simple command-line interactions.

## 🚀 Features & Commands
###   📌 Adding a To-Do

Adds a task with no start or due date.

Command Format: `deadline <description> /by yyyy-mm-dd HHMM`
Example: `todo Buy groceries`

### ⏳ Adding a Deadline

Adds a deadline task to task list with information about its due date.

Command Format: `deadline <description> /by yyyy-mm-dd HHMM`
Example: `deadline Submit report /by 2025-02-20 2359`

### 📅 Adding an Event

Adds an event task to task list with information about its start & end date.

Command Format: `event <description> /from yyyy-mm-dd HHMM /by yyyy-mm-dd HHMM`
Example `event Team meeting /from 2025-02-22 1400 /by 2025-02-22 1600`

### 🔍 Finding a Task

Searches for tasks containing the specified keyword which will be displayed to user.

Command Format: `find <description>`
Example: `find report`

### ✅ Marking a Task as Done
Marks a task as completed which will be indicated on display.

Command Format: `mark <task index>`
Example: `mark 2`

### ❌ Unmarking a Task
Marks a task as incomplete which will be indicated on display.

Command Format: `unmark <task index>`
Example: `unmark 2`

### 📜 Viewing All Tasks

Displays the list of all tasks for users to reference.

Command Format: `list`

### 📇 Creating a Contact

Adds a contact with a name, phone number, and email.

Command Format: `contact <name> / <phone number> / <email>`
Example: `contact Alice Tan / 98765432 / alice@example.com`

### 👥 Viewing All Clients

Displays all saved client contacts.

Command Format: `clients`

### 🔚 Exiting the Program

Closes the application.

Command Format: `bye`

