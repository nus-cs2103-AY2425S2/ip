package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.TaskList;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param chat The chat application
     * @param storage The storage.
     * @return The response to the user.
     * @throws AliceException If an error occurs during execution.
     */
    public abstract Response execute(TaskList tasks, Storage storage) throws AliceException;

}
