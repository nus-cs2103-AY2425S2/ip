package nana.logic;

/**
 * Represents an event task.
 * An event task is a task that starts and ends at specific times.
 */
public class Event extends Task {

    private String startTime;
    private String endTime;

    /**
     * Constructs a new Event instance with the specified description, start time, and end time.
     *
     * @param description the description of the event task
     * @param startTime the start time of the event task
     * @param endTime the end time of the event task
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        priority = Priority.UPPER_QUARTILE;
    }

    /**
     * Constructs a new Event instance with the specified description, start time, end time, and completion status.
     *
     * @param description the description of the event task
     * @param startTime the start time of the event task
     * @param endTime the end time of the event task
     * @param isDone the completion status of the event task
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
        priority = Priority.UPPER_QUARTILE;
    }

    /**
     * Returns a string representation of the event task.
     * The string representation includes the task type, task description, start time, and end time.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to:" + endTime + ")";
    }

    /**
     * Returns a string representation of the event task for storage.
     * The string representation includes the completion status, task type, task description, start time, and end time.
     *
     * @return a string representation of the event task for storage
     */
    @Override
    public String toStorage() {
        return toStorageIsDone() + " E event " + description + " /from " + startTime + " /to " + endTime;
    }

}
