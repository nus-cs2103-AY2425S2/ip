package core;

import command.Command;
import exception.BaimiException;
import storage.Storage;
import ui.Ui;

/**
 * The main entry point of the Baimi application.
 * <p>
 * This class initializes the application, loads stored tasks, and manages user interactions.
 */
public class Baimi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Baimi instance with a specified storage file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Baimi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BaimiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response to a user input.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeAndGetResponse(tasks, ui, storage);
        } catch (BaimiException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }


    /**
     * Runs the main loop of the application, handling user input.
     * <p>
     * The method continuously reads and processes commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                String response = c.executeAndGetResponse(tasks, ui, storage);
                ui.showMessage(response);
                isExit = c.isExit();
            } catch (BaimiException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Baimi("./data/duke.txt").run();
    }
}