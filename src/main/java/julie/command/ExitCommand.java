package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
