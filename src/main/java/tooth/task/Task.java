package tooth.task;

/**
 * Abstract Task Object
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    protected Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public abstract Task complete();

    public abstract Task incomplete();

    public abstract String serialize();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
