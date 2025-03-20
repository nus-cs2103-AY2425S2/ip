package ujin.command;

import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Represents a command to list all tasks from the task list.
 * This class inherits from the {@link Command} class and implements the execute behavior
 * of listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by listing all the tasks through the UI.
     *
     * @param taskList The task list containing all tasks.
     * @param ui       The user interface handler.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showTasks(taskList);
    }
}
