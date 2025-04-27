package ui;

import java.util.Scanner;

/**
 * The Ui class handles user interaction, including reading input
 * and displaying messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and application logo.
     */
    public void showWelcome() {
        String logo = " ____\n"
                + "|  _ \\  _____     __     __\n"
                + "| | | |/ _ \\  \\  /   \\  / /\n"
                + "| |_| |  __/ \\ v  / \\ \\/ /\n"
                + "|____/ \\___|  \\__/   \\__/\n";

        String startDialogue = "____________________________________________________________\n"
                + " Hello! I'm Dew\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println("Hello from\n" + logo + startDialogue);
    }

    /**
     * Reads and returns the user command input.
     * If the input is not a recognized command, an error message is shown,
     * and the user is prompted to enter another command.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            showError("Invalid command. Please enter a valid command.");
            return readCommand();
        }
        return input;
    }

    /**
     * Displays a line separator for better readability.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }
}
