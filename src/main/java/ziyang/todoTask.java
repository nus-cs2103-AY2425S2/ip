package ziyang;

public class todoTask extends Task {
    /**
     * Constructs a new todo task.
     * @param description The description of the task
     */
    public todoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * @return A string in the format "[T][status]description"
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }
}
