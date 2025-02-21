package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents the list command to be executed by the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @throws AvocadoException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        return tasks.printTaskList();
    }
}
