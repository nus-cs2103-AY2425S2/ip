import java.io.Serializable;
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
    public String statusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public abstract String toFileFormat();

    public String toString() {
        return this.statusIcon() + " " + this.description;
    }

}
