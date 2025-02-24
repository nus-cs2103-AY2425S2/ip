package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.response.Response.RESPONSE_TYPE;
import model.storage.Storage;
import model.task.TaskList;

public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param tasks The list of tasks.
     * @param storage The storage.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        return new Response(RESPONSE_TYPE.LIST_TASKS, tasks.toString());
    }

}
