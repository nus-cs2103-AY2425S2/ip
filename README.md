# Tasker: Your Ultimate Task Management Companion

![Screenshot](docs/Ui.png)

Tasker is an all-in-one task manager designed to simplify your life. Whether you're managing daily to-dos, tracking deadlines, scheduling events, or handling fixed-duration tasks, Tasker has you covered. With its intuitive interface and powerful features, you'll never miss a beat.

## Features

* To-dos: Add simple tasks with descriptions to be done.
* Deadlines: Set tasks with descriptions and deadlines to complete them by.
* Events: Schedule tasks with descriptions, start times, and end times.
* Fixed Duration Tasks: Specify tasks with descriptions and required durations to complete.
* Task Management: List, mark, unmark, find, and delete tasks efficiently.

## Installation

1. Clone the repository
   ```bash
   git clone https://github.com/BladerX11/ip.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ip
   ```
3. Compile the program:
   ```bash
   ./gradlew clean shadowJar
   ```
4. Run the program:
   ```bash
   java -jar build/libs/tasker.jar
   ```

## Usage

Tasker supports the following commands:

* Adding Tasks:
  * `todo {description}`: Adds a simple task.
  * `deadline {description} /by {datetime}`: Adds a task with a deadline.
  * `event {description} /from {datetime} /to {datetime}`: Adds a scheduled event.
  * `fixed {description} /hr {hours} /min {minutes}`: Adds a task with a fixed duration.
* Managing Tasks:
  * `list`: Lists all tasks.
  * `mark {index}`: Marks a task as complete.
  * `unmark {index}`: Unmarks a task.
  * `find {term}`: Finds tasks containing the search term.
  * `delete {index}`: Deletes a task.

For detailed usage and examples, refer to the [Tasker User Guide](https://bladerx11.github.io/ip/).

## Storage

Tasks are stored and loaded from the file at `./data/tasker.txt`.
