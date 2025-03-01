package bebop.ui;

import java.io.IOException;

import bebop.command.Parser;
import bebop.task.TaskList;

/**
 * A chatbot made to store tasks to be done. A <code>Bebop</code> object allows
 * you to insert, delete and mark formatted tasks.
 */

public class Bebop {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Bebop Constructor.
     * @param filePath filepath of the data in format data/Bebop.txt.
     * @throws IOException If the filepath given is invalid.
     */
    public Bebop(String filePath) throws IOException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList();
        tasks = storage.load(tasks);
    }

    public Parser getParser() {
        return parser;
    }
    public TaskList getTaskList() {
        return tasks;
    }
    public Ui getUi() {
        return ui;
    }
    public Storage getStorage() {
        return storage;
    }
}
