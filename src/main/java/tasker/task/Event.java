package tasker.task;

/**
 * A task with a starting and ending time.
 */
public class Event extends Task {
    /** The starting time of this task */
    private String start;
    /** The ending time of this task */
    private String end;

    /**
     * Class constructor.
     *
     * @param description The description of this task.
     * @param start The starting time of this task.
     * @param end The ending time of this task.
     */
    public Event(String description, String start, String end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    /**
     * Class constructor.
     *
     * @param description The description of this task.
     * @param isDone Whether this task is done.
     * @param start The starting time of this task.
     * @param end The ending time of this task.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, "E", isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
