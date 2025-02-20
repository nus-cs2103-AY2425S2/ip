package prophet.command;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The MarkNotDoneCommand class represents the command to mark a task as not done.
 */
public class MarkNotDoneCommand extends Command {
    private final int index;

    /**
     * Initialises a newly created MarkNotDoneCommand object.
     * @param type the type of command
     * @param index the description of the task
     */
    public MarkNotDoneCommand(CommandType type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Marks a specific task as not done.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
    * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return storage.setNotDone(index);
    }
}
