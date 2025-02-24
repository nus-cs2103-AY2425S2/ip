package boink;

import java.io.FileNotFoundException;

import boink.exceptions.BoinkException;

/**
 * This class represents an instance of Boink bot
 */

public class Boink {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String filePath = "data";

    /**
     * Public constructor for Boink bot.
     */

    public Boink() {
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList();
        this.ui = new Ui(this.storage, this.tasks);
    }

    /**
     * Returns welcome message to display.
     *
     * @return String containing welcome message.
     */

    public String sayWelcome() {
        return this.ui.showWelcome() + " " + this.loadTasks();
    }

    /**
     * Returns output to print on success of loading tasks.
     * @return String message indicating success or failure of loading tasks.
     */

    public String loadTasks() {
        try {
            this.storage.loadTasksFromFile(this.tasks);
            return String.format("You currently have %d tasks in your list!", this.tasks.getSize());
        } catch (FileNotFoundException err) {
            return "Error: Task file not found! Please ensure the file exists and try again.";
        }
    }

    /**
     * Processes the user input, executes the corresponding command,
     * and returns Boink's response to print.
     *
     * @param userInput The command input from the user.
     * @return A string representing the system's response after executing the command.
     * @throws BoinkException If an error occurs during command execution.
     */

    public String getResponse(String userInput) throws BoinkException {
        assert !userInput.isEmpty() : "User input should not be empty";
        Command userCommand = Parser.parseUserInput(userInput);
        String response = "";

        switch (userCommand) {
        case BYE:
            return ui.showExit();
        case LIST:
            return ui.listTasks();
        case MARK:
            return ui.markTask(userInput);
        case UNMARK:
            return ui.unmarkTask(userInput);
        case DELETE:
            return ui.deleteTask(userInput);
        case FIND:
            return ui.findTasks(userInput);
        case TODO:
        case DEADLINE:
        case EVENT:
            return ui.addTask(userInput);
        case ARCHIVE:
            return ui.archiveTasks();
        default:
            return response;
        }
    }

    /**
     * Returns an error response message to be displayed to the user.
     *
     * @param err The error message to be shown.
     * @return A string containing the formatted error response.
     */

    public String getErrorResponse(String err) {
        return this.ui.showError(err);
    }
}

