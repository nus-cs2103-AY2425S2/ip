package adam.command;

import adam.core.TaskList;
import adam.exceptions.AdamException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    public abstract String execute(TaskList manager) throws AdamException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
