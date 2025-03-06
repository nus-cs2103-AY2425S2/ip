package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandMark marks a task at given index as done.
 */
public class CommandMark extends Command {
    private int index;

    public CommandMark(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList t, Ui ui) {
        t.mark(index);
        ui.print("Nice! I've marked this task as done:");
        ui.print(t.get(index) + "\n");
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        t.mark(index);
        String res = "Nice! I've marked this task as done:\n" + t.get(index) + "\n";
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandMark) {
            CommandMark c = (CommandMark) obj;
            return this.index == c.index;
        } else {
            return false;
        }
    }
}
