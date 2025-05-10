package kyrie;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected DateTime deadline;

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, DateTime deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline task.
     * @param deadline The deadline of the task.
     * @param tags The tags of the deadline task.
     */
    public Deadline(String name, DateTime deadline, TagList tags) {
        super(name, TaskType.DEADLINE, tags);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     * 
     * @return The deadline of the task.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + this.deadline.toInputFormatString();
    }

    /**
     * Returns the string representation of the deadline task.
     * 
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}