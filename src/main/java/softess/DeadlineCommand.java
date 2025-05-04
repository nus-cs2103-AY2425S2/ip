package softess;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    /**
     * The task list to which the deadline task will be added.
     */
    public TaskList tasks;

    /**
     * The description of the deadline task.
     */
    public String description;

    /**
     * The due date/time of the deadline task.
     */
    public String by;

    /**
     * Constructs a DeadlineCommand with the specified user interface, task list, description, and due date.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list where the deadline task will be stored.
     * @param description The description of the deadline task.
     * @param by The due date or time of the deadline task.
     */
    public DeadlineCommand(UserInterface ui, TaskList tasks, String description, String by) {
        super(ui);
        this.tasks = tasks;
        this.description = description;
        this.by = by;
    }

    /**
     * Triggers the command to add a new deadline task to the task list.
     *
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String trigger() {
        return this.tasks.addTask(new Deadline(description, by, false));
    }
}
