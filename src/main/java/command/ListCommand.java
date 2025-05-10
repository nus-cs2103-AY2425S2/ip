package command;

import task.Tasklist;
import ui.Ui;

/**
 * Represents a command to print all the tasks in the tasklist to the UI.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Executes the list command: displays the tasks in the tasklist.
     * @param tasklist The tasklist to be displayed.
     * @param ui The UI used to print the tasks.
     */
    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.printTaskList(tasklist);
    }
}
