package task;

/*
An abstract task class that serves as the base for other types of tasks
Includes basic features for a task: the task description and status(done or not done yet)
 */
public abstract class Task {

    public enum Status{
        FINISHED, UNFINISHED;
    }

    protected String description;
    protected Status status;
    protected static int taskId = 0;
    protected int priority;

    public Task(String dsc, int p) {
        this.description = dsc;
        this.status = Status.UNFINISHED;
        this.taskId = taskId++;
        this.priority = p;
    }

    /**
     * Returns the priority value a.k.a. the number of points a user earns upon task completion
     * @return the priority value of a task
     */
    public int getPriority() {
        return priority;
    }

    public int getTaskID() {
        return taskId;
    }

    public String getStatusIcon() {
        return status == status.FINISHED ? "[X]"
                                         : "[ ]";
    }

    public void setCompleted() {
        this.status = status.FINISHED;
    }

    public void setNotCompleted() {

        this.status = status.UNFINISHED;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return status == Status.FINISHED;
    }

    @Override
    public String toString() {
        return "[Priority Score: " + getPriority() + "] " + getStatusIcon() + " " + getDescription();
    }
    public String toStorageString() {
        return "";
    };
}
