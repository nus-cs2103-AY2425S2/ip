package helix;

import java.io.IOException;
import java.nio.file.Paths;

import helix.command.Command;
import helix.command.CommandFactory;
import helix.enums.FilePath;
import helix.exception.HelixException;
import helix.storage.Storage;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Initializes and runs the Helix application.
 *
 * <p>
 * This class serves as the entry point for the Helix application, managing the initialization
 * of components such as storage, the task list, and the user interface. It also handles the
 * main program loop, which processes user commands and executes them using polymorphism.
 * </p>
 */
public class Helix {
    private final Storage storage;
    private TaskList taskList;
    private final ConsoleUi consoleUi;

    /**
     * Constructs a new instance of the Helix application.
     *
     * @param filePath The file path to the storage file where tasks are saved and loaded.
     */
    public Helix(String filePath) {
        this.consoleUi = new ConsoleUi();
        String resolvedFilePath = resolveFilePath();
        this.storage = new Storage(resolvedFilePath);
        initializeTaskList();
    }

    /**
     * Resolves the correct file path based on the execution environment.
     *
     * @return The absolute file path for storage.
     */
    private String resolveFilePath() {
        String storageFile = FilePath.STORAGE_FILE.getPath();
        return System.getProperty("user.dir").contains("text-consoleUi-test")
                ? Paths.get("..", storageFile).toAbsolutePath().normalize().toString()
                : Paths.get(storageFile).toAbsolutePath().normalize().toString();
    }

    /**
     * Initializes the task list from storage.
     * Displays an error message if loading fails.
     */
    private void initializeTaskList() {
        try {
            this.taskList = new TaskList(storage);
            assert taskList != null : "TaskList initialization failed!";
        } catch (IOException e) {
            consoleUi.showLoadingStorageError();
        }
    }

    /**
     * Executes a user command and returns the response message.
     *
     * @param userInput The user input string representing the command.
     * @return A string containing the result of execution or an error message.
     */
    public String executeCommand(String userInput) {
        try {
            Command command = CommandFactory.parseCommand(userInput);
            command.execute(taskList, consoleUi);
            return consoleUi.getLastMessage();
        } catch (HelixException e) {
            return e.getFormattedMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    /**
     * Starts the Helix application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Helix Running.");
    }
}
