package pixel.command;

import pixel.task.TaskList;
import pixel.util.PixelException;
import pixel.util.Storage;

/**
 * Represents the structure of a Command entered by the user, which performs a function when executed.
 */
public abstract class Command {
    protected boolean isExitCommand = false;

    public abstract String execute(TaskList taskList, Storage storage) throws PixelException;

    /**
     * Returns whether this Command is an ExitCommand
     *
     * @return isExitCommand boolean
     */
    public boolean isExitCommand() {
        return this.isExitCommand;
    }
}
