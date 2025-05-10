package ui;

import java.util.Scanner;

/**
 * Handles user interactions with the chatbot.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a Ui object for handling user input and output.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the chatbot starts.
     */
    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Koji.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a separator line for better readability in output.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message when the chatbot exits.
     */
    public void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints a given message to the user.
     * @param message The message to display.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message to the user.
     * @param errorMessage The error message to display.
     */
    public void printError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    /**
     * Reads a command from the user input.
     * @return The user's input as a string.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }
}
