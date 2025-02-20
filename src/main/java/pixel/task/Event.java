package pixel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public Event(String desc, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("event\n%s\n%s\n%s\n%s", this.desc, this.isDone, this.from, this.to);
    }
}
