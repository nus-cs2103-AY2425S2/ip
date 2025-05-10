package kyrie;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String name;
    private DateTime deadline;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructor for AddDeadlineCommand.
     * 
     * @param name The name of the deadline task.
     * @param deadline The deadline of the task.
     */
    public AddDeadlineCommand(String name, DateTime deadline) {
        super();
        assert name != null : "Name of deadline cannot be null";
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Executes the command to add a deadline task.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the added task.
     * @param tasks The task list to add the task to.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        Task task = new Deadline(name, deadline);
        tasks.addTask(task);
        ui.showAddedTask(task);
        storage.saveData(tasks);
    }
}