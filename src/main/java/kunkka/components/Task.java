package kunkka.components;

/**
 * Represents a task.
 */
public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;
    protected int priority;

    /**
     * Constructor for Task.
     *
     * @param name Name of the task.
     * @param type Type of the task.
     * @param isDone Status of the task.
     * @param priority priority of the task.
     */
    public Task(String name, String type, boolean isDone, int priority) {
        this.name = name;
        this.type = type;
        this.isDone = isDone;
        this.priority = priority;
        assert name != null : "Task name cannot be null";
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the task.
     *
     * @return Status of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the priority of the task.
     *
     * @return Priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority Priority of the task.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + name;
    }
}
