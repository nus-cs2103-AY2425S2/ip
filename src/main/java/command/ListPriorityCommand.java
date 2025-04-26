package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

public class ListPriorityCommand extends Command {

    public ListPriorityCommand() {}

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        return roster.printPriorityTask();
    }
}
