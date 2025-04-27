package stonks.command;

import stonks.exceptions.StonksException;
import stonks.storage.Storage;
import stonks.task.TaskManager;

/**
 * Displays an error message
 */
public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) throws StonksException {
        this.message = message;
        throw new StonksException(message);
    }

    @Override
    public String execute(TaskManager tm, Storage storage) {
        return (message);
    }
}