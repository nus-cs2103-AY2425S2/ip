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

}