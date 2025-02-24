package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Determines the task index to be marked.
 */
public class MarkCommand implements Command {
    private String details;

    /**
     * Creates a MarkCommand with the specified details.
     *
     * @param details The details of the task index to be marked.
     */
    public MarkCommand(String details) {
        this.details = details;
    }

    /**
     * Executes command by marking a task as completed in the task list.
     * Displays relevant messages to the user, saves the marked task to the local file.
     *
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @param tasks   The list of tasks.
     * @throws RunnyException If the task index is out of bounds.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        assert ui != null && storage != null && tasks != null : "One of the three objects, "
                + "ui,storage or tasks is null";
        int markIndex = Integer.parseInt(details) - 1;
        if (markIndex >= tasks.size() || markIndex < 0) {
            throw new RunnyException("OOPS!!! The specified task to mark is out of range.");
        }
        Task currentTask = tasks.get(markIndex);
        currentTask.markTask();
        storage.writeToFile(tasks);
        ui.printMessage("Nice! I've marked this task as done:\n" + currentTask.toString());
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
     * Undoes the MarkCommand and unmarks the completion of the task in the task list.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new UnmarkCommand(this.details);
    }

}
