package Tom.commands;

import Tom.TomException;
import Tom.tasks.TaskList;

/**
 * Abstract Command class that all commands inherit from.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The TaskList instance to perform operations on.
     * @return The string representation of the command's response.
     * @throws TomException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks) throws TomException;

}
