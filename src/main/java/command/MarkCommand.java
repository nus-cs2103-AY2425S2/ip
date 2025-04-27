package command;

import dew.DewException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The MarkCommand class represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as completed and updating storage.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving updated tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DewException {
        String responseToUser = tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        return responseToUser;
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as marking a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
