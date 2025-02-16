package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;

/**
 * Represents a events task
 */
public class ToDos extends Task {

    /**
     * Constructor for the todo class
     * @param name The name of the tasks
     * @param isDone Whether the tasks is done or not. Required when generating tasks from the saved file
     * @throws TaskExceptions Throws an exception if a wrong input is supplied
     */
    public ToDos(String name, boolean isDone) throws TaskExceptions {
        super(name, isDone);
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of what the task should produce
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generates the string content of the task for saving
     * @param isLast Indicates if the task is the last task in the task list object. Needed so the
     *             file writer does not add an unecessary new line which can cause file corruption
     * @return The content of the task
     */
    @Override
    public String saveTask(boolean isLast) {
        return "T" + super.saveTask(isLast) + (isLast ? "" : "\n");
    }
}
