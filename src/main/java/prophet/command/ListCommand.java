package prophet.command;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The ListCommand class represents a command to list all tasks in the chatbot.
 */
public class ListCommand extends Command {
    /**
     * Initialises a newly created ListCommand object.
     * @param type the type of command
     */
    public ListCommand(CommandType type) {
        super(type);
    }

    /**
     * Prints out all current tasks.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return storage.enumerateList();
    }
}
