package hamiltonianguy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public EventTask(String desc, String from, String to) {
        super(desc);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! Invalid date format. Use yyyy-MM-dd HHmm (e.g., 2024-02-01 1800)");
        }
    }

    public EventTask(String desc, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDesc() + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }
}

