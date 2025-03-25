package buddy.command;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.storage.DataStorage;
import buddy.task.TaskList;

/**
 * Represents the type Help command.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        return Display.help();
    }
}
