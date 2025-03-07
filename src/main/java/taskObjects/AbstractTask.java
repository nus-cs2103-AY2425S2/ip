package taskObjects;

import exception.InvalidInputException;
import interfaces.ITask;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Abstract {@code AbstractTask} class that encapsulate parent behaviour of all Task
 */
public abstract class AbstractTask implements ITask {

    private String description = "";
    private boolean isCompleted;
    private final String type;

    /**
     * Constructs a {@code AbstractTask} instance
     *
     * @param description Simple description of the task
     * @param isCompleted Status of completion of the task
     * @param type        The type of task
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    public AbstractTask(String description, boolean isCompleted, String type)
            throws InvalidInputException {
        this.description = description;
        this.isCompleted = isCompleted;
        this.type = type;
        if (description == null || type == null) {
            throw new InvalidInputException("Sorry Commander, but there is missing data");
        }
    }

    /**
     * Gets the description of the task
     *
     * @return The description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * aets the completion status of the taks
     *
     * @return The completion status of the task
     */
    private String getStatus() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Marks the task to be completed
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete
     */
    public void markUndone() {
        this.isCompleted = false;
    }

    /**
     * Converts the task to a String format to be saved in storage
     *
     * @return String representation of task to be saved in storage
     */
    public String toFileFormat() {
        return this.type + " | " + this.isCompleted + " | "
                + this.description;
    }

    /**
     * Abstract method to return deadline to be implemented
     * by children class
     * @return Deadline of task
     */
    public abstract LocalDateTime getDeadline();

    /**
     * Get the String representation of Task
     *
     * @return String representation of Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] "
                + this.getDescription();
    }
}
