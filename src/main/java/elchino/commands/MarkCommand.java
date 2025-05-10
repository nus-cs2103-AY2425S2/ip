package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;
    public static final String MESSAGE_MARK_TASK = "Ok, lo he marcado como hecho:\n%s";

    /**
     * Constructor for MarkCommand.
     * @param input The index of the task to mark as done.
     * @throws InvalidInputException if the input is invalid
     */
    public MarkCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_MARK_TASK, tasks.getTask(index));

    }
}