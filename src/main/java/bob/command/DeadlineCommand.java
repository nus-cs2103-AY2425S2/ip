package bob.command;

import java.io.IOException;
import java.time.LocalDateTime;

import bob.task.Deadline;
import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.deadline = new Deadline(description, by);
    }

    @Override
    public String execute() throws IOException {
        TaskList.addTask(this.deadline);
        String output = Formatter.format("Bob is on it! I've added this task:",
                this.deadline.toString(),
                "Now you have " + TaskList.getCount() + " task(s).");
        return output;
    }
}
