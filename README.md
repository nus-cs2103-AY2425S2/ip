### Helix Chatbot: Your Personal Task Manager

Helix is a simple, intuitive, and interactive chatbot designed to help you manage your tasks efficiently. Whether you're adding to-dos, setting deadlines, or planning events, Helix is here to assist you every step of the way.

---

## **Features**

1. **Task Management**:
   - Add tasks to your list (To-Dos, Deadlines, Events).
   - Mark tasks as completed or unmark them.
   - Delete tasks when no longer needed.
   - Find tasks by description.
   - Update specific task details.
   - List all tasks.

2. **Interactive Chatbot**:
   - Manage your tasks through a **chat-style GUI**.
   - Simple command-based task management.

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)** version 17 .
- A terminal or an IDE like IntelliJ IDEA, Eclipse, or VS Code.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/twhjames/ip.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ip
   ```
3. Compile the project:
   ```bash
   javac -d bin $(find . -name "*.java")
   ```
4. Run the chatbot:
   ```bash
   java -cp bin helix.Helix
   ```

---

## **Usage**

Helix operates entirely through its **GUI interface**. Here are some actions you can perform:

| Command       | Description                                                                 |
|--------------|-----------------------------------------------------------------------------|
| `add todo <description>` | Adds a simple to-do task. Example: `add todo Read a book`                   |
| `add deadline <description> /by <due date>` | Adds a task with a deadline. Example: `add deadline Submit report /by 2025-01-30` |
| `add event <description> /from <start time> /to <end time>` | Adds an event. Example: `add event Team meeting /from 10am /to 12pm` |
| `list`        | Displays all the tasks in your list.                                        |
| `mark <task number>` | Marks the specified task as done. Example: `mark 1`                      |
| `unmark <task number>` | Marks the specified task as not done. Example: `unmark 1`              |
| `delete <task number>` | Deletes the specified task. Example: `delete 1`                       |
| `find <keyword>` | Finds all tasks that contain the keyword. Example: `find groceries`        |
| `update <task number> <task type> <new details>` | Updates an existing task. Example: `update 2 deadline Submit final report /by 2025-01-01 1200` |
| `bye`         | Exits the chatbot.                                                         |

---

## **Project Structure**

```
src/
├── helix.command/
│   ├── AddCommand.java
│   ├── Command.java
│   ├── CommandFactory.java
│   ├── DeleteCommand.java
│   ├── ExitCommand.java
│   ├── FindCommand.java
│   ├── ListCommand.java
│   ├── MarkCommand.java
│   ├── UnmarkCommand.java
│   ├── UpdateCommand.java
├── helix.enums/
│   ├── CommandType.java
│   ├── ExecutionStatus.java
│   ├── OutputSymbol.java
│   ├── TaskStatus.java
│   └── TaskType.java
├── helix.exception/
│   ├── HelixException.java
│   ├── InvalidCommandException.java
│   ├── InvalidNumberFormatException.java
│   ├── InvalidTaskTypeException.java
│   ├── MissingArgumentException.java
│   ├── TaskIndexOutOfBoundsException.java
│   └── TooManyArgumentsException.java
├── helix.task/
│   ├── Deadline.java
│   ├── Event.java
│   ├── Task.java
│   ├── Todo.java
├── helix.ui/
│   ├── ConsoleUi.java
│   ├── MainApp.java
│   ├── MainWindow.java
│   ├── DialogBox.java
├── util/
│   ├── Storage.java
│   └── TaskList.java
└── helix.Helix.java
```

---

## Example Interaction

```
╔════════════════════════════════════════════════════════╗
║                                                        ║
║             _   _  _____  _      ___ __  __            ║
║            | | | || ____|| |    |_ _|\ \/ /            ║
║            | |_| ||  _|  | |     | |  \  /             ║
║            |  _  || |___ | |___  | |  /  \             ║
║            |_| |_||_____||_____||___|/_/\_\            ║
║                                                        ║
╚════════════════════════════════════════════════════════╝

🤖 [helix.Helix] : Hello! I'm your personal assistant, helix.Helix.
🤖 [helix.Helix] : What can I do for you today?

👤 [You]   : todo Read a book
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: TODO
  📝 Description: Read a book

You now have 1 helix.task(s) in your list.
════════════════════════════════════

👤 [You]   : event Team meeting /from 2pm /to 3pm
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: EVENT
  📝 Description: Team meeting
  🕒 From: 2pm
  🕒 To: 3pm

You now have 2 helix.task(s) in your list.
════════════════════════════════════

👤 [You]   : deadline Submit assignment /by 2025-01-30
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: DEADLINE
  📝 Description: Submit assignment
  📅 Due: 2025-01-30

You now have 3 helix.task(s) in your list.
════════════════════════════════════

👤 [You]   : list
🤖 [helix.Helix] : Listing tasks...
════════════════════════════════════
📝 Task List:
    1. [T][ ] Read a book
    2. [E][ ] Team meeting (from: 2pm to: 3pm)
    3. [D][ ] Submit assignment (by: 2025-01-30)
════════════════════════════════════

👤 [You]   : mark 1
✅ [helix.Helix] : Task marked as complete!
    [T][✔] Read a book

👤 [You]   : unmark 1
❌ [helix.Helix] : Task marked as incomplete!
    [T][ ] Read a book

👤 [You]   : delete 1
════════════════════════════════════
🗑️  Task Removed!
════════════════════════════════════
  📋 Type: TODO
  📝 Description: Read a book
  🛠️ Task Status: PENDING

You now have 2 helix.task(s) in your list.
════════════════════════════════════

👤 [You]   : bye
🤖 [helix.Helix] : Bye! Hope to see you soon! 👋
=========================================================
✨ Thank you for using helix.Helix. Have a great day! ✨
=========================================================
```