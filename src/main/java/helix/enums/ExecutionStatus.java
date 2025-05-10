package helix.enums;

/**
 * Represents the execution status of the application.
 */
public enum ExecutionStatus {
    /**
     * Indicates that the program should continue running.
     */
    CONTINUE(false),

    /**
     * Indicates that the program should terminate.
     */
    EXIT(true);

    private final boolean isExit;

    /**
     * Constructs an ExecutionStatus with the specified exit status.
     *
     * @param isExit true if the program should terminate, false otherwise.
     */
    ExecutionStatus(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns whether the program should terminate.
     *
     * @return true if the program should terminate, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
