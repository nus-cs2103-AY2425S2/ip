package softess;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class ToDoCommand extends Command {

    /**
     * The task list to which the to-do task will be added.
     */
    public TaskList tasks;

    /**
     * The description of the to-do task.
     */
    public String description;

    /**
     * Constructs a ToDoCommand with the specified user interface, task list, and description.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list where the to-do task will be stored.
     * @param description The description of the to-do task.
     */
    public ToDoCommand(UserInterface ui, TaskList tasks, String description) {
        super(ui);
        this.tasks = tasks;
        this.description = description;
    }

    /**
     * Triggers the command to add a new to-do task to the task list.
     *
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String trigger() {
        return this.tasks.addTask(new ToDo(description, false));
    }
}
