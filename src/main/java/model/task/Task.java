package model.task;

public abstract class Task {

    protected String name;
    protected Boolean isMarked = false;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Constructs a new Task with the specified name and marked status.
     *
     * @param name the name of the task
     * @param isMarked the marked status of the task
     */
    public Task(String name, Boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + name;
    }

    /**
     * Checks if this task is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if this task is equal to the specified object, false
     * otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task) {
            return this.name.equals(task.name) && this.isMarked.equals(task.isMarked);
        }
        return false;
    }

    /**
     * Returns a data string representation of the task. In gereral, the data
     * string representation of a task is in the format:
     *
     * C|isMarked|name|any|other|fields
     *
     * C is an uppercase character representing the type of the task
     *
     * @return a data string representation of the task
     */
    public abstract String toDataString();
}
