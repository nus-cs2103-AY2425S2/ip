package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyPhrase;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     *
     * @param keyPhrase The keyword to search for in task descriptions.
     */
    public FindCommand(String keyPhrase) {
        this.keyPhrase = keyPhrase;
    }

    /**
     * Executes the find command by searching for matching tasks and displaying them.
     *
     * @param tasks The task list to search in.
     * @param ui The user interface to display results.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showFoundTaskList(tasks, keyPhrase);
    }
}
