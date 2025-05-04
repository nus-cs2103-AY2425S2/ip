package softess;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * The task list whose tasks will be listed.
     */
    public TaskList tasks;

    /**
     * Constructs a ListCommand with the specified user interface and task list.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list whose tasks will be listed.
     */
    public ListCommand(UserInterface ui, TaskList tasks) {
        super(ui);
        this.tasks = tasks;
    }

    /**
     * Triggers the command to list all tasks in the task list.
     *
     * @return A string containing the list of all tasks.
     */
    @Override
    public String trigger() {
        return this.tasks.listTasks();
    }
}
