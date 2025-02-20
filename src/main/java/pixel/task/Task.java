package pixel.task;

/**
 * Represents a Task to be completed by the user.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

    abstract String toFileFormat();
}
