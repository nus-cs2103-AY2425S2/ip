package controller;

import java.util.Scanner;

/**
 * {@code Ui} class responsible to reading Sys in and writing to Sys out.
 * Handles printing messages as well as error messages
 */
public class Console {
    private static final String LINE_BREAK = "\n__________________________________________\n";
    private final Scanner scanner;

    /**
     * Construct an instance of {@code Ui} class with a scanner
     */
    public Console() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and trims it to a string
     *
     * @return Raw String user input trimmed
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints standard welcome message when starting the app
     */
    public String showWelcomeMessage() {
        String asciiArt = " ____   ____   _ __  __ ____   __  _ \n" +
                "| _) \\ / () \\ | |\\ \\/ // () \\ |  \\| |\n" +
                "|____//__/\\__\\|_| |__|/__\\/__\\|_|\\__|";
        String message = asciiArt + LINE_BREAK + "Hello I'm app.Daiyan What can I do for you?"
                + LINE_BREAK + "How may I assist you commander?" + LINE_BREAK;
        System.out.println(message);
        return message;
    }

    /**
     * prints standard goodbye message when exiting the app
     */
    public String showGoodbyeMessage() {
        String message = "Bye, hope to see you again Commander." + LINE_BREAK;
        System.out.println(message);
        return message;
    }

    /**
     * Prints message from task to System out
     *
     * @param message Message from task
     */
    public void showTaskMessage(String message) {
        System.out.println(message + LINE_BREAK);
    }

    /**
     * Prints the error message to System out
     *
     * @param message Error message to be printed
     */
    public void showErrorMessage(String message) {
        System.out.println(message + LINE_BREAK);
    }
}
