package charlie;

public class Todo extends Task {
    public Todo(String activity) {
        super(activity);
    }

    public Todo(String activity, Boolean marked) {
        super(activity, marked);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that can be written to a file to save the Deadline task.
     * The format includes "T" for the task type, followed by the task description.
     *
     * @return A string representing the Deadline task in a file-save format.
     */
    public String writeToFile() {
        return "T" + super.writeToFile() + "\n";
    }
}
