package mab.command;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Collections;

import mab.task.Task;
import mab.MabException;

/**
 * sorts the list of tasks by their start date and time.
 *
 * @field args The arguments of the command.
 *
 */
public class SortCommand extends Command {

    public SortCommand(String args) {
        super(args);
    }

    /**
     * sorts the list of tasks by their start date and time.
     *
     * @param list The list of tasks to perform the command on.
     * @throws MabException If the command fails to execute due to missing or invalid arguments.
    */
    @Override
    public String execute(ArrayList<Task> list) throws MabException {
        Collections.sort(list, (t1, t2) -> {
            LocalDateTime time1 = t1.getStartDateTime();
            LocalDateTime time2 = t2.getStartDateTime();

            if (time1 == null && time2 == null) return 0;
            if (time1 == null) return 1;
            if (time2 == null) return -1;

            return time1.compareTo(time2);
        });
        return "I've sorted the list for you!";
    }
}
