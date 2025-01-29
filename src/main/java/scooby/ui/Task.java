package scooby.ui;

public class Task {
    private String desc;
    private boolean isChecked;

    /**
     * Constructs a Ui object for the given chatbot name.
     *
     * @param desc the name of the chatbot.
     */
    Task(String desc) {
        this.desc = desc;
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
        return "[" + (isChecked ? "X" : " ") + "] " + desc;
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
}