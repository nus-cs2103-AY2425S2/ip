package yuki.task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event task.
     *
     * @param taskName The name of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String isDone, String taskName, String from, String to) {
        super(taskName, isDone.equals("1"));
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " to " + to + ")";
    }
}
