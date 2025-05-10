package parakeet.command;

import parakeet.DuplicateTaskError;
import parakeet.Storage;
import parakeet.TaskList;

/**
 * Represents a command that can be executed on a {@link TaskList}.
 * This is an abstract class that defines the {@link #execute(TaskList, Storage)} method,
 * which must be implemented by subclasses to define specific behavior for various commands.
 *
 * The command is executed by performing an action on the task list and updating the UI and storage accordingly.
 */
public abstract class Command {

    /**
     * Executes the specific command on the given task list, updating the UI and storage as necessary.
     *
     * @param taskList The list of tasks on which the command will be executed.
     * @param storage  The storage that may be used to persist task data (not used in all commands).
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DuplicateTaskError;
}
