package eve.command;

import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to list all the tasks in the taskList.
 */
public class ListCommand implements Command {
    /**
     * Lists all the tasks in the taskList.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     */
    public String execute(TaskList taskList, Storage storage) {
        String message = "Here are the tasks in your list:\n";
        return message + taskList.toString();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
