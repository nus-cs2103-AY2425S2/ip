package command;

import task.Tasklist;
import ui.Ui;

public class MotivationCommand extends Command {

    public MotivationCommand() {}

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.printMotivation();
    }
}

