package command;

import task.Tasklist;
import ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {}

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.userGuide();
    }
}
