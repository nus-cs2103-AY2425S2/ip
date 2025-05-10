package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 * This command updates the task status in the task list and storage.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param argument The task number to be marked as not done (1-based index).
     */
    public UnmarkCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    /**
     * Executes the command to mark a task as not done.
     * The task is updated in memory and saved in persistent storage.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save task data.
     * @throws IOException                If an error occurs while saving the updated task list.
     * @throws InvalidTaskNumberException If the specified task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        String unmarkString = "I knew it! You're not done!\n  " + task.toString();
        ui.showMessage(unmarkString);
        storage.save(tasks.getTasks());
    }
}
