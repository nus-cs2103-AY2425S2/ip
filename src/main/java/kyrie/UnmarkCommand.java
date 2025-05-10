package kyrie;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private Integer index;
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructor for UnmarkCommand.
     * 
     * @param index The index of the task to be unmarked, 1-indexed.
     */
    public UnmarkCommand(Integer index) {
        super();
        assert index > 0 : "Index of task to unmark must be greater than 0";
        this.index = index;
    }
    
    /**
     * Executes the command to unmark a task as done.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the unmarked task.
     * @param tasks The task list to unmark the task from.
     */ 
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) throws KyrieException {
        if (index > tasks.getTaskCount() || index < 1) {
            throw new KyrieException("Task does not exist in your list");
        }
        tasks.unmarkTaskAsDone(index);
        ui.showMarkedTask(tasks.getTask(index));
        storage.saveData(tasks);
    }
}