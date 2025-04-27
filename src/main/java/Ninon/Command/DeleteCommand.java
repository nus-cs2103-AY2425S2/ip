package Ninon.Command;

import Ninon.Storage;
import Ninon.TaskList;
import Ninon.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    public int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be removed from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
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
     * Executes the delete command by removing the task from the task list,
     * displaying the output, and exporting the updated task list to storage.
     *
     * @param taskList The task list to modify.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to handle saving and loading of tasks.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.delete(index);
        ui.output(message);
        storage.export(taskList);
        return message;
    }
}