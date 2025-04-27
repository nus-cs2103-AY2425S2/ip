package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The ListCommand class represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by listing all tasks in the task list.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system (not used in this command).
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String responseToUser = tasks.listTasks();
        return responseToUser;
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as listing tasks does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
