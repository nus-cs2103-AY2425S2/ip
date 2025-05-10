package eve.command;

import eve.exception.EveException;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to be executed.
 */
public interface Command {
    public String execute(TaskList taskList, Storage storage)
            throws EveException;

    /**
     * Returns whether it is a command to exit the program.
     */
    public boolean isExit();

    /**
     * Returns whether it is a command to close the program window.
     */
    public boolean isCloseWindow();
}
