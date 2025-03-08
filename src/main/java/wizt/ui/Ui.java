package wizt.ui;

import java.util.Scanner;
/**
 *  Represents the UI shown to user in the program
 */
public class Ui {

    /**
     * Scan the input by user
     * @return command
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    /**
     * Print the welcome message
     */
    public void showWelcome() {

        System.out.println("Hello! I'm wizt.ui.WizT");
        System.out.println("What can I do for you?");

    }

    /**
     * Print error message
     * @param error
     */
    public void showLoadingError(String error) {
        System.out.println("Error: " + error);
    }

    /**
     * print lines to separate each command
     */
    public void showLine() {
        System.out.println("-------------------------------------");
    }

    /**
     * Print error
     * @param error
     */
    public void showError(String error) {
        System.out.println(error);
    }

}
