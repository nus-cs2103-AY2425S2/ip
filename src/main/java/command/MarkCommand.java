package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;
import task.Todo;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new MarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking a task as done in the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return The response to the user command.
     * @throws BaimiException If an error occurs during the execution of the command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new TaskIndexOutOfBoundsException(tasks.getTasks().size());
        }

        tasks.markTask(taskIndex);
        Task task = tasks.getTasks().get(taskIndex);
        storage.save(tasks.getTasks());

        return "Nice baby! I've marked this task as done:\n  " + task;
    }
}

