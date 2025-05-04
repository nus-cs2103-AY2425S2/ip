package softess;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    /**
     * The task list to which the event task will be added.
     */
    public TaskList tasks;

    /**
     * The description of the event task.
     */
    public String description;

    /**
     * The start time or date of the event.
     */
    public String from;

    /**
     * The end time or date of the event.
     */
    public String to;

    /**
     * Constructs an EventCommand with the specified user interface, task list, description, start, and end time.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list where the event task will be stored.
     * @param description The description of the event task.
     * @param from The start time or date of the event.
     * @param to The end time or date of the event.
     */
    public EventCommand(UserInterface ui, TaskList tasks, String description, String from, String to) {
        super(ui);
        this.tasks = tasks;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Triggers the command to add a new event task to the task list.
     *
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String trigger() {
        return this.tasks.addTask(new Event(description, from, to, false));
    }
}
