package amara.command;

import java.util.ArrayList;

import amara.exceptions.AmaraException;
import amara.storage.Storage;
import amara.task.Task;
import amara.ui.Ui;

/**
 * A {@link Command} that unmarks a {@link Task} as incomplete
 * at a specific index in the given {@code ArrayList<Task>}.
 * <p>
 * This command retrieves the task, marks it as incomplete,
 * and updates the {@link Ui} accordingly.
 * </p>
 */
public class UnmarkCommand extends Command {
    private static final String MESSAGE = "OK, I've marked this task as not done yet:\n  %s";
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    /**
     * Unmarks the {@link Task} at the given {@code taskIndex}
     * and generates the {@code String} for the UI handler.
     *
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage To store the given List of tasks.
     * @return Messaage generated and passed to the UI handler.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws AmaraException {
        try {
            Task task = tasks.get(this.taskIndex);
            task.unmarkTask();
            String message = String.format(UnmarkCommand.MESSAGE, task);
            ui.display(message);
            storage.saveList(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw AmaraException.indexOutOfBounds();
        }
    }
}
