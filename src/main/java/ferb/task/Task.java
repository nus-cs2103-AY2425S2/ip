package ferb.task;

/**
 * Represents a task that can be added to a task list.
 */
public class Task {
    private boolean isDone;
    private String description;

    public Task(String description) {
        this(false, description);
    }

    public Task(boolean isDone, String description){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the string description of the task
     */
    public String taskDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return a string representation of the task's completion status
     */
    public String displayDone(){
        return this.isDone ? "[X] " : "[ ] ";
    }

    /**
     * Returns a boolean indicating whether the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return this.displayDone() + this.taskDescription();
    }

    /**
     * Returns a formatted string representation of the task for saving to a file.
     *
     * @return a formatted string representation of the task for saving to a file
     */
    public String toSave() {
        String id = this.isDone() ? "1" : "0";
        return id + "|" + this.taskDescription();
    }

    /**
     * Returns a boolean indicating whether the task description contains the keyword.
     *
     * @param keyword the keyword to search for
     * @return true if the task contains the keyword, false otherwise
     */
    public boolean contains(String keyword) {
        return this.taskDescription().contains(keyword);
    }

    /**
     * Returns a boolean indicating whether the task is a todo task.
     *
     * @return true if the task is a todo task, false otherwise
     */
    public boolean isTodo() {
        return false;
    }


}
