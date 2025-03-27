package qwerty.ui;

import java.util.Scanner;

/**
 * The Ui class handles user interactions, including displaying messages and reading input from the user.
 * It shows a welcome message, a separator line, and reads user commands from the console.
 */
public class Ui {

    private final String BREAKLINE = "------------------------------------------------------------"; // Separator line.
    private final String WELCOME_MESSAGE = "I'm Qwerty!\nReady to assist you with that ever-growing list of not-done tasks"; // Welcome message to display.
    private final String BYE_MESSAGE = "Bye! Maybe try pretending you'll be productive the next time we chat.";
    private final Scanner SCANNER = new Scanner(System.in); // Scanner to read user input.

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Displays a separator line to visually separate outputs.
     */
    public void showLine() {
        System.out.println(BREAKLINE);
    }

    public void showBye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        String command = SCANNER.nextLine();
        assert command.length() > 0 : "Command should not be an empty string!";
        return command;
    }

    /**
     * Closes the scanner to release the resources associated with it.
     */
    public void closeScanner() {
        this.SCANNER.close();
    }
}
