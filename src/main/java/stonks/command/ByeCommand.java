package stonks.command;

import stonks.storage.Storage;
import stonks.task.TaskManager;

/**
 * Command to display a goodbye to the user
 */
public class ByeCommand extends Command {
    private static final String MESSAGE = "bye bye";
    @Override
    public String execute(TaskManager tm, Storage storage) {
        return MESSAGE;
    }
}