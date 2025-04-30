package nikingoda.Task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor for task with description and status isDone (for loading old tasks from storage)
     *
     * @param description description
     * @param isDone      status whether it is done
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String toFile() {
        return this.description;
    }


    public void mark() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * constructor with isDone set to false in default (for new task created)
     *
     * @param description description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
