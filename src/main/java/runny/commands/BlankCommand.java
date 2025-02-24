package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;
/**
 * Represents a placeholder command that does nothing.
 * This command is used when an unrecognized or empty user input is provided.
 */
public class BlankCommand implements Command {
    /**
     * Executes a BlankCommand, displaying a message that there is nothing to be undone.
     *
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @param tasks   The TaskList containing the tasks.
     * @throws RunnyException If an error occurs during execution.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        ui.printMessage("OOPS! There is nothing to be undone.");
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Does nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) {
        return new BlankCommand();
    }
}
