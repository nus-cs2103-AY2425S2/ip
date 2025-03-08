package wizt.task;
/**
 *  Represents the Event Task
 */

public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    /**
     * @return Event task in a specified format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
