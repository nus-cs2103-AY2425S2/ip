package mab.task;

import java.time.LocalDateTime;

/**
 * Abstract base class representing a task with common attributes.
 * Provides foundation for task management and persistence.
 */
public abstract class Task {
    String text;
    boolean isDone;

    /**
     * Creates a new generic task.
     * 
     * @param t Text description of task
     * @param d Initial completion status
     */
    public Task(String t, boolean d) {
        text = t; isDone = d;
    }

    /**
     * Updates task completion status.
     * 
     * @param d New completion status
     */
    public void setDone(boolean d) {
        isDone = d;
    }
    /**
     * Updates task description.
     * 
     * @param t New task description
     */
    public void setText(String t) {
        text = t;
    }
    /**
     * @return Text description of task
     */
    public String getText() {
        return text;
    }
    /**
     * @return Task completion status
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Generates storage-formatted string representation.
     * 
     * @return String suitable for persistent storage
     */
    public abstract String getSaveString();

    /**
     * returns the start date and time of the task
     *
     * @return Start date and time of task
     */
    public abstract LocalDateTime getStartDateTime();

    /**
     * @return Display format: {@code [status] [description]}
     * where status is "X" if done, " " if not done
     */
    @Override
    public String toString() { return String.format("[%s] %s", isDone ? "X": " ", text); }
}
