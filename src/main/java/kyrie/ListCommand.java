package kyrie;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command to list all tasks.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the tasks.
     * @param tasks The task list to list the tasks from.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.showTasks(tasks);
    }
}