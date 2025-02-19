package tasks;

/**
 * Abstract parent class of all Task classes, used to encapsulate information about
 * a specific task that a user has added. Every task has a name and needs to keep
 * track of whether it is done.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for the Task class
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks a task as done if it is currently undone. If the operation is successful, return true.
     * If the task was already marked in the first place, return false
     *
     * @return True if changed from unmarked to marked, false if remain as marked
     */
    public boolean markDone() {
        if (!isDone) {
            this.isDone = true;
            return true;
        }

        return false;
    }

    /**
     * Unmarks a task as done if it is currently done. If the operation is successful, return true.
     * If the task was already unmarked in the first place, return false
     *
     * @return True if changed from marked to unmarked, false if remain as unmarked
     */
    public boolean unmarkDone() {
        if (isDone) {
            this.isDone = false;
            return true;
        }

        return false;
    }

    public String getFullName() {
        if (isDone) {
            return "[X] " + this.name;
        }

        return "[ ] " + this.name;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }
}
