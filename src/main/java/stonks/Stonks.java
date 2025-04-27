package stonks;

import stonks.storage.Storage;
import stonks.command.Command;
import stonks.task.TaskManager;
import stonks.parser.Parser;
import stonks.exceptions.StonksException;

/**
 * Chatbot named Stonks to store a todo-list
 */
public class Stonks {
    private Storage storage;
    private TaskManager tm;

    public Stonks(String filePath) {
        storage = new Storage(filePath);
        tm = new TaskManager(storage.load());
    }

    public Stonks() {
        storage = new Storage("./data/stonks.txt");
        tm = new TaskManager(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tm, storage);
        } catch (StonksException e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Stonks, your personal task manager. How can I help you today?";
    }
}