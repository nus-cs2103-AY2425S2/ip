package eve.command;

import eve.exception.EveException;
import eve.util.Storage;
import eve.util.TaskList;


/**
 * Represents a command to clear all the tasks in the taskList.
 */
public class ClearCommand implements Command {
    /**
     * Clears all the tasks in the taskList.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        taskList.clear();
        storage.writeToFile(taskList);
        return "Yay all the tasks in your list are cleared.";
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
