package mab.command;

import java.util.ArrayList;

import mab.MabException;
import mab.util.MabStorage;
import mab.task.Task;

/**
 * Handles marking tasks as done or not done based on position argument.
 */
public class MarkingCommand extends Command {
    private final boolean isMarkAsDone;

    /**
     * Creates a new task marking command.
     * 
     * @param args The position argument as string
     * @param markAsDone True to mark as done, false to unmark
     */
    public MarkingCommand(String args, boolean markAsDone) {
        super(args);
        this.isMarkAsDone = markAsDone;
    }

    /**
     * Updates task status at specified position and persists changes.
     * 
     * @param list The task list to modify
     * @throws MabException If argument is invalid or position out of bounds
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

        list.get(pos - 1).setDone(isMarkAsDone);
        new MabStorage().write(list);
        return String.format(
                        "Nice! I marked task %s: %s as %s\n",
                        args, 
                        list.get(pos - 1).toString(),
                        isMarkAsDone ? "done :)" : "not done :p");
    }
}
