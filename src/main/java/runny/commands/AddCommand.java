package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;
/**
 * This command is responsible for adding a specified task to the task list,
 * updating the user interface, and saving the task data.
 * This command is used when undoing the DeleteCommand.
 */
public class AddCommand implements Command {
    private Task restoreTask;
    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task  The task to be added.
     */
    public AddCommand(Task task) {
        this.restoreTask = task;
    }
    /**
     * Executes the add command, adding the specified task to the task list,
     * Saves the task to the local file and updating the user interface.
     *
     *
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @param tasks   The TaskList containing the tasks.
     * @throws RunnyException If an error occurs during execution.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        tasks.add(this.restoreTask);
        storage.writeToFile(tasks);
        ui.printMessage("Got it. I've added back this task:\n" + "\t" + this.restoreTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");

    }
    /**
     * Loads tasks from storage.
     *
     * @param tasks The TaskList to which tasks would be loaded.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }
    /**
     * Returns an BlankCommand as a result of an undo command.
     *
     * @param tasks The TaskList containing the tasks.
     * @return An BlankCommand instance.
     * @throws RunnyException If an error occurs during undo.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new BlankCommand();
    }
}
