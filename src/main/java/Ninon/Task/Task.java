package Ninon.Task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    private Boolean isDone; // Indicates whether the task is completed.
    private String event; // Stores the task description.

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param event the description of the task
     */
    public Task(String event) {
        this.isDone = false;
        this.event = event;
    }

    /**
     * Marks the task as completed.
     */
    public void mark_As_Done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void mark_As_Not_Done() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * showing whether it is done or not.
     *
     * @return a formatted string representing the task status and description
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.event;
        }
        return "[ ] " + this.event;
    }

    /**
     * Formats the task's status and description for output.
     * The format follows "1 / event" if done and "0 / event" if not done.
     *
     * @return a formatted string indicating task completion status and description
     */
    public String formatOut() {
        if (this.isDone) {
            return "1 / " + this.event;
        }
        return "0 / " + this.event;
    }
}
