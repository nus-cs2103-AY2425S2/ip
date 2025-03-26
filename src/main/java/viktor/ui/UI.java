package viktor.ui;

/**
 * This class represents the user interface for Viktor.
 */
public class UI {
    /**
     * Returns the welcome message.
     *
     * @return the welcome message string
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Viktor!\n" + "What can I do for you today?\n";
    }
    public String getStartMessage() {
        return "Here are some of my advanced capabilities:\n\n"
                + "Adding Tasks\n"
                + "Deleting a Task\n"
                + "Marking Tasks as Done\n"
                + "Viewing Tasks\n"
                + "Finding Tasks by Keyword\n"
                + "Viewing Tasks by Date\n"
                + "Exiting the Program\n"
                + "Resetting the Program\n"
                + "What can I do for you today?\n";
    }
}
