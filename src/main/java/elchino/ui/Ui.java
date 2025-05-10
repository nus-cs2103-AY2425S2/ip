package elchino.ui;
import java.util.Scanner;

/* heavily inspired by addressbook-level2 TextUi.java */
/**
 * Represents the Text UI of the application.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for the UI.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hola! Yo me llamo El Chino. Encantado.");
        System.out.println("¿Le puedo ayudar en algo?");
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Gracias por usar El Chino. ¡Adiós!");
        showLine();
    }

    /**
     * Shows a line.
     */
    public void showLine() {
        System.out.println("--------------------");
    }

    /**
     * Reads the command from the user.
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a message.
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}