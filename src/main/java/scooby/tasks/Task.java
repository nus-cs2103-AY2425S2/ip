package scooby.tasks;

public class Task {
    protected String description;
    private boolean isChecked;

    /**
     * Constructs a Ui object for the given chatbot name.
     *
     * @param description the name of the chatbot.
     */
    Task(String description) {
        this.description = description;
        this.isChecked = false;
    }

    /**
     * Checks off the task.
     */
    public void setChecked() {
        this.isChecked = true;
    }

    /**
     * Unchecks the task.
     */
    public void setUnchecked() {
        this.isChecked = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isChecked ? "X" : " ") + "] " + description;
    }

    /**
     * Returns the raw representation of the String to be read from the txt file
     * Only overridden by Deadline and Event classes
     *
     * @return returns the string to be added into the txt file
     */
    public String toRawString() {
        return this.toString();
    }


    /**
     * Returns the value of the isChecked value inside the Task class
     *
     * @return returns the value of isChecked
     */
    public boolean isChecked() {
        return this.isChecked;
    }

    /**
     * Updates the task description.
     *
     * @param newDescription The new description to update.
     */
    public void updateDetails(String newDescription) {
        this.description = newDescription.trim();
    }
}