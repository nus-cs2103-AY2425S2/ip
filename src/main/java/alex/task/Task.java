package alex.task;

/**
 * The general class of all tasks
 */
public class Task {
    private String content;
    private boolean isCompleted;

    public Task(String content) {
        assert content != null : "Task content cannot be null";

        this.content = content.trim();
        this.isCompleted = false;
    }

    public Task(String content, boolean isCompleted) {
        assert content != null : "Task content cannot be null";

        this.content = content;
        this.isCompleted = isCompleted;
    }

    // Constructor with String status, 1 for done and 0 for not done
    public Task(String content, String isCompleted) {
        this(content, isCompleted.equals("1"));
    }

    public String getContent() {
        return this.content;
    }

    public boolean isDone() {
        return this.isCompleted;
    }

    /**
     * Get the incomplete format to be saved in storage for subclasses
     * @return data to be saved
     */
    public String getSavedDataFormat() {
        String statusRep = isCompleted ? "1" : "0";
        return statusRep + " | " + this.content;
    }

    /**
     * Change the current status of completion
     * @param isSetDone the target status
     * @return the reponse message to be printed
     */
    public String setStatus(boolean isSetDone) {
        if (isSetDone) {
            this.isCompleted = true;
            return ("Nice! I've marked this task as done:\n" + this + "\n");
        } else {
            this.isCompleted = false;
            return ("Ok, I've marked this task as not done yet:\n" + this + "\n");
        }
    }

    @Override
    public String toString() {
        String checkBox = "[ ] ";
        if (this.isDone()) {
            checkBox = "[X] ";
        }
        return checkBox + getContent();
    }
}
