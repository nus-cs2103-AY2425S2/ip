package eve.task;

import java.time.LocalDateTime;

/**
 * Represents a generic task added by users.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Initialize a task with its description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of task. X for done and blank space for not done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    };

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    };

    /**
     * Returns whether the input string is contained in this task's description.
     *
     * @param inputString String to check
     * @return True if string is contained in this task's description.
     */
    public boolean containString(String inputString) {
        return description.contains(inputString);
    }

    /**
     * Returns date and time to be compared for comparator.
     */
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Returns a string representing this task to be stored in the data file.
     */
    public String toDataString() {
        return description;
    }

    /**
     * Returns a string describing this task to be displayed on the user interface.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
