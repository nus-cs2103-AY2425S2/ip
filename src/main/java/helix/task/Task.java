package helix.task;

import helix.enums.OutputSymbol;
import helix.enums.TaskStatus;
import helix.enums.TaskType;

/**
 * Represents a generic task in Helix.
 */
public abstract class Task {
    private String description;
    private TaskStatus taskStatus;

    /**
     * Constructs a Task with the specified description and pending helix.task status.
     *
     * @param description the description of the helix.task
     */
    public Task(String description) {
        this.description = description;
        this.taskStatus = TaskStatus.PENDING;
    }

    /**
     * Marks the helix.task as done by setting the completion status to COMPLETED.
     */
    public void markAsDone() {
        this.taskStatus = TaskStatus.COMPLETED;
    }

    /**
     * Marks the helix.task as not done by setting the completion status to PENDING.
     */
    public void markAsUndone() {
        this.taskStatus = TaskStatus.PENDING;
    }

    /**
     * Retrieves the description of the helix.task.
     *
     * @return the description of the helix.task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks the helix.task status, i.e. if the helix.task is marked as done.
     *
     * @return the status of the helix.task (PENDING or COMPLETED)
     */
    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * Returns the type of the helix.task. To be overridden by subclasses.
     *
     * @return the type of the helix.task as a TaskType Enum
     */
    public abstract TaskType getTaskType();

    /**
     * Returns helix.task-specific details. To be overridden by subclasses.
     *
     * @return Additional details about the helix.task.
     */
    public abstract String getTaskDetails();

    /**
     * Updates the task details based on the provided new details.
     *
     * <p>
     * Each subclass should implement this method to handle task-specific updates,
     * such as modifying the description, deadlines, or event timings.
     * </p>
     *
     * @param newDetails The updated task details.
     * @throws IllegalArgumentException if the new details format is invalid.
     */
    public abstract void updateTaskDetails(String newDetails);

    /**
     * Sets or updates the task description.
     *
     * @param description The new task description.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the helix.task, including its completion status.
     *
     * @return the string representation of the helix.task
     */
    @Override
    public String toString() {
        String outputSymbol = (this.taskStatus == TaskStatus.COMPLETED)
                ? OutputSymbol.CHECKMARK.getSymbol() : " ";
        return "[" + outputSymbol + "] " + description;
    }
}
