package julie;

import java.util.ArrayList;
import java.util.List;

import julie.command.Command;
import julie.exception.WrongFormatException;
import julie.task.Task;

/**
 * Manages user tasks, handles input, executes commands, and manages task persistence.
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
     * Returns the chatbot's welcome message.
     * This is used to display the greeting when the GUI starts.
     *
     * @return The welcome message as a string.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Processes user input and returns a response (for GUI usage).
     *
     * @param input The user input string.
     * @return The chatbot’s response.
     */
    public String getResponse(String input) {
        assert input != null : "User input is null!";

        try {
            Command command = Parser.parse(input);
            String response = executeAndCapture(command);

            if (command.isExit()) {
                return ui.showGoodbye();
            }

            return response;
        } catch (WrongFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes a command and captures the UI output as a string.
     *
     * @param command The command to execute.
     * @return The formatted response string.
     */
    private String executeAndCapture(Command command) {
        assert command != null : "Command to execute is null.";

        ui.enableCaptureMode();
        try {
            command.execute(tasks, ui, storage);
        } catch (WrongFormatException e) {
            ui.showError(e.getMessage());
        }
        String capturedResponse = ui.getCapturedResponse();
        assert capturedResponse != null : "Captured response is null.";

        return ui.getCapturedResponse();
    }
}

