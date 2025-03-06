package paimon.commands;

import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/**
 * Abstract class for all commands.
 * A command is an action that can be executed with a tasklist and ui given
 * a user input.
 */
public abstract class Command {
    /**
     * Execute the command with current tasklist and ui
     * 
     * @param t tasklist involved.
     * @param ui ui for interaction with user
     * @return boolean whether the program should continue to run after command is executed
     */
    public abstract boolean execute(TaskList t, Ui ui);

    /**
     * Similar to execute but returns a string instead for printing to screen.
     * 
     * @param t tasklist involved.
     * @param ui ui for interaction with user
     * @return String to be printed to screen
     */
    public abstract String executeToString(TaskList t, Ui ui);

    // same type of command is euqal
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command) {
            return true;
        } else {
            return false;
        }
    }
}
