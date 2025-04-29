/**
 * Represents a task
 * The description argument describes what the task is about and
 * the isDone argument describes the status of the task, whether it has been done or not
 */

package lemon;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a task with a specified description
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String toString() {
        if(isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}
