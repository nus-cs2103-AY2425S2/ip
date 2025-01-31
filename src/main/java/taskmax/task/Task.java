package taskmax.task;

public class Task {
    private String description;
    private boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return isDone;
    }


    public String getStatusIcon() {   //Marks a checkbox
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {  //marks isDone as true
        isDone = true;
    }

    public void markAsNotDone() { //marks isDone as false
        isDone = false;
    }

    @Override
    public String toString() {  //default task string
        return "[" + getStatusIcon() + "] " + description;
    }
}
