package tyler.command;

import tyler.storage.Storage;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command for when the user makes an unknown input.
 */
public class UnknownCommand extends Command {

    /**
     * Represents an action to perform on the list of tasks and then returns the list.
     *
     * @param tasks   The list of tasks which the command should be operated on (unused).
     * @param ui      The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The transformed list.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("\t !!I'm sorry, I don't know what that means!!");
        return tasks;
    }
}
