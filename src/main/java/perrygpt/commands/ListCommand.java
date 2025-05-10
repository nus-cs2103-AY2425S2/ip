package perrygpt.commands;

import perrygpt.storage.Storage;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by retrieving all tasks and displaying them to the user.
     *
     * @param tasks   The task list containing all tasks.
     * @param ui      The user interface for displaying the task list.
     * @param storage The storage system (not used in this command).
     * @return A message displaying the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getAllTasks());
    }
}