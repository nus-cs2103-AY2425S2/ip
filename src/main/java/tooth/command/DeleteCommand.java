package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that deletes task from tasklist
 */
public class DeleteCommand implements Command {

    private int index;

    /**
     * Deletes a task from  the tasklist
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        assert index >= 0;
        tasks.delete(index);
        ui.say("Deleted tasks: " + index);
    }
}
