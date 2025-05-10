package eve.command;

import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command for finding a string in a task.
 */
public class FindCommand implements Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.getMatchingTasks(this.description);
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
