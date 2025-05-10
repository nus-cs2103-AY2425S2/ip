package command;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

/**
 * Represents a command to add a task to the tasklist and print the confirmation message to the UI.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs a AddCommand with the task object to be added.
     * @param task The task to be added to the tasklist.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command: adds the task to the tasklist and displays
     *               a message on the UI indicating the task has been added.
     * @param tasklist The tasklist in which the task is being added to.
     * @param ui The UI used to print the add message.
     */
    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) throws ElmachoException {
        assert task != null : "Task should not be null.";
        tasklist.add(this.task);
        ui.printAddMessage(tasklist, this.task);
    }
}
