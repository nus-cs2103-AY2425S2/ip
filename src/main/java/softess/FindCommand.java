package softess;

/**
 * Represents a command to find tasks based on a query.
 */
public class FindCommand extends Command {

    /**
     * The task list to search within.
     */
    private TaskList tasks;

    /**
     * The search query used to find matching tasks.
     */
    private String query;

    /**
     * Constructs a FindCommand with the specified user interface, task list, and query.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list to search within.
     * @param query The search query to find matching tasks.
     */
    public FindCommand(UserInterface ui, TaskList tasks, String query) {
        super(ui);
        this.tasks = tasks;
        this.query = query;
    }

    /**
     * Triggers the command to search for tasks matching the query.
     *
     * @return A string containing the matching tasks.
     */
    @Override
    public String trigger() {
        return this.tasks.findTasks(query);
    }
}
