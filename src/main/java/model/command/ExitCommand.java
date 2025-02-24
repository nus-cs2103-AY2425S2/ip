package model.command;

import model.exception.AliceException;
import model.exception.AliceExit;
import model.response.Response;
import model.storage.Storage;
import model.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The list of tasks.
     * @param storage The storage.
     * @return The response to the user.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        storage.saveTasks(tasks);
        throw new AliceExit();
    }

}
