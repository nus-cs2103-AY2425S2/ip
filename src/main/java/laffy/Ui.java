package laffy;

import java.util.Scanner;

/**
 * Represents the User Interface of the application.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets the user.
     */
    public void greet() {
        showLine();
        System.out.println("Hello! I'm L.A.F.F.Y");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Shows the error message.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Echoes the input.
     *
     * @param input The input to be echoed.
     */
    public void echo(String input) {
        System.out.println(input);
    }
}
