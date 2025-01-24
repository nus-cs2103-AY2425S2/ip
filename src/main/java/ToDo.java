public class ToDo extends Task {
    private final String MARKER = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return MARKER + " " + super.toString();
    }
}


