package tasker.task;

import java.time.LocalDateTime;

/**
 * A task with a deadline.
 */
public class Deadline extends DateTimeTask {
    /** Deadline to complete task before */
    private LocalDateTime deadline;

    /**
     * Constructor class.
     *
     * @param description Description of this task.
     * @param deadline    Deadline of this task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.D);
        this.deadline = deadline;
    }

    /**
     * Constructor class.
     *
     * @param description Description of this task.
     * @param isDone      Whether this task is done.
     * @param deadline    Deadline of this task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, TaskType.D, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toStorage() {
        return String.format("%s|%s", super.toStorage(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.formatOutput(deadline));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        Deadline other = (Deadline) obj;
        return (deadline == null ? other.deadline == null : deadline.equals(other.deadline));
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (deadline == null ? 0 : deadline.hashCode());
    }
}
