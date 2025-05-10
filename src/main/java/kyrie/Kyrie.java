package kyrie;

import java.io.IOException;

/**
 * Represents the Kyrie program.
 */
public class Kyrie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Kyrie.
     * 
     * @param dirPath The directory path to save the task list to.
     */
    public Kyrie(String dirPath) {
        assert dirPath != null : "Directory path cannot be null";
        this.ui = new Ui("Kyrie");
        this.storage = new Storage(dirPath);
        try {
            this.storage.createDirAndFile();
            this.tasks = storage.loadData();
        } catch (IOException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets the response from Kyrie.
     * 
     * @param input The input from the user.
     * @return The response from Kyrie.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            command.execute(storage, ui, tasks);
            if (command.isExit()) {
                System.exit(0);
            }
            return ui.getResponse();
        } catch (KyrieException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    } 

    /**
     * Gets the welcome message from Kyrie.
     * 
     * @return The welcome message from Kyrie.
     */
    public String getWelcomeMessage() {
        ui.showWelcome();
        return ui.getResponse();
    }   
}
