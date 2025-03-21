package tyler.command;

import tyler.storage.Storage;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to trigger the end of the program.
 */
public class EndCommand extends Command {

    public EndCommand() {
        super(true);
    }

    /**
     * Shows the farewell message.
     *
     * @param tasks The list of tasks.
     * @param ui The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The unmodified list.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
        return tasks;
    }
}
