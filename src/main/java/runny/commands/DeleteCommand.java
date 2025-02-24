package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Determines the task index to be deleted.
 */
public class DeleteCommand implements Command {
    private String details;
    private Task deletedTask;

    /**
     * Creates a DeleteCommand with the specified details.
     *
     * @param details The details of the task index to be deleted.
     */
    public DeleteCommand(String details) {
        this.details = details;
        this.deletedTask = null;
    }

    /**
     * Executes command by removing a task from the task list.
     * Displays relevant messages to the user, saves the task to the local file.
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
        int deleteIndex = Integer.parseInt(details) - 1;
        if (details == "") {
            throw new RunnyException("OOPS!!! The description of a delete command cannot be empty.\n");
        }

        if (deleteIndex >= tasks.size() || deleteIndex < 0) {
            throw new RunnyException("OOPS!!! The specified task to delete is out of range.");
        }
        Task currentTask = tasks.get(deleteIndex);
        this.deletedTask = currentTask;
        tasks.remove(currentTask);
        storage.writeToFile(tasks);
        ui.printMessage("Noted. I've removed this task:\n" + currentTask.toString()
                + "\nNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
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
     * Undoes the DeleteCommand and adds the task back to the task list.
     *
     * @param tasks The list of tasks to which the task will be added.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new AddCommand(this.deletedTask);
    }


}
