package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new DeleteCommand with the given task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command, removing a task from the task list.
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

        Task removedTask = tasks.getTasks().remove(taskIndex);
        storage.save(tasks.getTasks());

        return "Noted baby. I've removed this task:\n  " + removedTask +
                "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}


