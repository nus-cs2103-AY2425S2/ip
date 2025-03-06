package paimon.commands;

import paimon.items.Todo;
import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * CommandInsert class is a subclass of Command that 
 * Insert a new Todo task and adds it to the TaskList at given index.
 */
public class CommandInsert extends Command {
    private Todo td;
    private int index;

    /**
     * Constructor for CommandInsert class
     * 
     * @param td todo task to be inserted
     * @param index index of insertion
     */
    public CommandInsert(Todo td, int index) {
        this.td = td;
        this.index = index;
    }

    @Override
    public boolean execute(TaskList t, Ui ui) {
        // t.add(this.td);
        t.insert(this.td, this.index);
        
        ui.print("Got it. I've added this task:");
        ui.print(this.td);
        ui.print("Now you have " + t.size() + " tasks in the list.\n");
        return true;
    }

    @Override
    public String executeToString(TaskList t, Ui ui) {
        // t.add(this.td);
        t.insert(this.td, this.index);

        String res = "Got it. I've added this task:\n" 
                + this.td + "\nNow you have " + t.size() 
                + " tasks in the list.\n";
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandInsert) {
            CommandInsert c = (CommandInsert) obj;
            return this.td.equals(c.td) && this.index == c.index;
        } else {
            return false;
        }
    }
}
