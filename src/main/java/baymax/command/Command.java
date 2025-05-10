package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;

/**
 * Represents an abstract command in the Baymax chatbot.
 * Commands are executed based on user input and can manipulate the task list,
 * interact with storage, and display output via the UI.
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary operations on the task list,
     * UI, and storage.
     *
     * @param tasks   The task list to be modified or accessed.
     * @param storage The storage handler to save or retrieve tasks.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws IOException;
}
