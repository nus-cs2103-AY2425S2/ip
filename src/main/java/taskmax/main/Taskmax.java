package taskmax.main;

import java.io.IOException;

import taskmax.command.Command;
import taskmax.exception.TaskmaxException;
import taskmax.parser.Parser;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

/**
 * The main class for Taskmax.
 * Handles user input, executes commands, and manages task storage.
 */
public class Taskmax {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Taskmax instance with the specified file path.
     *
     * @param filePath The path to the task storage file.
     */
    public Taskmax(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showMessage("Loaded previous tasks from file.");
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Taskmax application.
     * Continuously reads and executes user commands until exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                isExit = command.execute(tasks, ui, storage);
            } catch (TaskmaxException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main entry point for Taskmax.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Taskmax("data/tasks.txt").run();
    }
}
