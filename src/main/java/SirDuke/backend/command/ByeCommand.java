package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;

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
     * @param toDoList
     * @param storage
     * @return String representing exit command
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        storage.saveDataToDisk(toDoList);
        return UI.sayBye();
    }
}
