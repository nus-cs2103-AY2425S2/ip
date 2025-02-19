package mab.command;

import java.util.ArrayList;

import mab.task.Task;
import mab.MabException;

/**
 * Represents a command that the user can input.
 *
 * @field args The arguments of the command.
 *
 */
public abstract class Command {
    protected String args;

    public Command(String args) {
        this.args = args;
    }

    /**
     * Executes the command.
     *
     * @param list The list of tasks to perform the command on.
     * @throws MabException If the command fails to execute due to missing or invalid arguments.
    */
    public abstract String execute(ArrayList<Task> list) throws MabException;
}
