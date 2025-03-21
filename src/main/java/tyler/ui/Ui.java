package tyler.ui;

import java.util.Scanner;

/**
 * Handles user interactions in the application.
 */
public class Ui {
    private static final String LOGO = "  _____      _           \n"
            + " |_   _|   _| | ___ _ __ \n"
            + "   | || | | | |/ _ \\ '__|\n"
            + "   | || |_| | |  __/ |   \n"
            + "   |_| \\__, |_|\\___|_|   \n"
            + "       |___/             \n";
    private static final String SEPARATOR = "\t"
            + "____________________________________________________________\n";
    private static final String GREETING = "\t" + " Hello! I'm Tyler!\n"
            + "\t" + " What can I do for you?\n";
    private static final String FAREWELL = "\t" + " Bye-bye now!\n";

    private String message;

    private Scanner scanner = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Displays the greeting when the application starts.
     */
    public void showGreeting() {
        this.message = ("Hello from\n" + LOGO + "\n"
                + SEPARATOR + GREETING + SEPARATOR);
    }

    /**
     * Displays the farewell message when the program ends and closes the scanner.
     */
    public void showFarewell() {
        this.message = FAREWELL;
        scanner.close();
    }

    /**
     * Displays an error message when the file containing the tasks cannot be loaded.
     */
    public void showLoadingError() {
        this.message = "\t " + "!!Old tasks could not be loaded!!\n" + SEPARATOR;
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be shown.
     */
    public void showMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the UI message.
     *
     * @return The current message of the UI.
     */
    public String getMessage() {
        return this.message;
    }

}
