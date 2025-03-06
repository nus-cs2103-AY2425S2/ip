package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandUnmark marks a task at given index as not done.
 */
public class CommandUnmark extends Command {
    private int index;

    public CommandUnmark(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList t, Ui ui) {
        t.unmark(index);
        ui.print("OK, I've marked this task as not done yet:");
        ui.print(t.get(index) + "\n");
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        t.unmark(index);
        String res = "OK, I've marked this task as not done yet:\n" + t.get(index) + "\n";
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandUnmark) {
            CommandUnmark c = (CommandUnmark) obj;
            return this.index == c.index;
        } else {
            return false;
        }
    }
}
