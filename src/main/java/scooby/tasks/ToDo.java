package scooby.tasks;

public class ToDo extends Task {

    /**
     * Constructs a new ToDo task.
     *
     * @param description the name of the chatbot.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if the given object is equal to this task instance.
     *
     * @param obj The object to compare with this task.
     * @return {@code true} if both the description and type are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ToDo todo) {
            return this.description.equalsIgnoreCase(todo.description);
        }
        return false;
    }
}