package chitti;

/**
 * Represents an event-based task.
 * This subclass of Task stores information about the event's start and end times,
 * and provides methods to display the task in a user-friendly format and in a file-friendly format.
 */
public class EventTask extends Task{
    private String start;
    private String end;
    /**
     * Constructs an EventTask with a specified name, start time, and end time.
     *
     * @param name The name of the task.
     * @param start The start time of the event in string format (e.g., "2025-02-24 14:00").
     * @param end The end time of the event in string format (e.g., "2025-02-24 16:00").
     */
    public EventTask(String name, String start, String end){
        super(name);
        this.start = start;
        this.end = end;
    }
    /**
     * Returns the string representation of the EventTask for display.
     * The format will include the task type, the task's name, the start time, and the end time.
     *
     * @return A string representing the event in a human-readable format.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
    /**
     * Returns the string format of the EventTask for saving to a file.
     * This format is used to serialize the task's data for later retrieval.
     *
     * @return A string representing the event's data in a format suitable for storage in a file.
     */
    @Override
    public String toFileFormat(){
        return "E|" + super.toFileFormat() + "|" + this.start + "|" + this.end;
    }
}
