package taskmax.task;

/**
 * Represents the different types of tasks supported in Taskmax.
 */
public enum TaskType {
    /**
     * Represents a to-do task without a specific deadline.
     */
    TODO,

    /**
     * Represents a task with a specific deadline.
     */
    DEADLINE,

    /**
     * Represents an event with a start and end time.
     */
    EVENT
}
