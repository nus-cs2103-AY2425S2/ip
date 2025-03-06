package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandEmpty is a Command that does nothing.
 * This is used to handle wrong user command.
 */
public class CommandEmpty extends Command {
    @Override
    public boolean execute(TaskList t, Ui ui) {
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        return "Missing Command.\n";
    }
}
