public class ToDo extends Task {
    private static final String MARKER = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return MARKER + " " + super.toString();
    }
}


