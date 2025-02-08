package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException;

    public boolean isExit() {
        return false;
    }

}
