package perrygpt.tasks;

/**
 * The {@code TaskType} enum represents the type of tasks that can be managed in the application.
 * It defines three types of tasks:
 * <ul>
 *   <li>{@link #TODO}: A simple task with a description.</li>
 *   <li>{@link #DEADLINE}: A task with a description and a deadline.</li>
 *   <li>{@link #EVENT}: A task with a description, start time, and end time.</li>
 * </ul>
 */
public enum TaskType {
    /**
     * Represents a simple task with a description.
     */
    TODO,

    /**
     * Represents a task with a description and a deadline.
     */
    DEADLINE,

    /**
     * Represents a task with a description, start time, and end time.
     */
    EVENT
}
