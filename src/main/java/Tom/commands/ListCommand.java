package Tom.commands;

import Tom.tasks.TaskList;

/**
 * The user command to print the task list
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList The TaskList instance.
     * @return The string representation of the command's response.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.printTaskList();
    }
}