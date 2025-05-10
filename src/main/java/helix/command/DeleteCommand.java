package helix.command;

import helix.enums.CommandType;
import helix.exception.HelixException;
import helix.exception.TaskIndexOutOfBoundsException;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand for the specified helix.task index.
     *
     * @param taskIndex the index of the helix.task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete helix.command by removing the helix.task from the helix.task list.
     *
     * @param taskList the helix.task list from which the helix.task will be removed
     * @param consoleUi the ConsoleUi component used to display messages to the user
     * @throws TaskIndexOutOfBoundsException if the helix.task index is invalid
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Remove the helix.task and notify the user
        Task task = taskList.removeTask(taskIndex, consoleUi);
        consoleUi.showTaskRemoved(task, taskList.getTaskCount());

    }
}
