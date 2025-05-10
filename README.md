# L.A.F.F.Y
> "Hello, I am Laffy, Lucas' Automated Friendship For You." - Laffy 😊

Laffy is a task management application that helps you keep track of your tasks. It is a CLI app that allows you to add, mark, unmark, list, delete, and find tasks.

## Using Laffy
1. download the latest release from [here](https://github.com/aekyr/ip/releases)
2. Run the jar file using the command `java -jar laffy.jar`
3. Start chatting with Laffy!

## Features

- **Add tasks:** Add different types of tasks such as todos, deadlines, and events.
- **Mark tasks:** Mark tasks as done.
- **Unmark tasks:** Unmark tasks that are marked as done.
- **List tasks:** List all tasks.
- **Delete tasks:** Delete tasks from the list.
- **Find tasks:** Find tasks by searching for keywords.
- **Upcoming tasks:** View tasks that are due soon.

## Commands
1. `list`: Lists all tasks.
2. `todo <description>`: Adds a todo task to the list.
3. `deadline <description> /by <date>`: Adds a deadline task to the list.
4. `event <description> /from <date> /to <date>`: Adds an event task to the list.
5. `mark <index>`: Marks a task as done.
6. `unmark <index>`: Marks a task as undone.
7. `delete <index>`: Deletes a task from the list.
8. `find <keyword>`: Finds tasks that contain the keyword.
9. `upcoming`: Lists tasks that are due soon.
10. `bye`: Exits the application.
11. `help`: Displays the list of commands.

### To do list

- [x] ~~Create project structure~~
- [x] ~~Implement basic features~~
- [ ] Add **_friendship_**
- [ ] Write documentation

### Main Function of Laffy Application (For those who would like to make Laffy your own)

```java
public static void main(String[] args) throws IOException {
    new Laffy("data/laffy.txt").run();
}
```