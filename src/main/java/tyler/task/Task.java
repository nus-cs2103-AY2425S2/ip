package tyler.task;

import tyler.ui.Ui;

/**
 * Represents a basic Task. Meant to only be inherited from and not instantiated.
 */
public class Task {
    //@@author VikramGoyal23-reused
    // Reused from https://nus-cs2103-ay2425s2.github.io/website/se-book-adapted/projectDuke/

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task instance.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //@@author

    private void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the completion status of the task as complete.
     *
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Sets the completion status of the task as complete.
     *
     * @param ui the UI object to display the marking.
     */
    public void markAsDone(Ui ui) {
        setDone(true);
        ui.showMessage("\t " + "I've marked this task as done: " + "\n"
                + "\t\t" + this);
    }

    /**
     * Sets the completion status of the task as incomplete.
     *
     */
    public void markAsUndone() {
        setDone(false);
    }

    /**
     * Sets the completion status of the task as incomplete.
     *
     * @param ui the UI object to display the marking.
     */
    public void markAsUndone(Ui ui) {
        setDone(false);
        ui.showMessage("\t " + "I've marked this task as incomplete: " + "\n"
                + "\t\t" + this);
    }

    /**
     * Returns description of task instance.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a letter representing the type of task for inherited versions of Task; empty string here.
     * @return Empty String
     */
    public String getCategory() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getCategory() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
