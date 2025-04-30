package nikingoda.Task;

public class Todo extends Task {
    /**
     * create TodoTask (use to create new task)
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * create TodoTask (use to parse saving task from saving file)
     *
     * @param description description of task
     * @param isDone      whether is done
     */

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "T|" + done + "|" + this.description;
    }
}
