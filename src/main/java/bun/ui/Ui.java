package bun.ui;

import java.util.Scanner;

public class Ui {
    private static final String name = "Bun";
    private static final String logo = """
                    .-. .-')                    .-') _ \s
                    \\  ( OO )                  ( OO ) )\s
                     ;-----.\\  ,--. ,--.   ,--./ ,--,' \s
                     | .-.  |  |  | |  |   |   \\ |  |\\ \s
                     | '-' /_) |  | | .-') |    \\|  | )\s
                     | .-. `.  |  |_|( OO )|  .     |/ \s
                     | |  \\  | |  | | `-' /|  |\\    |  \s
                     | '--'  /('  '-'(_.-' |  | \\   |  \s
                     `------'   `-----'    `--'  `--'  \s
            """;

    /**
     * Returns the opening message.
     */
    public String showWelcome() {
        return "    Hello from\n" + logo
                + "    Hello! I'm " + name + ".\n"
                + "    What can I do for you?";
    }

    /**
     * Returns the farewell message when the conversation is ended by the user.
     */
    public String getEndOfConversation() {
        return "    Bye. Hope to see you again soon!";
    }

    /**
     * Returns the error message if the bot fails to load.
     */
    public String getLoadingError() {
        return "    Sorry, bun couldn't be loaded.";
    }

    public String[] readCommand(String line) {
        String[] parts = line.split("\\s+", 2);
        if (parts.length == 1) {
            return new String[]{parts[0]}; // No arguments
        } else {
            return new String[]{parts[0], parts[1]}; // Command + Arguments
        }
    }

    /**
     * Reads the commands from user input.
     *
     * @param scanner Scanner to read the user input.
     * @return Array of String which stores the command word and following details (if any) separately
     */
    public String[] readCommand(Scanner scanner) {
        String line = scanner.nextLine().trim(); // Read full line
        return readCommand(line);
    }

    /**
     * Returns the notification message after a task is successfully added.
     *
     * @param task Task added.
     */
    public String getAddTaskMessage(Task task, int taskCount) {
        return "    Got it. I've added this task:\n"
                + "      " + task + "\n"
                + "    Now you have " + taskCount + " task(s) in the list.";
    }

    /**
     * Returns the notification message after a task is successfully removed.
     *
     * @param task Task removed.
     */
    public String getDeleteTaskMessage(Task task, int taskCount) {
        return "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + taskCount + " task(s) in the list.";
    }

    /**
     * Returns the notification message after a task is successfully marked as done.
     *
     * @param task Task marked.
     */
    public String getMarkTaskMessage(Task task) {
        return "    OK :D I've marked this task as done:\n      " + task;
    }

    /**
     * Returns the notification message after a task is successfully unmarked as not done.
     *
     * @param task Task unmarked.
     */
    public String getUnmarkTaskMessage(Task task) {
        return "    OK D: I've marked this task as not done yet:\n      " + task;
    }

    /**
     * Returns the notification message after a task is successfully added.
     *
     * @param error Error message.
     */
    public String getError(String error) {
        return "    Bun is facing some error: \n" + error;
    }

    /**
     * Returns current list of tasks.
     *
     * @param taskList taskList List of active tasks.
     */
    public String getListForPrint(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "    No tasks yet.";
        } else {
            return "    Here are the tasks in your list:\n" + taskList;
        }
    }

    /**
     * Prints list of tasks in response to FIND command.
     *
     * @param taskList List of tasks found.
     */
    public String getFoundForPrint(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "    No matching task is found.";
        } else {
            return "    Here are the matching tasks in your list:\n" + taskList;
        }
    }
}
