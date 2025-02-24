package boink.tasks;

import java.time.LocalDateTime;

import boink.utils.Utils;

/**
 * This class represents a task with a datetime deadline.
 */

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     * @param name The name of task.
     * @param deadline The deadline of task.
     */

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Creates output in format for saving task.
     * @return Task as string to write to file.
     */

    @Override
    public String saveTask() {
        return String.format(
                "D | %d | %s | %s",
                super.getDoneIntValue(),
                this.getName(),
                Utils.saveDateTime(this.deadline)
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[D] %s by %s",
                super.toString(),
                Utils.getDateTime(this.deadline)
        );
    }
}
