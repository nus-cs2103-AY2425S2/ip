package pixel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueBy;

    public Deadline(String desc, LocalDateTime dueBy) {
        super(desc);
        this.dueBy = dueBy;
    }

    public Deadline(String desc, boolean isDone, LocalDateTime dueBy) {
        super(desc, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("deadline\n%s\n%s\n%s", this.desc, this.isDone, this.dueBy);
    }
}
