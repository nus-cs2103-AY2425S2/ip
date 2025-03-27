package yapper.tasktypes;

import yapper.exceptions.MissingTaskArgs;

/**
 * Represents a ToDo task, which consists only of a task name without any date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task from the given user request.
     * The request must be formatted as: {@code todo task_name}.
     *
     * @param request The user input containing the task name.
     * @throws MissingTaskArgs If the task name is missing from the request.
     */
    public ToDo(String request) {
        this.request = request;
        String[] splitString = request.split(" ", 2);
        if (splitString.length < 2 || splitString[1].trim().isEmpty()) {
            throw new MissingTaskArgs("\tHey! Don't just tell me the type of command, tell me "
                    + "what your task is. And leave a space between words, will ya.");
        }
        this.taskName = splitString[1];
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
