package kyrie;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected DateTime start;
    protected DateTime end;

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String name, DateTime start, DateTime end) {
        super(name, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @param tags The tags of the event task.
     */
    public Event(String name, DateTime start, DateTime end, TagList tags) {
        super(name, TaskType.EVENT, tags);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event task, suitable for saving to file.
     * 
     * @return The string representation of the event task, suitable for saving to file.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + this.start.toInputFormatString() + " | " + this.end.toInputFormatString();    
    }
    
    /**
     * Returns the string representation of the event task.
     * 
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}