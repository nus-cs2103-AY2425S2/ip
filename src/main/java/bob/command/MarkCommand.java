package bob.command;

import java.io.IOException;

import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {

    private int number;

    public MarkCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute() throws IndexOutOfBoundsException, IOException {
        TaskList.markTask(number);
        String output = Formatter.format("Bob is on it! Marked the following as done [X]:",
                TaskList.getTask(number).toString());
        return output;
    }
}
