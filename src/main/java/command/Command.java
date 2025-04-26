package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Base command class with an execute function
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }
    public abstract String execute(PriorityRoster priorityRoster, Ui ui, Storage storage);
}
