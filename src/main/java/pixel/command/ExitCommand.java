package pixel.command;

import pixel.task.TaskList;
import pixel.util.Storage;
import pixel.util.Ui;

/**
 * Represents an exit Command to terminate Pixel.
 */
public class ExitCommand extends Command {
    /**
     * Initiates termination procedures by Ui class, then sets the isExit flag to true.
     *
     * @param taskList TaskList storing the tasks
     * @param storage Storage object handling disk storage for this instance of Pixel
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.EXIT;
    }
}
