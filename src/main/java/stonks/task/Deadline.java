package stonks.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Type of task
 * Has a description, completion status and deadline
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(formatter));
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1:0, this.description, this.deadline);
    }
}
