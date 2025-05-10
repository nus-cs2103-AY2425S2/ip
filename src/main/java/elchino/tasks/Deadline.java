package elchino.tasks;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for a deadline task.
     * @param description The description of the deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDate(deadline);
    }

    /**
     * Retrieves the deadline of the task.
     * @return The formatted deadline of the task.
     */
    public String getBy() {
        return deadline.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getBy());
    }

    @Override
    public String storeTask() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline.format(INPUT_DATE_FORMAT));
    }
}