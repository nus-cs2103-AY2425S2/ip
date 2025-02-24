package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Iterates through the task list and displays the tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes command by displaying all the tasks in the task list.
     * Displays the tasks in the list to the user.
     *
     * @param ui      The user interface for displaying messages.
     * @param storage The storage.
     * @param tasks   The list of tasks.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) {
        assert ui != null && storage != null && tasks != null : "One of the three objects, "
                + "ui,storage or tasks is null";
        String output = "";
        if (tasks.isEmpty()) {
            output = "There are no tasks in your list.";
            ui.printMessage(output);
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.isEmpty()) {
                output += (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        ui.printMessage(output);
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {

    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new BlankCommand();
    }

}
