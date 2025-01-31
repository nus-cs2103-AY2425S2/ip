package taskmax.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String description, String dateTimeString) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + dateTime.format(outputFormatter) + ")";
    }
}
