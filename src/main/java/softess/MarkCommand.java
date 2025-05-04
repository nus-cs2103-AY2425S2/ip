package softess;

/**
 * Represents a command to mark or unmark a task as done in the task list.
 */
public class MarkCommand extends Command {

    /**
     * The task list containing the task to be marked or unmarked.
     */
    public TaskList tasks;

    /**
     * The index of the task to be marked or unmarked.
     */
    public int index;

    /**
     * A flag indicating whether the task should be marked as done or undone.
     */
    public boolean shouldMark;

    /**
     * Constructs a MarkCommand with the specified user interface, task list, task index, and mark status.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list containing the task.
     * @param index The index of the task to be marked or unmarked.
     * @param shouldMark True if the task should be marked as done, false if it should be marked as undone.
     */
    public MarkCommand(UserInterface ui, TaskList tasks, int index, boolean shouldMark) {
        super(ui);
        this.tasks = tasks;
        this.index = index;
        this.shouldMark = shouldMark;
    }

    /**
     * Triggers the command to mark or unmark a task as done.
     *
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String trigger() {
        if (shouldMark) {
            return this.tasks.markTaskAsDone(this.index);
        } else {
            return this.tasks.markTaskAsUndone(this.index);
        }
    }
}
