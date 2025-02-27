package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param argument The task number (1-based index) as a string.
     * @throws NumberFormatException If the argument is not a valid integer.
     */
    public DeleteCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance for saving and updating tasks.
     * @throws IOException               If an error occurs while saving the task list.
     * @throws InvalidTaskNumberException If the provided task number is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Task number " + (index + 1) + " is invalid!!");
        }
        tasks.removeTask(index);
        ui.showMessage("You're goofy but I deleted your task");
        storage.save(tasks.getTasks());
    }
}
