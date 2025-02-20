package nana.logic;

import java.io.IOException;

/**
 * The main class for the Nana application.
 * It initializes the UI, storage, and task list, and handles the main program loop.
 */
public class Nana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Nana instance with the specified file path for storage.
     *
     * @param filePath the file path for storage
     */
    public Nana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Nana application.
     * It prints the greeting message, reads user input, and processes commands until the user exits.
     */
    public void run() {
        ui.printGreeting();

        while (true) {
            ui.readInput();

            if (ui.getSignal().equals("bye")) {
                ui.printBye();
                break;
            } else {
                try {
                    tasks.process(ui.getInfo());
                } catch (NanaException e) {
                    ui.printNanaException(e);
                }
            }
        }
    }

    public String getResponse(String input) {
        ui.readInput(input);
        String s = "";
        try {
            s = tasks.process(ui.getInfo());
        } catch (NanaException e) {
            return e.getMessage();
        }
        return s;
    }

    /**
     * The main method to start the Nana application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Nana("./data/Nana.txt").run();

    }
}
