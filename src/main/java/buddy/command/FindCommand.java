package buddy.command;

import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.storage.DataStorage;
import buddy.task.TaskList;

/**
 * Represents the type Find command.
 */
public class FindCommand extends Command {

    /**
     * Instantiates a new Find command.
     *
     * @param args the args from the user
     */
    public FindCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        if (taskList.getLength() == 0) {
            return "You have no task in your list.\n";
        }
        TaskList filteredList = taskList.filter(args.get(0));
        if (filteredList.getLength() == 0) {
            return "No tasks found based on your keyword.\n";
        }
        return Display.filterTask(filteredList);
    }
}
