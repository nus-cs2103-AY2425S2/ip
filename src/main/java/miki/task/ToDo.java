package miki.task;

/**
 * ToDo class represents a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /*
     * Constructor for ToDo class.
     */
    public ToDo(String description, String... tags) {
        super(description, TaskType.TODO, tags);
    }

    /**
     * Returns the task in storage format.
     *
     * @return task in storage format.
     */
    @Override
    public String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

    /**
     * Returns the task in string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString() + "\n";
    }
}
