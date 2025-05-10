package ricky;

import ricky.command.Command;
import ricky.task.TaskList;
import java.nio.file.Path;

/**
 * Represents the Ricky chatbot.
 */
public class Ricky {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String commandType;
    private boolean isFirstTime = true;

    /**
     * Constructs a Ricky object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Ricky(Path filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();

        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (isFirstTime) {
            isFirstTime = false;
            return ui.getWelcomeMessage();
        }
        try {
            Command c = Parser.parse(input);
            commandType = c.getClass().getSimpleName();
            if (commandType.equals("ExitCommand")) {
                saveTasks();
                System.exit(0);
            }
            return c.execute(tasks, ui, storage);
        } catch (RickyException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the command type of the last command executed
     * @return The command type of the last command executed
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Saves the tasks to the file.
     */
    public void saveTasks() {
        try {
            storage.storeTasks(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
