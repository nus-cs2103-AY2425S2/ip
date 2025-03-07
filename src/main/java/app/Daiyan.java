package app;

import commands.AbstractCommand;
import controller.Console;
import controller.Parser;
import controller.Storage;
import datastructure.TaskList;
import exception.InvalidInputException;

/**
 * The main class for the Daiyan task management application.
 * It initializes and manages the core components such as storage, task list, and user interface.
 * The application runs in a loop, continuously processing user commands until an exit command is issued.
 */
public class Daiyan {

    private final Storage storage;
    private TaskList taskList;
    private final Console console;
    private String commandType;

    /**
     * Constructs a new instance of the Daiyan application.
     * It initializes the storage system, user interface, and loads tasks from the specified file.
     *
     * @param filePath The file path where task data is stored.
     */
    public Daiyan(String filePath) {
        this.console = new Console();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.load();
        } catch (InvalidInputException e) {
            console.showErrorMessage("Sorry Commander, I was unable to fetch our old logs");
            taskList = new TaskList();
        }
    }

    /**
     * Starts the application, continuously processing user commands until an exit command is received.
     */
    public void run() {
        Parser parser = new Parser(storage);

        this.console.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            String input = console.readCommand();
            try {
                AbstractCommand command = parser.parse(input);
                command.execute(this.taskList, this.console);
                if (command.isExit()) {
                    isRunning = false;
                }
            } catch (InvalidInputException e) {
                console.showErrorMessage(e.getMessage());
            }
        }
    }

    public String getStartUpResponse() {
        return this.console.showWelcomeMessage();
    }
    public String getResponse(String input) {
        Parser parser = new Parser(this.storage);
        try {
            AbstractCommand c = parser.parse(input);
            c.execute(taskList, console);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (InvalidInputException e) {
            commandType = "Error";
            return e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * The main entry point of the Daiyan application.
     * It initializes and runs the application with the specified storage file path.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Daiyan("./data/storage.txt").run();
    }
}
