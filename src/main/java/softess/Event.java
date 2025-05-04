package softess;

/**
 * Represents an event task with a start and end time.
 * An {@code Event} task includes a description, a time range (from - to),
 * and a completion status.
 *
 * This class extends {@link Task} and provides specific behavior for marking
 * the event as done or undone and formatting its representation.
 *
 * The expected format for storing event data follows:
 * {@code EVENT|status|description|from|to}.
 *
 * @author Hrishikesh Sathyian
 */
public class Event extends Task {

    /** The starting time of the event */
    protected String from;

    /** The ending time of the event */
    protected String to;

    /**
     * Constructs a new {@code Event} task.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The status of the task; {@code true} if completed, {@code false} otherwise.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Marks this event as completed and prints a confirmation message.
     */
    @Override
    public String markAsDone() {
        super.isDone = true;
        return "Nice! I've marked this task as done: \n " + this.toString();
    }

    /**
     * Marks this event as not completed and prints a confirmation message.
     */
    @Override
    public String markAsUnDone() {
        super.isDone = false;
        return "Nice! I've marked this task as undone: \n " + this.toString();
    }

    /**
     * Generates a formatted string representation of the event task for file storage.
     * The format follows: {@code EVENT|status|description|from|to}.
     *
     * @return A string representation of the event suitable for file storage.
     */
    @Override
    public String generateTextToFile() {
        int status = this.isDone ? 1 : 0;
        return "EVENT|%d|%s|%s|%s".formatted(status, this.description, this.from, this.to);
    }
}
