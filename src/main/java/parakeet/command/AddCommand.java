package parakeet.command;


import parakeet.DuplicateTaskError;
import parakeet.TaskList;
import parakeet.Storage;

/**
 * Represents an abstract command that adds a task to a {@link TaskList}.
 * This class extends {@link Command} and defines the structure for commands that add tasks to the task list.
 *
 * Subclasses must implement the {@link Command#execute(TaskList, Storage)} method to define the specific behavior
 * of adding a task (e.g., adding a {@link parakeet.task.Todo} or {@link parakeet.task.Event}).
 */
public abstract class AddCommand extends Command {

    /**
     * Executes the command to add a task to the task list.
     * This method must be implemented by subclasses to define the specific behavior for adding a task.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param storage  The storage used to save the tasks (not used in all commands).
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DuplicateTaskError;
}
