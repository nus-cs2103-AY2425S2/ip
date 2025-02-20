package tasks;

import exceptions.TaskException;
import tasks.priority.TaskPriority;

/**
 * This class represents a class with no deadline.
 * It extends from the {@link Task} class.
 *
 * @author Yashvan
 */
public class Todo extends Task {

    /**
     * Private constructor for the Todo class.
     *
     * @param description This is a description of what the todo task should be.
     * @param taskPriority This is the priority of the task.
     */
    private Todo(String description, TaskPriority taskPriority) {
        super(description, taskPriority);

        assert description != null && !description.isBlank() : "Description should not be null or empty";
        assert taskPriority != null : "TaskPriority should not be null";
    }

    /**
     * Creates an instance of Todo.
     * Factory method to parse input and create a Todo object.
     *
     * @param input The input string for the todo task.
     * @return A new Todo object.
     * @throws TaskException If the input format is invalid.
     */
    public static Todo create(String input) throws TaskException {
        assert input != null && !input.isBlank() : "Input should not be null or empty";

        String[] parts = input.split(" /priority ");
        String todoTask = parts[0].substring(4).trim();
        if (todoTask.isEmpty()) {
            throw new TaskException("PLEASE BRUH! Use: todo <description> /priority <LOW|MEDIUM|HIGH|URGENT> ._.");
        }

        TaskPriority taskPriority;
        try {
            taskPriority = parts.length > 1 ? TaskPriority.valueOf(parts[1].toUpperCase()) : TaskPriority.LOW;
        } catch (IllegalArgumentException e) {
            throw new TaskException("Get your priorities in order! Use: LOW, MEDIUM, HIGH, or URGENT!");
        }

        return new Todo(todoTask, taskPriority);
    }

    /**
     * Returns string representation of the object.
     *
     * @return Shows whether the todo task has or has not been completed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
