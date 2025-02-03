public class Event extends Task {
    protected String from;
    protected String by;
    private final String MARKER = "[E]";

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + by;
    }

    @Override
    public String toString() {
        return this.MARKER + " " + super.toString() + "(from: " + this.from + " to: " + this.by + ")";
    }
}
