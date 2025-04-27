package a;

import a.parser.Parser;
import a.storage.Storage;
import a.tasklist.TaskList;
import a.ui.Ui;

import java.util.function.Consumer;


/**
 * The main entry point for the A chatbot application.
 * It initializes the UI, storage, and task list, and then runs the command loop.
 */
public class A {
    private Ui ui;
    private Storage storage;
    private TaskList list;

    /**
     *
     */
    public A() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        list = new TaskList(storage.load());
    }

    /**
     *
     * @param outputConsumer
     */
    public void setOutputConsumer(Consumer<String> outputConsumer) {
        ui.setOutputConsumer(outputConsumer);
    }

    /**
     * 
     * @param input
     */
    public void handleInput(String input) {
        Parser.parse(input, ui, list);
        storage.save(list.getTasks());
    }
}
