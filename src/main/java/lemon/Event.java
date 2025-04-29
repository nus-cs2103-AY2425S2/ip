/**
 * Represents a subclass of the Task class, a task with a starting and ending time
 * The description argument describes what the event is about
 * The start and end argument describe the starting and ending time of the event
 */


package lemon;

public class Event extends Task{
    protected String start;
    protected String end;

    /**
     * Construct an event task with a specified description, start and end
     * @param description The description of the task
     * @param start The starting time of the task
     * @param end The ending time of the task
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "/" + start + "/" + end;
    }
}
