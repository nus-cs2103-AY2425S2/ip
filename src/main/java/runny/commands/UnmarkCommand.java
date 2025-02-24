package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Determines the task to be unmarked.
 */
public class UnmarkCommand implements Command {
    private String details;

    /**
     * Creates an UnmarkCommand with the specified details.
     *
     * @param details The details of the task index to be marked.
     */
    public UnmarkCommand(String details) {
        this.details = details;
    }

    /**
     * Executes command by unmarking a task as not completed in the task list.
     * Displays relevant messages to the user, saves the unmarked task to the local file.
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
        int unmarkIndex = Integer.parseInt(details) - 1;
        if (unmarkIndex >= tasks.size() || unmarkIndex < 0) {
            throw new RunnyException("OOPS!!! The specified task to unmark is out or range.");
        }
        Task currentTask = tasks.get(unmarkIndex);
        currentTask.unmarkTask();
        storage.writeToFile(tasks);
        ui.printMessage("OK, I've marked this task as not done yet:\n" + currentTask.toString());
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
     * Undoes the UnmarkCommand and marks the completion of the task in the task list.
     *
     * @param tasks The list of tasks to which the task will be mark.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new MarkCommand(this.details);
    }


}
