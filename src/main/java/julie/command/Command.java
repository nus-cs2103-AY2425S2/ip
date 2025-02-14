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
     * Executes the command, performing operations on the task list, UI, and storage.
     *
     * @param tasks The task list containing the user's tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving and loading tasks.
     * @throws WrongFormatException If the command is not properly formatted.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException;

    /**
     * Determines whether this command will terminate the application.
     * By default, commands do not exit the program.
     *
     * @return {@code true} if the command causes the program to exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
