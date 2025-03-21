package Tom.tasks;

/**
 * Represents a basic to-do task without any time constraints.
 */
public class ToDo extends Task{

    /**
     * Creates a Todo task with a description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description){
        super(description, TaskType.TODO);
    }

    /**
     * Creates a Todo task with a description.
     *
     * @param description The description of the task.
     * @param status If the task is completed or not
     */
    public ToDo(String description, boolean status){
        super(description, TaskType.TODO, status);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The formatted string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
