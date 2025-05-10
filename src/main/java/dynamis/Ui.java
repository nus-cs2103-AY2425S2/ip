package dynamis;

import java.util.Scanner;

/**
 * Handles the common responses that the chatbot will provide to the user.
 */
public class Ui {
    private final Scanner scanner;
    private final static String HORIZONTAL_LINE = "----------------------------------------\n";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message response.
     */
    public String printWelcomeMessage() {
        return HORIZONTAL_LINE + "Hello! I'm Dynamis.\nWhat can I do for you?\n"
                + HORIZONTAL_LINE;
    }

    /**
     * Prints the list of tasks in a given TaskList object.
     * @param taskList The list of tasks to print.
     * @return The list of tasks as a String.
     */
    public String printTaskList(TaskList taskList) {
        return taskList.listItems();
    }

    /**
     * Returns the incorrect usage error message.
     */
    public String printIncorrectUsageError() {
        return "Incorrect usage! Please try again! Use the 'help' command to see the usage information!";
    }

    /**
     * Prints a help page for the available command inputs.
     */
    public String printHelpMessage() {
        return HORIZONTAL_LINE +
                "Dynamis - Here are the available commands:\n"
                + "  todo <task>               - Adds a to-do task\n"
                + "  deadline <task> /by <yyyy-MM-dd> - Adds a deadline\n"
                + "  event <task> /from <yyyy-MM-dd> /to <yyyy-MM-dd> - Adds an event\n"
                + "  list                      - Lists all tasks\n"
                + "  mark <task_number>        - Marks a task as done\n"
                + "  delete <task_number>      - deletes a task\n"
                + "  find <keyword>            - Searches for tasks containing the keyword\n"
                + "  help                      - Shows this help message\n"
                + "  bye                       - Exits the program\n"
                + HORIZONTAL_LINE;
    }
}
