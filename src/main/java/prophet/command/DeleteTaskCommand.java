package prophet.command;

import prophet.exception.InvalidTaskNumberException;
import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The DeleteTaskCommand class represents the command to delete a task from the chatbot.
 */
public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * Initialises a newly created DeleteTaskCommand object.
     * @param type the type of command
     * @param index the description of the task.
     */
    public DeleteTaskCommand(CommandType type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Deletes a specific task.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
    * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) throws InvalidTaskNumberException {
        String deleted = storage.deleteTask(this.index);
        return ui.print(deleted + "\n");
    }
}
