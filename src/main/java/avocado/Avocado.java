package avocado;

import avocado.command.Command;
import avocado.parser.Parser;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;
import java.util.ArrayList;

/**
 * The main class for the Avocado chatbot.
 * Handles initialization and execution of user commands.
 */

public class Avocado {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an Avocado instance with a specified storage file.
     *
     * @param filePath The path to the file used for saving/loading tasks.
     */

    public Avocado(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        ArrayList<String> tags = storage.loadTags();
        // Initialize tags in TaskList if needed

        assert storage != null : "Storage should not be null";
        assert tasks != null : "TaskList should not be null";
    }

    /**
     * Runs the Avocado chatbot.
     * Displays welcome message and reads user commands until the user exits the chatbot.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                assert command != null : "Command should not be null";
                executeCommand(command);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private void executeCommand(Command command) throws Exception {
        String result = command.execute(tasks, ui, storage);
        // Save tags after executing a command that might modify them
        storage.saveTags(new ArrayList<>(tasks.getTags()));
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showWelcome() {
        return ui.showWelcome();
    }
    

    /**
     * The main method for the Avocado chatbot.
     * Creates an Avocado instance and runs the chatbot.
     *
     * @param args The command line arguments.
     */

    public static void main(String[] args) {
        new Avocado("data/tasks.txt").run();
    }
}



