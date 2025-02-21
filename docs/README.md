# Avocado Project

Avocado is a task management application that helps you keep track of your tasks, deadlines, and events. It also allows you to tag tasks with custom tags to categorize them.

Why the name Avocado? I don't know, how about you tell me then.

## Features

- Add, delete, and list tasks
- Mark tasks as done or not done
- Add deadlines and events
- Tag tasks with custom tags
- Save and load tasks from a file

## Getting Started

### Prerequisites

- Java 17

### How to run

#### Through cloning repo
To run the application, follow these simple steps:

- Clone the Repository: First, clone this repository to your local machine:

```
git clone https://github.com/lilduckling/ip
```

- Run the application: ./gradlew run

#### Through release
- Download release v2.0: https://github.com/lilduckling/ip/releases
- Use your terminal, run:
```
java -jar avocado-2.0.0.jar 
```

## Usage

### Adding a Todo

To add a todo task, use the `todo` command followed by the task description.

Example:
```
todo read book
```

### Adding a Deadline

To add a deadline, use the `deadline` command followed by the task description and the due date.
The date formating is yyyy-mm-dd

Example:
```
deadline return book /by 2023-10-10
```

### Adding an Event

To add an event, use the `event` command followed by the task description, start time, and end time.
The date time formating is yyyy-mm-dd HHmm

Example:
```
event project meeting /from 2023-10-10 1400 /to 2023-10-10 1600
```

### Listing Tasks

To list all tasks, use the `list` command.

Example:
```
list
```

### Marking a Task as Done

To mark a task as done, use the `mark` command followed by the task number.

Example:
```
mark 1
```

### Marking a Task as Not Done

To mark a task as not done, use the `unmark` command followed by the task number.

Example:
```
unmark 1
```

### Deleting a Task

To delete a task, use the `delete` command followed by the task number.

Example:
```
delete 1
```

### Tagging a Task

To add a tag to a task, use the `tag` command followed by the task number and the tag.

Example:
```
tag 1 #fun
```

### Removing a Tag

To remove a tag from a task, use the `untag` command followed by the task number and the tag.

Example:
```
untag 1 #fun
```

### Finding Tasks

To find tasks that contain a specific keyword, use the `find` command followed by the keyword.

Example:
```
find book
```

### Exiting the Application

To exit the application, use the `bye` command.

Example:
```
bye
```

## Saving and Loading Tasks

Tasks are automatically saved to a file (`./data/tasks.txt`) when the application exits and loaded from the file when the application starts.

## Sample UI
![alt text](https://lilduckling.github.io/ip/Ui.png)

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request.



