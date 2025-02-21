package grass;
// event class for grass

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = formatDateTime(from);
        this.to = formatDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.description.equals(other.description) && this.from.equals(other.from) && this.to.equals(other.to);
        }
        return false;
    }
}