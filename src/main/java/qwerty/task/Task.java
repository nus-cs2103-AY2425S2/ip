package qwerty.task;

import qwerty.exceptions.EmptyTaskNameException;

/**
 * Represents a general task with a name and completion status.
 * This is a base class for different types of tasks, such as Deadline and Event.
 */
public class Task {

    protected String name; // The name or description of the task.
    protected boolean isDone; // The completion status of the task (true if completed, false if not).

    /**
     * Constructs a Task with the specified name.
     *
     * @param name The name or description of the task.
     * @throws EmptyTaskNameException If the task name is empty.
     */
    public Task(String name) throws EmptyTaskNameException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException();
        }
        this.name = name;
        this.isDone = false; // Task is not done initially.
    }

    /**
     * Marks the task as completed.
     */
    public void markTaskDone() {
        this.isDone = true; // Sets task status to completed.
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmarkTaskDone() {
        this.isDone = false; // Sets task status to not completed.
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the Task, including the completion status.
     *
     * @return A string representing the Task with its completion status and name.
     */
    @Override
    public String toString() {
        String check = this.isDone ? "X" : " "; // Displays X if task is done, otherwise space.
        return "[" + check + "] " + this.name; // Returns task with completion status.
    }

    /**
     * Returns a string representation of the Task for saving to file.
     * Subclasses should override this method to provide specific save formats.
     *
     * @return A string representing the Task in a save-friendly format.
     */
    public String toSaveString() {
        return ""; // Default implementation returns an empty string.
    }
}
