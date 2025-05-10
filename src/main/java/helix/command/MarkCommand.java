package helix.command;

import helix.enums.CommandType;
import helix.enums.TaskStatus;
import helix.exception.HelixException;
import helix.exception.TaskIndexOutOfBoundsException;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand for the specified helix.task index.
     *
     * @param taskIndex the index of the helix.task to mark as done
     */
    public MarkCommand(int taskIndex) {
        super(CommandType.MARK);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark helix.command, marking the specified helix.task as done.
     *
     * @param taskList the TaskList containing the helix.task to be updated
     * @param consoleUi the ConsoleUi component used to display messages to the user
     * @throws HelixException if the helix.task index is invalid
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // get the helix.task
        Task task = taskList.getTask(taskIndex);

        // handle helix.task status and notify via ConsoleUi
        if (task.getTaskStatus() == TaskStatus.COMPLETED) {
            consoleUi.showTaskAlreadyCompleted();
        } else {
            taskList.markTaskAsDone(taskIndex, consoleUi);
            consoleUi.showTaskMarkedComplete(task);
        }
    }
}
