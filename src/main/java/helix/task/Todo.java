package helix.task;

import helix.enums.TaskType;

/**
 * Represents a todo helix.task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo helix.task with the specified description.
     *
     * @param description The description of the todo helix.task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Updates the description of the Todo task.
     *
     * @param newDetails The new description of the todo task.
     */
    @Override
    public void updateTaskDetails(String newDetails) {
        setDescription(newDetails);
    }

    /**
     * Gets the type of the helix.task.
     *
     * @return A TaskType enum representing the type of the helix.task (TODO).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Gets additional details specific to a Todo helix.task.
     *
     * @return An empty string as Todo tasks have no additional details.
     */
    @Override
    public String getTaskDetails() {
        return ""; // No additional details for a Todo helix.task
    }

    /**
     * Returns the string representation of the Todo helix.task.
     *
     * @return A formatted string representing the Todo helix.task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.TODO.getCode() + "]" + super.toString();
    }
}
