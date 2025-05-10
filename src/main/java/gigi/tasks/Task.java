package gigi.tasks;

/**
 * Represents a task in Gigi.
 * This class serves as parent base class for different types of tasks,
 * such as ToDo, Deadline, and Event tasks
 */
public class Task {
    private String taskName;
    private boolean isComplete;

    /**
     * Constructs a new task with the specified name.
     * The task is initially marked as incomplete.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    /**
     * Returns the status icon indicating whether the task is complete or not.
     *
     * @return A string representing task's completion status.
     */
    public String getStatusIcon() {
        return (isComplete ? "[X]" : "[ ]");
    }

    public String getTaskname() {
        return taskName;
    }

    /**
     * Marks this task as done.
     * The task's completion status is updated to true.
     *
     * @param index The index of the task in the list (not used in this implementation).
     */
    void markDone(int index) {
        this.isComplete = true;
    }

    /**
     * Marks this task as not done.
     * The task's completion status is updated to false.
     *
     * @param index The index of the task in the list (not used in this implementation).
     */
    void markUndone(int index) {
        this.isComplete = false;
    }

    /**
     * Converts the task into a formatted string for storage.
     *
     * @return A string representation of the task for saving.
     */
    public String convertToText() {
        return isComplete + " | " + this.taskName;
    }

    /**
     * Returns a string representation of the task,
     * including its status icon and name.
     *
     * @return A formatted string describing the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isComplete() {
        return isComplete;
    }
}

