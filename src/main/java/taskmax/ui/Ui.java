package taskmax.ui;

import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner;
    public static final String LINE = "-".repeat(100);

    /**
     * Constructs a Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message along with the mascot.
     */
    public void showWelcome() {
        String intro = "Greetings! I'm Taskmax, your personal tasking companion.\n"
                + "What can I schedule for you today?\n";
        System.out.println(LINE + intro + LINE + new Mascot() + LINE
                + "\nEnter \"hello!\" to begin\n" + LINE);
    }

    /**
     * Reads and returns the next user command from input.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays one or multiple formatted messages.
     *
     * @param messages The messages to display.
     */
    public void showMessage(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showMessage("Error loading tasks. Starting with an empty list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner to release system resources.
     */
    public void close() {
        scanner.close();
    }
}