package zoe.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an abstract task with status management capabilities.
 * Acts as a base class for all types of tasks.
 */
@RequiredArgsConstructor
public abstract class Task {
    @Getter
    protected final String taskName;
    /**
     * Represents the current status of the task.
     * Defaults to UNDONE when a task is created.
     */
    protected TaskStatus status = TaskStatus.UNDONE;

    /**
     * Returns the icon representing the status of the task.
     * "X" for DONE and a blank space for UNDONE.
     *
     * @return the status icon as a String.
     */
    public String getStatusIcon() {
        return switch (status) {
        case DONE -> "X";
        case UNDONE -> " ";
        };
    }

    /**
     * Returns the icon specific to the type of task.
     *
     * @return a String representing the task type icon.
     */
    public abstract String getTypeIcon();

    /**
     * Returns a brief description of the task.
     *
     * @return a String representing the task description.
     */
    public abstract String getDescription();

    /**
     * Marks the task as DONE by updating its status.
     */
    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    /**
     * Marks the task as UNDONE by resetting its status.
     */
    public void markAsUndone() {
        status = TaskStatus.UNDONE;
    }

    /**
     * Generates the full description of the task,
     * which includes the type icon, status icon, and description.
     *
     * @return a formatted String representing the full task details.
     */
    public String getFullDescription() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }

    /**
     * Enum representing the status of a task.
     * A task can either be marked as DONE or UNDONE.
     */
    public enum TaskStatus {
        DONE,
        UNDONE
    }
}
