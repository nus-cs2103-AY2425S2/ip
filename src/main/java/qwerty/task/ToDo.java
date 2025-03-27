package qwerty.task;

import qwerty.exceptions.EmptyTaskNameException;

/**
 * Represents a ToDo task, which includes a description and completion status.
 * Inherits from {@link Task} and provides methods for formatting and saving the task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified name.
     *
     * @param name The name or description of the ToDo task.
     * @throws EmptyTaskNameException If the task name is empty.
     */
    public ToDo(String name) throws EmptyTaskNameException {
        super(name); // Calls the parent constructor to set the task name.
    }

    /**
     * Returns a string representation of the ToDo task, including the description and completion status.
     *
     * @return A string representing the ToDo task, including its completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Adds the "T" label for ToDo tasks and calls the parent toString method.
    }

    /**
     * Returns a string representation of the ToDo task for saving to file.
     *
     * @return A string representing the ToDo task in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.name; // Includes save format for task state.
    }
}
