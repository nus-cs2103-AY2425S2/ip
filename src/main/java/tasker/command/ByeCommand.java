package tasker.command;

import tasker.task.TaskList;

/**
 * Exits the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
