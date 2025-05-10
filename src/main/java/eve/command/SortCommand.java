package eve.command;

import eve.exception.EveException;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to list all the tasks in the taskList.
 */
public class SortCommand implements Command {
    /**
     * Sorts and lists all the tasks in the taskList in sorted order.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        taskList.sort();
        storage.writeToFile(taskList);
        StringBuilder message = new StringBuilder();
        message.append("The tasks in your list has been sorted based on their dates. ")
                .append("Here are the tasks in your list now:\n")
                .append(taskList);
        return message.toString();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
