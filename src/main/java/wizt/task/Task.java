package wizt.task;


/**
 *  Represents the Task a user can create
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents a Constructor that takes in description
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void update(String newDescription) {
        this.description = newDescription;
    }
    /**
     * mark task as undone
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * @return task in a specified format
     */
    public String toString() {
        return (isDone ? "[X] " + this.description.trim() : "[ ] " + this.description.trim());
    }
}
