package partillay.command;

import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a show task list command that shows the current task list.
 */
public class ShowTaskListCommand extends Command {

    /**
     * Instructs the user interface to print the current task list.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.getTaskListTasks(tasks);
    }
}
