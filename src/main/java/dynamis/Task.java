package dynamis;

/**
 * Represents a task that will be used in the TaskList.
 * Tasks can be marked as done.
 */
public abstract class Task {
    protected Boolean isDone;
    protected String name;

    /**
     * Constructs a new Task object.
     *
     * @param name The name of the task.
     */
    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Edits the isDone property.
     *
     * @param isDone The boolean value to change to.
     */
    public void editIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets and returns the value of the isDone property.
     *
     * @return The value of the isDone property.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
