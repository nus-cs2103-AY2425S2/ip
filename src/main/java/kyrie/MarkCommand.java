package kyrie;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private Integer index;
    public static final String COMMAND_WORD = "mark";

    /**
     * Constructor for MarkCommand.
     * 
     * @param index The index of the task to be marked as done, 1-indexed.
     */
    public MarkCommand(Integer index) {
        super();
        assert index > 0 : "Index of task to mark as done must be greater than 0";
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the marked task.
     * @param tasks The task list to mark the task from.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) throws KyrieException {
        if (index > tasks.getTaskCount() || index < 1) {
            throw new KyrieException("Task does not exist in your list");
        }
        tasks.markTaskAsDone(this.index);
        ui.showMarkedTask(tasks.getTask(this.index));
        storage.saveData(tasks);
    }
}