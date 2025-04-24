package jimmy.tasks;

/**
 * The {@code Todo} class represents a task without any date/time attached to it.
 * It is a subclass of {@code Task} and provides specific implementations for
 * representing a to-do task both in the UI and when saving to a file.
 *
 * <p>To-do tasks are typically simple tasks that need to be done without any
 * specific deadline or schedule.</p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified name.
     *
     * @param name the description of the to-do task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Converts the to-do task into a file-friendly format for saving.
     * The format is:
     * <pre>
     * T | [1 or 0] | [task name]
     * </pre>
     * Where:
     * <ul>
     *     <li><b>T</b> indicates it's a to-do task.</li>
     *     <li><b>1</b> indicates the task is completed, and <b>0</b> means it's not done.</li>
     *     <li><b>[task name]</b> is the description of the task.</li>
     * </ul>
     *
     * @return the string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isCompleted ? "1" : "0") + " | " + name;
    }

    /**
     * Returns the string representation of the to-do task for display in the UI.
     * The format is:
     * <pre>
     * [T][] Task Name
     * </pre>
     * or
     * <pre>
     * [T][] Task Name
     * </pre>
     * depending on whether the task is completed or not.
     *
     * @return the string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[T][" + getStatus() + "] " + name;
    }
}
