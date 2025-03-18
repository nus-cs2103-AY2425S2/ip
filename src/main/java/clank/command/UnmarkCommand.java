package clank.command;

import clank.exception.ClankException;
import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param input The raw input containing the task index to unmark.
     * @throws ClankException If the input does not contain a valid index.
     */
    public UnmarkCommand(String input) throws ClankException {
        try {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                        "unmark <index>");
            }
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Oh no! That's not a number!");
        }
    }

    /**
     * Executes the unmark command, setting the specified task as not done.
     *
     * @param taskList The task list containing the task to unmark.
     * @param ui The UI instance for user interaction.
     * @param storage The storage system (not modified in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "TaskList should not be null.";

        try {
            taskList.unmark(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify a valid index!");
        }
    }
}
