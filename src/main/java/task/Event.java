package task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an Event task.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Creates an Event task from a saved file format.
     *
     * @param line The saved format string.
     * @return An Event task.
     */
    public static Event fromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1"); //
        Event task = new Event(parts[2], parts[3], parts[4]);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
