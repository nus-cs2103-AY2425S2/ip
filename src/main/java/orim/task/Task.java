package orim.task;

/**
 * Represents Users' tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert !description.equals("") : "description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        assert !description.equals("") : "description cannot be empty";
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public String mark() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n " + this.toString();
    }

    /**
     * Marks the task as not done.
     */
    public String unmark() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n " + this.toString();
    }

    public boolean getIsMark() {
        return this.isDone;
    }

    /**
     * Transforms the state and detail of the tasks to a string.
     * Will be store in the data file and be reloaded when user open Orim again.
     *
     * @return log to represents the state and detail of the tasks
     */
    public String generateLog() { // generate log of the task
        return "";
    }

    public boolean containKeyword(String searchKey) {
        return description.contains(searchKey);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}