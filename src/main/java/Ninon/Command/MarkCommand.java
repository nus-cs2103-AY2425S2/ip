package Ninon.Command;

import Ninon.Storage;
import Ninon.TaskList;
import Ninon.Ui;

/**
 * Represents a command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task to be marked or unmarked.
     */
    public int index;

    /**
     * Indicates whether the task should be marked as done (true) or not done (false).
     */
    public boolean is_Mark;

    /**
     * Constructs a MarkCommand with the specified task index and mark status.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param is_Mark True to mark as done, false to unmark.
     */
    public MarkCommand(int index, boolean is_Mark) {
        this.index = index;
        this.is_Mark = is_Mark;
    }

    /**
     * Returns false since this command does not exit the program.
     *
     * @return false, indicating the program should continue running.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the mark command by marking or unmarking the task as done,
     * displaying the output, and exporting the updated task list to storage.
     *
     * @param taskList The task list containing the task to be modified.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to handle saving and loading of tasks.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message;
        if (this.is_Mark) {
            message = taskList.mark(index);
            ui.output(message);
        } else {
            message = taskList.unmark(index);
            ui.output(message);
        }
        storage.export(taskList);
        return message;
    }
}

