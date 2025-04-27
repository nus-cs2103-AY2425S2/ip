package command;

import dew.DewException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The UnmarkCommand class represents a command to unmark a task as incomplete in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as incomplete and updating storage.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving updated tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DewException {
        String responseToUser = tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        return responseToUser;
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as unmarking a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
