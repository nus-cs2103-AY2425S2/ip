package bob.command;

import java.io.IOException;

import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to unmark a task, i.e. not completed.
 */
public class UnmarkCommand extends Command {

    private int number;

    public UnmarkCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute() throws IOException {
        TaskList.unmarkTask(number);
        String output = Formatter.format("Bob is on it! Marked the following as undone [ ]",
                TaskList.getTask(number).toString());
        return output;
    }
}
