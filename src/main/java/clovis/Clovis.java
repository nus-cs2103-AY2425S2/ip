package clovis;

import clovis.command.Command;
import clovis.task.TaskList;

/**
 * The {@code Clovis} class is responsible for initializing components and running the chatbot.
 */
public class Clovis {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructs a {@code Clovis} instance with the specified file path for storage.
     *
     * @param filePath the path to the file where the saved list of tasks are stored.
     */
    public Clovis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (ClovisException e) {
            ui.displayErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns Clovis's response.
     *
     * @param input the user's input command.
     * @return Clovis's response as a String.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null!";
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            assert response != null && !response.isEmpty() : "Duke response should not be null or empty!";
            return response;
        } catch (ClovisException e) {
            return e.getMessage();
        }
    }
}
