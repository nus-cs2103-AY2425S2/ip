package miki.task;

import java.time.LocalDateTime;

/**
 * Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     */
    public Deadline(String description, LocalDateTime deadline, String... tags) {
        super(description, TaskType.DEADLINE, tags);
        this.deadline = deadline;
    }

    /**
     * Returns the task in storage format.
     *
     * @return task in storage format.
     */
    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat()
                + " | " + deadline;
    }

    /**
     * Returns the task in string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "\n"
                + "        Deadline: "
                + deadline.getDayOfMonth() + " "
                + deadline.getMonth() + " "
                + deadline.getYear() + ", "
                + String.format("%02d", deadline.getHour()) + ":"
                + String.format("%02d", deadline.getMinute());
    }
}
