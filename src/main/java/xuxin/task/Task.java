package xuxin.task;

/**
 * Represents a generic task with a name and completion status.
 */
public abstract class Task {
    protected String name;
    protected String status;
    protected String des = "[ ]";
    protected boolean isDone;

    /**
     * Constructs a Task with the given name and completion status.
     *
     * @param name The name of the task.
     * @param isDone Indicates whether the task is completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.status  = "[ ]";
        this.isDone = isDone;
    }

    /**
     * Marks this task as completed.
     */
    public void markTask() {
        this.status = "[x]";
        this.isDone = true;
    }

    /**
     * Unmarks this task as incomplete.
     */
    public void unmarkTask() {
        this.status = "[ ]";
        this.isDone = false;
    }

    public String toString() {
        return this.status + " " + this.name;
    }

    /**
     * Get the name of the Task
     * @return name
     */
    public String getName() {
        return this.name;
    }

    public abstract String toFileFormat();

    public boolean getIsDone() {
        return this.isDone;
    }
}
