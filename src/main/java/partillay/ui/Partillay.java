package partillay.ui;

import partillay.command.Command;
import partillay.exception.PartillayException;
import partillay.parser.Parser;
import partillay.storage.Storage;
import partillay.task.TaskList;

/**
 * Main class of the whole programme to run the chatbot.
 */
public class Partillay {
    public final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new Partillay object to run the programme,
     * possess less significance.
     */
    public Partillay() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.getTasks());
    }

    /**
     * Returns the string to be displayed in the GUI
     * @param input input from user
     * @return {@code String} to be displayed
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, ui);
            storage.writeTasksToFile(tasks);
            return result;
        } catch (PartillayException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Utilises run method to return a String responding to the input
     * @param input input from user
     * @return {@code String} to be displayed
     */
    public String getResponse(String input) {
        return run(input);
    }
}
