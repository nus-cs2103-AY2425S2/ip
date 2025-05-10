package bun.ui;

/**
 * DeleteCommand is the main entity we'll be using to handle commands to delete a task.
 * @author OVOtter
 */
public class DeletionCommand extends Command {
    private final int index;

    /**
     * Constructs a new instance of `DeletionCommand` with the specified parameters.
     *
     * @param index Index of the task to be deleted.
     */
    public DeletionCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Execute the command by deleting its task from the taskList.
     *
     * @param taskList TaskList to be updated by the command.
     * @param ui       Ui to be updated by the command.
     * @param storage  Storage to be updated by the command (not used).
     * @throws InvalidIndexException If index of command is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        Task task = taskList.deleteTask(index);
        storage.save(taskList);
        return ui.getDeleteTaskMessage(task, taskList.getSize());
    }
}
