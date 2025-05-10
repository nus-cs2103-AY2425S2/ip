package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.Deadline;
import elchino.tasks.Task;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;
    public static final String MESSAGE_DELETE = "Eliminado: %s";

    /**
     * Constructor for DeleteCommand with the task index to delete.
     * @param input The index of the task to delete.
     * @throws InvalidInputException If the input is not a valid integer.
     */
    public DeleteCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task removedTask = tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_DELETE, removedTask);
    }
}