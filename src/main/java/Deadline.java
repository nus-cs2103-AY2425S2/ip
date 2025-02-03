public class Deadline extends Task {
    protected String by;
    private final String MARKER = "[D]";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return this.MARKER + " " + super.toString() + "(by: " + this.by + ")";
    }
}
