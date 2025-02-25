package alex.command;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
