package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Lists all the tasks in the current roster
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String execute(PriorityRoster priorityRoster, Ui ui, Storage storage) {
        return priorityRoster.printRoster();
    }
}
