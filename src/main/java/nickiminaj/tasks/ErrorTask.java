package nickiminaj.tasks;

public class ErrorTask extends Task {
    private final String message;

    public ErrorTask(String message) {
        super("Error"); // Assuming Task has a constructor that takes a name
        this.message = message;
    }

    @Override
    public String serialize() {
        return "Gurllll! Invalid task format: " + message;
    }

    @Override
    public String toString() {
        return "[X] ERROR: " + message;
    }
}
