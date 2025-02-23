package bob.command;

import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute() {
        return Formatter.formatTasks(TaskList.getTasks());
    }
}
