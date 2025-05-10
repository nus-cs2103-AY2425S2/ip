package kyrie;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructor for DeleteCommand.
     * 
     * @param index The index of the task to be deleted, 1-indexed.
     */
    public DeleteCommand(int index) {
        super();
        assert index > 0 : "Index of task to delete must be greater than 0";
        this.index = index;
    }

    /**
     * Executes the command to delete a task.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the deleted task.
     * @param tasks The task list to delete the task from.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) throws KyrieException {
        if (index > tasks.getTaskCount() || index < 1) {
            throw new KyrieException("Task does not exist in your list");
        }
        Task task = tasks.deleteTask(index);
        storage.saveData(tasks);
        ui.showDeletedTask(task, tasks);
    }
}