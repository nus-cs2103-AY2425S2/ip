package nguyen;

import java.util.Scanner;

/**
 * Handles user interface interactions, including displaying messages and reading user input.
 */
public class Ui {
    private Scanner scanner; // Scanner object to read user input from the console

    /**
     * Constructs a new Ui object and initializes the scanner to read from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nguyen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command from the user via the console.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a generic loading error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Displays a horizontal line to separate sections of the user interface.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
