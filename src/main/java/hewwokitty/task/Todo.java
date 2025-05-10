package hewwokitty.task;

/**
 * Represents a type of Task with a description
 */
public class Todo extends Task {
    /**
     * Returns a Todo object
     * @param message A string describing what the Todo is about
     */
    public Todo(String message) {
        super(message);
    }

    /**
     * Formats a string into the format meant to save to file
     * @return A String that has been formatted with the type Todo, isDone and description
     */
    @Override
    public String writeToFile() {
        return "T|" + super.writeToFile() + "\n";
    }

    /**
     * Formats a string into the format that is displayed to the user
     * @return A String to display the Todo information to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
