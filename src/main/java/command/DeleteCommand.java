package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task from the task list and updating storage.
     *
     * @param tasks   The task list from which the task is removed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving updated tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String responseToUser = tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        return responseToUser;
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as deleting a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
