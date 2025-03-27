package yapper.ui;

/**
 * Handles the user interface for the Yapper chatbot,
 * including displaying messages and formatting output.
 */
public class UI {
    private static final int HORIZONTAL_LINE_LENGTH = 80;
    private static final String NAME = "Yapper";

    /**
     * Constructs a {@code UI} instance with the chatbot's name.
     */
    public UI() {}

    /**
     * Prints the chatbot's introduction message.
     */
    public String getIntroduction() {
        return "Hello! I'm " + NAME + ". What can I do for you?";
    }

    /**
     * Prints the chatbot's exit message.
     */
    public String getExit() {
        return "Bye. Hope to see you again soon!";
    }
}
