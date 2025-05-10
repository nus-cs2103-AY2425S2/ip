package ferb.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + this.displayDone() + this.taskDescription();
    }

    /**
     * Returns a formatted string of the todo task for saving to a file.
     *
     * @return a formatted string of the todo task for saving to a file
     */
    @Override
    public String toSave() {
        return "T|" + super.toSave();
    }

    @Override
    public boolean isTodo() {
        return true;
    }

}
