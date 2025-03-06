package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandGoodbye is a Command that terminates the program.
 */
public class CommandGoodbye extends Command {
    @Override
    public boolean execute(TaskList t, Ui ui) {
        ui.goodbye();
        return false;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        return "Goodbye paimon!";
    }
}
