package command;

import task.Tasklist;
import ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.printFilteredTasklist(tasklist, keyword);
    }
}
