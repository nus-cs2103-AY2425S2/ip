package miki.task;

/**
 * TaskType enum represents the type of a task.
 */
public enum TaskType {
    EVENT,
    DEADLINE,
    TODO;

    /**
     * Returns the string representation of the task type.
     *
     * @return String representation of the task type.
     */
    @Override
    public String toString() {
        switch (this) {
        case EVENT:
            return "Event";
        case DEADLINE:
            return "Deadline";
        case TODO:
            return "ToDo";
        default:
            throw new IllegalArgumentException("Unknown task type: " + this);
        }
    }
}
