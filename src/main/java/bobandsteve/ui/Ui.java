package bobandsteve.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the BobAndSteve application.
 * The Ui class is responsible for handling user input and displaying various messages to the user.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a new Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        String name = "Bob and Steve";
        String greeting = "Hello! I'm " + name + " \n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a loading error message when resources fail to load.
     */
    public void showLoadingError() {
        System.out.println("Error: Failed to load resources. Please try again.");
    }

    public String showText(String txt) {
        return txt;
    }

    public void printOutput(String txt) {
        System.out.println(txt);
    }
}
