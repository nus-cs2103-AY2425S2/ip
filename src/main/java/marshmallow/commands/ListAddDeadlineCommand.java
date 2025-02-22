package marshmallow.commands;

import marshmallow.MarshmallowException;
import marshmallow.tasks.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class ListAddDeadlineCommand extends ListAddTaskCommand {
    /**
     * Constructs a command to add a deadline task to the task list.
     *
     * @param description Description of the deadline task.
     * @param endTime End time of the deadline task.
     */
    public ListAddDeadlineCommand(String description, String endTime) {
        if (endTime == null) {
            throw new MarshmallowException("Please tell me when you need to do this by!");
        }

        this.task = new Deadline(description, endTime);
    }
}
