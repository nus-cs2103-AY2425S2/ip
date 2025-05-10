package helix.enums;

/**
 * Represents the status of a helix.task.
 */
public enum TaskStatus {
    PENDING(false),
    COMPLETED(true);

    private final boolean isDone;

    /**
     * Constructs a TaskStatus with the specified completion status.
     *
     * @param isDone true if the helix.task is completed, false otherwise.
     */
    TaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether the helix.task is completed.
     *
     * @return true if the helix.task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
