package kaji.task;

/**
 * Represents an Event task with a specific start and end date-time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String type, Boolean isDone, String description, String start, String end) {
        super(type, isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time of this event.
     *
     * @return the start time as a String
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the end time of this event.
     *
     * @return the end time as a String
     */
    public String getEnd() {
        return end;
    }


    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
