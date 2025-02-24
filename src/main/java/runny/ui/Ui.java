package runny.ui;

/**
 * User interface class responsible for interacting with the user.
 * Provides methods to display messages and read user commands.
 */
public class Ui {
    private String output;

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void printMessage(String message) {
        this.output = message;
    }

    /**
     * Returns the message stored.
     */
    public String getOutput() {
        return this.output;
    }
}
