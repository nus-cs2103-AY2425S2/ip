package gigi.commands;

import java.io.IOException;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Task;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int taskIndex;

    public DeleteCommand(int i) {
        this.taskIndex = i - 1;
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, saving the updated task list to storage, and returning
     * a confirmation message.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The UI component to display messages.
     * @param storage The storage system to save tasks.
     * @return A confirmation message indicating the task has been deleted.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new GigiException("MEOW! You don't have that many tasks.");
        }

        Task removedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        tasks.saveTasks(storage);

        return ui.showDelMessage() + "\n"
                + ui.showMessage(removedTask.toString()) + "\n"
                + ui.showTaskNumber(tasks);
    }
}
