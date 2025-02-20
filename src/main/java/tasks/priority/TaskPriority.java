package tasks.priority;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * This enums represents TaskPriority.
 * It allows any of the instances of {@link Todo}, {@link Deadline}
 * and {@link Event} to be assigned a priority.
 *
 * @author Yashvan
 */
public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    URGENT("Urgent");

    private final String displayName;

    TaskPriority(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the priority of the task.
     *
     * @return The priority ("Low", "Medium", "High", "Urgent") of the task.
     */
    public String getDisplayName() {
        return displayName;
    }


}
