package dew;

import command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Dew class serves as the main entry point for the application.
 * It initializes the necessary components and starts the command loop.
 */
public class Dew {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Dew instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Dew(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DewException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String commandOutput = command.execute(tasks, ui, storage);
            return commandOutput;
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            storage.saveTasks(tasks.getTasks());
            return errorMessage;
        }
    }

    /**
     * Runs the main loop of the application, processing user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DewException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
