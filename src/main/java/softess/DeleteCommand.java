package softess;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The task list from which the task will be removed.
     */
    public TaskList tasks;

    /**
     * The index of the task to be removed.
     */
    public int index;

    /**
     * Constructs a DeleteCommand with the specified user interface, task list, and task index.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list from which the task will be removed.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(UserInterface ui, TaskList tasks, int index) {
        super(ui);
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Triggers the command to remove a task from the task list.
     *
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String trigger() {
        return this.tasks.removeTask(this.index);
    }
}