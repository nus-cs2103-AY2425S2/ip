package julie;

import java.util.ArrayList;
import java.util.List;

import julie.command.Command;
import julie.exception.WrongFormatException;
import julie.task.Task;


/**
 * Represents the Julie chatbot, which manages user tasks.
 * It handles user input, executes commands, and manages task persistence.
 */

public class Julie {
    private static final String FILE_PATH = "./data/julie.txt";
    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a {@code Julie} chatbot instance.
     * Initializes the user interface, storage, and loads existing tasks from the storage file.
     */
    public Julie() {
        this.ui = new UI();
        this.storage = new Storage(FILE_PATH);

        List<Task> loadedTasks = storage.loadTasks();
        this.tasks = new TaskList(new ArrayList<>(loadedTasks));
    }

    /**
     * Starts the chatbot application.
     * Displays a welcome message and continuously reads and processes user commands
     * until the exit command is received.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (WrongFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the application.
     * Creates and runs an instance of {@code Julie}.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Julie().run();
    }
}
