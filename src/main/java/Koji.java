import java.io.IOException;

import commands.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The main entry point of the Koji chatbot application.
 * Handles initializing the chatbot and processing user commands.
 */
public class Koji {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes Koji with storage, task list, and UI components.
     * @param filePath Path to the storage file.
     */
    public Koji(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage);
        } catch (IOException e) {
            ui.printError("Error loading tasks.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs the chatbot, continuously processing user input until exit.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                Command c = Parser.parse(fullCommand);

                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Main method that starts Koji.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Koji("./data/koji.txt").run();
    }
}
