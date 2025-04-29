package xuxin.main;

import xuxin.command.Command;
import xuxin.exception.DukeException;

/**
 * Xuxin is a chatbot that helps users manage their tasks.
 */
public class Xuxin {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Statistics stats;
    private static final String DEFAULT_FILE_PATH = "./data/duke.txt";
    private boolean isExit = false;

    public Xuxin() {
        storage = new Storage(DEFAULT_FILE_PATH);
        ui = new Ui();

        try {
            tasks = storage.loadTasks(ui);
        } catch (DukeException e) {
            ui.addMessage(e.getMessage());
            tasks = new TaskList();
        }

        stats = new Statistics(tasks);
    }

    /**
     * Execute the each command to capture the String output.
     * @param input the message to be passed to ui
     * @return ui output
     */
    public String getOutput(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.resetOutput();
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage, stats);
            isExit = command.isExit();
            return ui.showOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}