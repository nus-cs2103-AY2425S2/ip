package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command to exit the program.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws MikiException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        return ui.showExit();
    }
}
