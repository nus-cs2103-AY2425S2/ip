# Jimmy Chatbot

Jimmy is a personal task manager chatbot built with Java and JavaFX. It helps users manage their tasks effectively through a graphical user interface (GUI).

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Commands](#commands)
- [Saving and Loading Data](#saving-and-loading-data)
- [Building the Application](#building-the-application)

---

## Features
- 📌 **Add tasks**: Supports different types of tasks such as Todos, Deadlines, and Events.
- ✅ **Mark/Unmark tasks**: Keep track of completed and pending tasks.
- ❌ **Delete tasks**: Remove tasks from the list.
- 🔍 **Find tasks**: Search tasks by keywords.
- 📄 **Save and load tasks**: Automatically saves tasks to a file and loads them when restarted.
- 🖥️ **Graphical User Interface (GUI)**: A simple, user-friendly JavaFX-based UI.

---

## Installation
### **Prerequisites**
Ensure you have the following installed on your system:
- Java (JDK 17 or later)
- JavaFX 17 (if not bundled with Java)
- Gradle (for building the application)

### **Steps**
1. **Clone the repository**
   ```sh
   git clone https://github.com/jiancheng37/ip
   cd ip
   ```
2. **Run the application**
   ```sh
   ./gradlew run
   ```

---

## Usage
### **Starting the Application**
- Run the application via `./gradlew run` or by executing the built JAR file.
- The chatbot will display a welcome message in the chat window.
- Enter commands into the text field and press `Enter` or click `Send`.

### **Commands**
#### 1️⃣ **List all tasks**
```sh
list
```
Displays all current tasks in the list.

#### 2️⃣ **Add a Todo task**
```sh
todo Buy groceries
```
Adds a Todo task named "Buy groceries".

#### 3️⃣ **Add a Deadline task**
```sh
deadline Submit assignment /by 2024-12-01 2359
```
Adds a Deadline task with a due date.

#### 4️⃣ **Add an Event task**
```sh
event Team meeting /from 2024-12-02 1400 /to 2024-12-02 1600
```
Adds an Event task with a specified time range.

#### 5️⃣ **Mark a task as done**
```sh
mark 2
```
Marks task #2 as completed.

#### 6️⃣ **Unmark a task as done**
```sh
unmark 2
```
Marks task #2 as not completed.

#### 7️⃣ **Delete a task**
```sh
delete 3
```
Removes task #3 from the list.

#### 8️⃣ **Find tasks**
```sh
find meeting
```
Searches for tasks containing the keyword "meeting".

#### 9️⃣ **Exit the application**
```sh
bye
```
Closes the chatbot.

---

## Saving and Loading Data
- Jimmy automatically saves your tasks to `data/jimmy.txt` upon exit.
- When restarted, Jimmy loads the saved tasks from this file.

---

## Building the Application
To create an executable JAR file with JavaFX included:
```sh
./gradlew shadowJar
```
The JAR file will be available at `build/libs/jimmy.jar`.
Run it using:
```sh
java -jar build/libs/jimmy.jar
```

---

Enjoy using Jimmy! 🚀