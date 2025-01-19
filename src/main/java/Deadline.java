public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task.
     *
     * @param desc the name of the deadline task.
     * @param by is the date where the task is due by.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}