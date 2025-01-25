package tasker.task;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {
    /** Deadline to complete task before */
    private String deadline;

    /**
     * Constructor class.
     *
     * @param description Description of this task.
     * @param deadline Deadline of this task.
     */
    public Deadline(String description, String deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
