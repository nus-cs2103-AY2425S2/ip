package nyanko.ui;

import java.util.Scanner;

/**
 * Handles user interaction for the Nyanko application.
 * This class supports both CLI-based and GUI-based outputs.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private StringBuilder responseBuffer = new StringBuilder();

    /**
     * Reads the next command from the user (CLI mode).
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Stores a message and prints it to the CLI.
     *
     * @param message The message to store.
     */
    public void showMessage(String message) {
        responseBuffer.append(message).append("\n");
        System.out.println(message); // Keep CLI functionality
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        showMessage("HEEHAW I'M NYANKO \nToday's a good day to chill and slack!\nzzzzzz");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showMessage(message);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showMessage("boooo... error loading tasks!");
    }

    /**
     * Displays a goodbye message when the program exits.
     */
    public void showGoodbye() {
        showMessage("Good night... I'm going to nap zzzzz");
    }

    /**
     * Retrieves the accumulated response stored in the buffer for GUI display.
     *
     * @return The accumulated response as a string.
     */
    public String getLastResponse() {
        String result = responseBuffer.toString();
        responseBuffer.setLength(0); // Clear buffer after retrieving response
        return result;
    }
}
