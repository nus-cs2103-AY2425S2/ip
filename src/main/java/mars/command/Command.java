package mars.command;

import mars.task.TaskList;
import mars.ui.UI;
import mars.storage.Storage;


/**
 * Represents a command that can be executed on the task list.
 * All commands should extend this abstract class and implement the execute method
 *
 * */
public abstract class Command {
    /**
     * Executes command with the given task list, user interface, and storage.
     *
     * @param tasklist The task list to modify.
     * @param ui       The user interface
     * @param storage  handler for the loading and storing of tasks
     *
     */
    public abstract void execute(TaskList tasklist,UI ui, Storage storage);

    public boolean isExit(){
        return false;
    }


}
