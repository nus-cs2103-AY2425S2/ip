package ricky.command;

import ricky.RickyException;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents a command to mark a task as done or undone in the task list.
 */
public class MarkCommand extends Command {

    private int index;
    private boolean isDone;

    /**
     * Constructs a MarkCommand with the specified index and done status.
     *
     * @param index  The index of the task to be marked.
     * @param isDone The status to mark the task (true for done, false for undone).
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command, marking the task as done or undone and printing the result.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The UI to print the result message.
     * @param storage The storage to save the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RickyException {
        if (index < 1 || index > tasks.size()) {
            throw new RickyException("Invalid task number!");
        }
        if (isDone) {
            if (tasks.get(index - 1).getIsDone()) {
                throw new RickyException("Task is already done!");
            }
            tasks.markDone(index - 1);
            return ui.getMarkMessage(tasks.get(index - 1));
        } else {
            if (!tasks.get(index - 1).getIsDone()) {
                throw new RickyException("Task is not completed yet!");
            }
            tasks.markUndone(index - 1);
            return ui.getUnmarkMessage(tasks.get(index - 1));
        }
    }
}
