package botato;

import java.util.Scanner;

/**
 * The Ui class represents the user interface of the bot application.
 * It is responsible for displaying messages to the user, reading user input,
 * and showing error messages when necessary.
 */
public class Ui {
    private Scanner reader;

    /**
     * Constructs a new Ui instance, initializing the Scanner to read user input.
     */
    public Ui() {
        reader = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     * The welcome message includes an ASCII art logo and a greeting.
     */
    public static String showWelcome() {
        String welcomeMessage = "Welcome to BOTato! How may I help you today?";
        showLine();

        System.out.println(welcomeMessage);
        showLine();
        return welcomeMessage;
    }

    /**
     * Reads a command entered by the user from the console.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Displays a separator line on the console for formatting.
     */
    public static void showLine() {
        String line = "------------------------------------------------------------------------------------------------"
                + "---------------";
        System.out.println(line);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
