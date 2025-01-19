public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task.
     *
     * @param desc the name of the deadline task
     * @param from is the start date
     * @param to is the end date of the event
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}