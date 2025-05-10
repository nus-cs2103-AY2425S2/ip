package gigi.tasks;

/**
 * Represents a ToDo task in Gigi.
 * This class extends {@code Task} and represents a simple task
 * without any associated date or time.
 */
@SuppressWarnings("checkstyle:Regexp")
public class ToDos extends Task {
    public static final String ICON_TODO = "[T]";
    private String taskName;
    private boolean isComplete;

    /**
     * Constructs a ToDo task with a given name.
     *
     * @param taskName The name of the ToDo task.
     */
    public ToDos(String taskName) {
        super(taskName);
        this.isComplete = false;
    }

    /**
     * Constructs a ToDo task with a given name and completion status.
     *
     * @param taskName   The name of the ToDo task.
     * @param isComplete The completion status of the task.
     */
    public ToDos(String taskName, boolean isComplete) {
        super(taskName);
        this.isComplete = isComplete;
    }

    /**
     * Returns the status icon for a ToDo task.
     *
     * @return A formatted status string with "[T]" indicating a ToDo.
     */
    @Override
    public String getStatusIcon() {
        return ICON_TODO + super.getStatusIcon();
    }

    /**
     * Marks this ToDo task as done.
     *
     * @param index The index of the task in the list.
     */
    @Override
    void markDone(int index) {
        super.markDone(index);
    }

    /**
     * Marks this ToDo task as not done.
     *
     * @param index The index of the task in the list.
     */
    void markUndone(int index) {
        super.markUndone(index);
    }

    /**
     * Converts the ToDo task into a formatted string for storage.
     *
     * @return A string representation of the ToDo task for saving.
     */
    public String convertToText() {
        return ICON_TODO + " | " + super.convertToText();
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string describing the ToDo task.
     */
    public String toString() {
        return super.toString();
    }

    public boolean isComplete() {
        return super.isComplete();
    }

}
