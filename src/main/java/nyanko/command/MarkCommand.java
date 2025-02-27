package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 * The specified task is identified using its index and updated accordingly.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a {@code MarkCommand} with the given task index.
     *
     * @param argument The index of the task to be marked as done (1-based index).
     * @throws NumberFormatException If the argument cannot be parsed as an integer.
     */
    public MarkCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    /**
     * Executes the command to mark a specified task as completed.
     * If the task index is invalid (out of bounds), an {@link InvalidTaskNumberException} is thrown.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save task data.
     * @throws IOException                 If an error occurs while saving the updated task list.
     * @throws InvalidTaskNumberException If the provided task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        String markString = "zzzz... oh WHAT you're done already?\n  " + task.toString();
        ui.showMessage(markString);
        storage.save(tasks.getTasks());
    }
}
