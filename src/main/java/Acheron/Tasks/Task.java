package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;

/**
 * Abstrat class for all tasks which contain the minimal fields and methods
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * A constructor for the Tasks class
     * @param name Te name of the task
     * @param isDone Whether the tasks is done or not. Required when generating tasks from the saved file
     * @throws TaskExceptions An error if invalid input is fed into the constructor
     */
    public Task(String name, boolean isDone) throws TaskExceptions {
        if (name == null) {
            throw new TaskExceptions();
        }
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of the task object
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] %s"
                    .formatted(this.name);
        } else {
            return "[] %s".formatted(this.name);
        }
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks an already done task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Generates the string content of the task for saving
     * @param isLast Indicates if the task is the last task in the task list object. Needed so the
     *             file writer does not add an unnecessary new line which can cause file corruption
     * @return The content of the task
     */
    public String saveTask(boolean isLast) {
        if (isDone) {
            return "|" + "X" + "|" + name;
        } else {
            return "|" + "O" + "|" + name;
        }
    };

    public boolean containsKeyword(String keyword) {
        return this.name.contains(keyword);
    }
}

