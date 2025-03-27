package yapper.tasktypes;

/**
 * Represents an abstract task that can be marked as completed or not completed.
 * It serves as a base class for different types of tasks.
 */
public abstract class Task {

    protected String request;
    protected String taskName;
    private boolean isCompleted = false;

    /**
     * Returns a string representation of the task, indicating its completion status.
     *
     * @return A formatted string showing whether the task is completed or not.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + taskName;
    }

    /**
     * Marks the task as completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setNotCompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns the original user input associated with this task.
     *
     * @return The user input string used to create this task.
     */
    public String getUserInput() {
        return this.request;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    protected String getTaskName() {
        return this.taskName;
    }
}
