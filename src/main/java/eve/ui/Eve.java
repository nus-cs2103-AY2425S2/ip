package eve.ui;

import eve.command.Command;
import eve.command.CommandParser;
import eve.exception.EveException;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Main class for Eve chatbot.
 */
public class Eve {
    private TaskList taskList;
    private boolean isExit = false;
    private boolean isCloseWindow = false;
    private final Storage storage;
    private String labelType = "normal";

    /**
     * Creates an instance of Eve with ui and storage.
     */
    public Eve() throws EveException {
        this.storage = new Storage("data/", "eve.txt");
        taskList = storage.loadTasks();
    }

    /**
     * Generates a response for the user's input message.
     *
     * @param input User's input message.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = CommandParser.parseString(input);
            output = command.execute(taskList, storage);
            isExit = command.isExit();
            isCloseWindow = command.isCloseWindow();
            labelType = "normal";
        } catch (EveException ex) {
            output = ex.getMessage();
            labelType = "error";
        }
        return output;
    }

    /**
     * Returns the welcome message to be displayed on the GUI at the start of the program.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Eve, your personal chat bot. What can I do for you today?\n"
                + "Use command \"help\" to view all available commands.";
    }

    /**
     * Returns whether program has been ended by user.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns whether program window has been closed by user.
     */
    public boolean isCloseWindow() {
        return isCloseWindow;
    }

    /**
     * Return the type of label to be displayed on the ui.
     */
    public String getLabelType() {
        return labelType;
    }
}
