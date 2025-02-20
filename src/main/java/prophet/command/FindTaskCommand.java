package prophet.command;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The FindTaskCommand class represents a command to find tasks.
 */
public class FindTaskCommand extends Command {
    private final String keyword;

    /**
     * Initialises a newly created FindTaskCommand object with a keyword.
     * @param type the type of command
     * @param keyword the keyword to search for
     */
    public FindTaskCommand(CommandType type, String keyword) {
        super(type);
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks.
     * @param ui the user interface
     * @param storage the storage of tasks
    * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return storage.findTasks(this.keyword);
    }
}
