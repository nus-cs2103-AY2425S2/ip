package mab.command;

import java.util.ArrayList;

import mab.MabException;
import mab.util.MabStorage;
import mab.task.Task;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String args) {
        super(args);
    }

    /**
     * Deletes a task from the list based on a 1-based position argument.
     * The operation will persist changes through the MabStorage update mechanism.
     *
     * @param list The task list containing elements to modify
     * @throws MabException If arguments are invalid or position is out of bounds
     */
    @Override
    public String execute(ArrayList<Task> list) throws MabException {
        if (args.isBlank()) throw new MabException("argument cannot be empty");
        int pos;
        try {
            pos = Integer.parseInt(args);
        } catch(NumberFormatException e) {
            throw new MabException(String.format("%s is not a valid number", args));
        }
        if (pos < 1 || pos > list.size()) {
            throw new MabException(String.format("argument cannot be beyond range 1 - %d", list.size()));
        }

        Task t = list.remove(pos - 1);
        new MabStorage().write(list);
        return String.format("Okay! I''ll delete task %s: %s\n", args, t.toString());
    }
}
