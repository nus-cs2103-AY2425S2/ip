package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;
    public static final String MESSAGE_UNMARK_TASK = "Ok, lo he marcado como no hecho:\n%s";

    /**
     * Constructor for UnmarkCommand.
     * @param input The index of the task to unmark as done.
     * @throws InvalidInputException if the input is invalid
     */
    public UnmarkCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_UNMARK_TASK, tasks.getTask(index));
    }
}