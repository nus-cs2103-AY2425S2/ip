package marshmallow.commands;

import marshmallow.MarshmallowException;
import marshmallow.tasks.Event;

/**
 * Represents a command to add an event task to the task list.
 */
public class ListAddEventCommand extends ListAddTaskCommand {
    /**
     * Constructs a command to add an event task to the task list.
     *
     * @param description Description of the event task.
     * @param startTime Start time of the event task.
     * @param endTime End time of the event task.
     */
    public ListAddEventCommand(String description, String startTime, String endTime) {
        if (startTime == null) {
            throw new MarshmallowException("Please tell me when this event starts!");
        }

        if (endTime == null) {
            throw new MarshmallowException("Please tell me when this event ends!");
        }

        this.task = new Event(description, startTime, endTime);
    }
}
