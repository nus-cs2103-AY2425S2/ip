package paimon.commands;

import paimon.items.Todo;
import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandDelete class is a subclass of Command that deletes a task from the TaskList.
 */
public class CommandDelete extends Command {
    private int index;

    public CommandDelete(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList t, Ui ui) {
        Todo d = t.remove(index);
        ui.print("Noted. I've removed this task:");
        ui.print(d);
        ui.print("Now you have " + t.size() + " tasks in the list.\n");
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        Todo d = t.remove(index);
        String res = "Noted. I've removed this task:\n" + d + "\nNow you have " + t.size() + " tasks in the list.\n";
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandDelete) {
            CommandDelete c = (CommandDelete) obj;
            return this.index == c.index;
        } else {
            return false;
        }
    }
}
