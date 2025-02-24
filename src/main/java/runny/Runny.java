package runny;

import java.time.DateTimeException;

import runny.commands.Command;
import runny.commands.UndoCommand;
import runny.parser.Parser;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * The main class for the chatbot application.
 * Runny is a chatbot that helps to manage tasks;
 * Some things that it can do includes adding todo, events, deadlines.
 * It can also delete and find tasks as well as mark and unmark tasks.
 */
public class Runny {

    private Ui ui;
    private Storage storage;
    private TaskList tasks = new TaskList();

    /**
     * Creates a Runny instance with the provided file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Runny(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.load();
        } catch (RunnyException e) {
            ui.printMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the Ui instance that was created.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Runs the chatbot and processes user commands.
     * @param input The user input.
     */
    public void run(String input) {
        try {
            Command c = Parser.parse(input);
            c.doCommand(ui, storage, tasks);
            UndoCommand.saveCommand(c);
        } catch (RunnyException | DateTimeException e) {
            ui.printMessage(e.getMessage());
        }
    }
}
