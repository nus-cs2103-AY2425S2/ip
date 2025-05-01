package Lara.ui;

import java.util.Scanner;

public class Ui {

    public void welcomeMessage() {
        System.out.println("Hello! I'm Lara");
        System.out.println("What can I do for you?");
    }

    public void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String showHelp() {
        return "Here are the available commands:\n"
                + "1. `hello` - Greet the assistant.\n"
                + "2. `bye` - Exit the program.\n"
                + "3. `list` - Show all tasks.\n"
                + "4. `todo <description>` - Add a To-Do task.\n"
                + "5. `deadline <description> /by <date>` - Add a Deadline task.\n"
                + "6. `event <description> /from <start> /to <end>` - Add an Event task.\n"
                + "7. `delete <index>` - Remove a task.\n"
                + "8. `mark <index>` - Mark a task as done.\n"
                + "9. `unmark <index>` - Mark a task as not done.\n"
                + "10. `find <keyword>` - Find tasks containing a keyword.\n"
                + "11. `sort` - Sort tasks by start dates.\n"
                + "12. `help` - Show this help message.\n";
    }

    public void viewTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        tasks.printList();
    }

    public void errorMessage(String message) {
        System.out.println(message);
    }

    public void addedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void deletedTask(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void markedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void loadingError() {
        System.out.println("Sorry, there was an error loading the tasks.");
    }
}

