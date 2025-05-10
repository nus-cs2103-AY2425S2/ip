package perrygpt;
import perrygpt.commands.Command;
import perrygpt.parser.Parser;
import perrygpt.tasks.*;
import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * The {@code PerryGPT} class represents the main application for managing tasks.
 * It handles user input, processes commands, and interacts with other classes like {@link TaskList},
 * {@link Storage}, {@link Ui}, and {@link Parser}.
 */
public class PerryGPT {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new {@code PerryGPT} application.
     * Initializes the storage, task list, and user interface.
     * Loads tasks from the specified file, or starts fresh if the file cannot be loaded.
     *
     * @param filePath The file path where tasks are stored.
     */
    public PerryGPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (PerryGPTException | IOException e) {
            ui.showError("Failed to load tasks." + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws PerryGPTException, IOException {
        Command command = Parser.parse(input);
        try {
            return command.execute(tasks, ui, storage);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * The main entry point for the {@code PerryGPT} application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // No Longer Used
    }
}
