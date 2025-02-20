package tasks;

import tasks.priority.TaskPriority;

/**
 * This abstract class represents a task.
 * It is inherited by {@link Todo}, {@link Deadline} and {@link Event}.
 *
 * @author Yashvan
 */
public abstract class Task {
    private String description;
    private TaskPriority taskPriority;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description This is a description of what the task should be.
     */
    public Task(String description, TaskPriority taskPriority) {
        this.description = description;
        this.taskPriority = taskPriority;
        isDone = false;
    }

    /**
     * Marks the task as done.
     * This is used mainly in the TaskManager class.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     * This is used mainly in the TaskManager class.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Checks if task is empty.
     *
     * @return Whether the task is empty or not.
     */
    public boolean isEmpty() {
        return this.description == null || this.description.trim().isEmpty();
    }

    /**
     * Gets the description of any one of the tasks.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns string representation of the object.
     *
     * @return Shows whether the task has or has not been completed.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] "
                + this.description
                + " (Priority: " + this.taskPriority.getDisplayName() + ")";
    }

    /**
     * Returns whether a task is done.
     *
     * @return boolean value indicating if task has been completed.
     */
    public boolean isDone() {
        return isDone;
    }
}
