package charlie;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the task manager.
 * This class handles displaying messages to the user and reading input from the user.
 */
class Ui {
    /**
     * Displays a welcome message when the application starts.
     *
     * @return The welcome message as a String.
     */
    public static String showWelcome() {
        return "Hello! I'm charlie.Charlie\nWhat can I do for you?";
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input by the user as a String.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a help message that shows the available commands the user can use.
     *
     * @return The help message as a String.
     */
    public static String printHelp() {
        return "Here are the commands you can use:\n"
                + "1. todo <task description>\n"
                + "2. deadline <task description> /by <time>\n"
                + "3. event <task description> /from <start time> /to <end time>\n"
                + "4. delete <task number>\n"
                + "5. list\n"
                + "6. bye";
    }

    /**
     * Displays a goodbye message when the user exits the application.
     *
     * @return The goodbye message as a String.
     */
    public static String showGoodbye() {
        return "Goodbye! Hope to see you soon!";
    }

    /**
     * Displays an error message when there is an error.
     *
     * @param message The error message.
     * @return The formatted error message as a String.
     */
    public static String showError(String message) {
        return "Error: " + message;
    }
}
