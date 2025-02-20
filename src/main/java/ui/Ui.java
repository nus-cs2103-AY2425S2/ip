package ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Baimi, a task manager chatbot.");
        System.out.println("What can I do for you today?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting fresh.");
    }

    /**
     * Shows the error message.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Shows the message.
     *
     * @param message The message to be shown.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next command from the user.
     *
     * @return The next command from the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
