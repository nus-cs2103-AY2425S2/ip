package bun.ui;

public class EditCommand extends Command {
    private final boolean isMarkOperation;
    private final int index;

    /**
     * Constructs a new instance of `EditCommand` with the specified parameters.
     *
     * @param isMarkOperation Whether the command is mark or unmark/
     * @param index           Index of task to mark/unmark.
     */
    public EditCommand(boolean isMarkOperation, int index) {
        super(false);
        this.isMarkOperation = isMarkOperation;
        this.index = index;
    }

    /**
     * Execute the command by marking or unmarking the task in the taskList.
     *
     * @param taskList TaskList to be updated by the command.
     * @param ui       Ui to be updated by the command.
     * @param storage  Storage to be updated by the command.
     * @return Response to be printed.
     * @throws InvalidIndexException If index is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        if (this.isMarkOperation) {
            Task task = taskList.markTask(this.index);
            storage.save(taskList);
            return ui.getMarkTaskMessage(task);
        } else {
            Task task = taskList.unmarkTask(this.index);
            storage.save(taskList);
            return ui.getUnmarkTaskMessage(task);
        }
    }
}
