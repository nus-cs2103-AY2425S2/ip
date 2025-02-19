# Mab - Task Management Application
        ██╗  ██╗███████╗██╗     ██╗      ██████╗  ██╗        ██╗███╗   ███╗    ███╗   ███╗ █████╗ ██████╗ 
        ██║  ██║██╔════╝██║     ██║     ██╔═══██╗ ██║        ██║████╗ ████║    ████╗ ████║██╔══██╗██╔══██╗
        ███████║█████╗  ██║     ██║     ██║   ██║ ██║        ██║██╔████╔██║    ██╔████╔██║███████║██████╔╝
        ██╔══██║██╔══╝  ██║     ██║     ██║   ██║ ╚═╝        ██║██║╚██╔╝██║    ██║╚██╔╝██║██╔══██║██╔══██╗
        ██║  ██║███████╗███████╗███████╗╚██████╔╝ ██╗        ██║██║ ╚═╝ ██║    ██║ ╚═╝ ██║██║  ██║██████╔╝
        ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝  ╚═╝        ╚═╝╚═╝     ╚═╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ 
A simple yet powerful command-line task management application.

## Features

- **Task Management**
  - Create and manage todos
  - Track deadlines
  - Schedule events
  - Mark tasks as complete/incomplete
  - List all tasks
  - Find specific tasks

## How to Use

The application supports the following commands:

- `todo <description>` - Create a new todo
- `deadline <description> /by <date>` - Create a task with deadline
- `event <description> /from <date> /to <date>` - Schedule an event
- `list` - Display all tasks
- `mark <task-number>` - Mark a task as complete
- `unmark <task-number>` - Remove a task
- `find <keyword>` - Search for tasks

## Getting Started

### Prerequisites
- Java 17 or higher

### Installation
1. Download the latest release from the Releases page.
2. Run the JAR file using the following command:
   ```
   java -jar mab.jar
   ```
