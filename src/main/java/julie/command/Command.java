package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;

/**
 * Represents an abstract command that can be executed in the task management application.
 * Subclasses must implement the {@code execute} method to define specific command behaviors.
 */
public abstract class Command {
    /**
     * Constructs a {@code Command} instance.
     * This constructor is protected to ensure only subclasses can instantiate commands.
     */
    protected Command() {
    }

    /**
     * Validates that the given task index is within the valid range.
     *
     * @param index The 1-based index of the task.
     * @param tasks The task list containing all stored tasks.
     * @throws WrongFormatException If the index is out of bounds.
     */
    protected void validateIndex(int index, TaskList tasks) throws WrongFormatException {
        if (index < 1 || index > tasks.size()) {
            throw new WrongFormatException("Oops! That task number doesn't exist!\n"
                    + "Please enter a valid task number.");
        }
    }

    /**
     * Executes the command, performing operations on the task list, UI, and storage.
     *
     * @param tasks The task list containing the user's tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving and loading tasks.
     * @throws WrongFormatException If the command is not properly formatted.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException;

    /**
     * Determines whether executing this command will terminate the application.
     * Most commands do not exit the program, but certain commands (e.g., "bye") override this to return {@code true}.
     *
     * @return {@code true} if the command causes the program to exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
