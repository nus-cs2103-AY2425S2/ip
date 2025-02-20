package amara.command;

import java.util.ArrayList;

import amara.exceptions.AmaraException;
import amara.storage.Storage;
import amara.task.Task;
import amara.ui.Ui;

/**
 * A {@link Command} that deletes a {@link Task} at a specified index
 * in the given {@code ArrayList<Task>}.
 * <p>
 * This command removes the task from the list and updates the UI
 * with the corresponding changes.
 * </p>
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE = "Noted. I've removed this task:\n"
            + "  %s\nNow you have %d tasks in the list.";
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    /**
     * Removes a {@link Task} at a given {@code taskIndex} and
     * generates a {@code String} for the ui.
     *
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage To store the given List of tasks.
     * @return Messaage generated and passed to the UI handler.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws AmaraException {
        try {
            Task task = tasks.remove(this.taskIndex);
            String message = String.format(DeleteCommand.MESSAGE, task, tasks.size());
            ui.display(message);
            storage.saveList(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw AmaraException.indexOutOfBounds();
        }
    }
}
