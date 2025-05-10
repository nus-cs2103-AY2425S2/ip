package scooby.ui;

/**
 * Provides a help message containing usage instructions for the chatbot.
 *
 * This class contains a static help message (`DEFAULTRESPONSE`) that outlines
 * the available commands and their expected formats. It helps users understand
 * how to interact with the chatbot.
 */
public class HelpPrompt {
    private static final String DEFAULTRESPONSE =
            "Hi, my name is Scooby.\nTo add tasks to be done, follow up with the following format:\n" +
                    "\"ToDo XXX\"\n" +
                    "To add tasks with deadlines, follow up with the following format:\n" +
                    "\"Deadline XXX /by yyyy-mm-dd hhmm\"\n" +
                    "To add event tasks, follow up with the following format:\n" +
                    "\"Event XXX /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\"\n\n" +
                    "To list tasks, send \"list\"\n" +
                    "To delete tasks, send \"delete <index>\"\n" +
                    "To update tasks, send \"update <index> <format>\"\n" +
                    "To end the chat, send \"bye\"";

    /**
     * Returns the chatbot's help message containing usage instructions.
     *
     * @return A string containing the list of supported commands and their formats.
     */
    public String getHelp() {
        return DEFAULTRESPONSE;
    }
}
