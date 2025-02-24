package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        String output = ui.showList(tasks);
        return output;
    }
}
