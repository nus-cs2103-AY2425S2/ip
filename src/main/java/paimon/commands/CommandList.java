package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandList is a Command that lists all the tasks in the task list.
 */
public class CommandList extends Command {
    @Override
    public boolean execute(TaskList t, Ui ui) {
        t.list_items();
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        return t.list_items_toString();
    }
}
