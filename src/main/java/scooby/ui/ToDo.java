package scooby.ui;

public class ToDo extends Task {

    /**
     * Constructs a new ToDo task.
     *
     * @param desc the name of the chatbot.
     */
    public ToDo(String desc) {
        super(desc);
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
}