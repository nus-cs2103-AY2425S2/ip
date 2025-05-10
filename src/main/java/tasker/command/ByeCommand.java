package tasker.command;

import tasker.Storage;
import tasker.task.TaskList;

/**
 * Exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Exits the program.
     *
     * @param tasks   The task list to execute this command on.
     * @param storage The storage for saving changes to.
     * @return The exit phrase.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        System.exit(0);
        return "Bye. Hope to see you again soon!";
    }
}
