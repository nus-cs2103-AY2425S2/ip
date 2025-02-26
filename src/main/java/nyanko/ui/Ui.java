package nyanko.ui;

import java.util.Scanner;

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

    public void showWelcome() {
        showMessage("HEEHAW I'M NYANKO 🐱\nToday's a good day to chill and slack!\nzzzzzz");
    }

    public void showError(String message) {
        showMessage(message);
    }

    public void showLoadingError() {
        showMessage("boooo... error loading tasks!");
    }

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
