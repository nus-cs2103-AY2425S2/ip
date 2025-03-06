package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandText is a Command that does nothing.
 * This is used to handle wrong user command.
 */
public class CommandText extends Command {

    private final String text;

    public CommandText(String s) {
        this.text = s;
    }

    @Override
    public boolean execute(TaskList t, Ui ui) {
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        return this.text + "\n";
    }

    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof CommandText) {
            CommandText c = (CommandText) obj;
            return this.text.equals(c.text);
        } else {
            return false;
        }
    }
}
