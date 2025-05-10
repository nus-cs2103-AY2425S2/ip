package godbot.task;

/**
 * Represents an event task with a start and end time.
 * Inherits from the Task class and adds the from and to attributes.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event task with a description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     * @param isDone      The completion status of the event. {@code true} if completed, otherwise {@code false}.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Constructs an Event task with a description, start time, and end time.
     * The task is marked as not done by default.
     *
     * @param description The description of the event task.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task for display purposes.
     *
     * @return A formatted string showing the task type, status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the Event task to a file-friendly format for saving to storage.
     *
     * @return A string representing the task in the format {@code E | isDone | description | | from | to}.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}

