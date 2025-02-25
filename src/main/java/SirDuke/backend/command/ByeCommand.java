package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;

// @@author testing1234567891011121314
// Reused from https://github.com/testing1234567891011121314/ip
// with minor modifications

/**
 * The ByeCommand class represents a command to exit SirDuke.
 */
public class ByeCommand extends Command {

    /**
     * Instantiate a ByeCommand
     */
    public ByeCommand() {
        super("");
    }

    /**
     * Bye exits SirDuke.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves data and exits SirDuke
     * @param toDoList the list of tasks to write to disk
     * @param storage the storage object to save data to disk
     * @return String representing ByeCommand's execution status
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        storage.saveDataToDisk(toDoList);
        return UI.sayBye();
    }
}
