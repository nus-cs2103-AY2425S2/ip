package eve.command;

import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represent a command to exit the program.
 */
public class CloseWindowCommand implements Command {
    /**
     * Does nothing as window will be close.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     */
    public String execute(TaskList taskList, Storage storage) {
        return "";
    }

    public boolean isExit() {
        return true;
    }

    public boolean isCloseWindow() {
        return true;
    }
}
