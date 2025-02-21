package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage of tasks.
     * @throws AvocadoException If an error occurs when executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        return ui.showError("Oops! I don't understand this command.");
    }
    
    /**
     * Returns false to indicate that this is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
