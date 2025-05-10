package kaji;

import kaji.command.Command;

/**
 * Initializes chatbot
 */
public class Kaji {

    private static final String FILE_PATH = "./data/kaji.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Kaji() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        loadFile();
        ui.showWelcome();
    }

    /**
     * Returns a response for the given user input.
     *
     * @param input the user input
     * @return the response as a string
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                storage.save(tasks);
                return ui.showExit();
            }
            return c.execute(tasks);
        } catch (KajiException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads tasks from the storage file.
     */
    private void loadFile() {
        try {
            tasks = new TaskList(storage.load());
        } catch (KajiException e) {
            ui.showLoadingError(); // If file cannot be created or loaded fully
            tasks = new TaskList();
        }
    }

    // CLI interface (Not In Use)
    public static void main(String[] args) {
    }
}
