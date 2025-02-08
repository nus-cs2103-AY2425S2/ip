package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
