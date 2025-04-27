package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that marks task as incomplete in TaskList
 */
public class UnmarkCommand implements Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Execute task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        assert index >= 0;
        ui.say("Unmarking task: " + index);
        tasks.unmark(index);
    }
}
