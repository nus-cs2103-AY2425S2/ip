package hamiltonianguy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DeadlineTask extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public DeadlineTask(String desc, String by) {
        super(desc);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! Invalid date format. Use yyyy-MM-dd HHmm (e.g., 2024-02-01 1800)");
        }
    }

    public DeadlineTask(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    public DeadlineTask(String desc, LocalDateTime by, boolean isDone) {
        super(desc, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDesc() + " | " + by.format(INPUT_FORMAT);
    }
}
