package gigi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in Gigi.
 * This class extends {@code Task} and includes an additional attribute to store
 * the deadline of the task.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Deadlines extends Task {
    public static final String ICON_DEADLINE = "[D]";
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("d MMM yyyy,"
                    + " "
                    + "h:mma");

    private String taskName;
    private boolean isComplete;
    private LocalDateTime deadline;


    /**
     * Constructs a deadline task with a given name and deadline.
     * @param taskName The name of the deadline task.
     * @param deadline The due date and time of the task.
     */
    public Deadlines(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
        this.isComplete = false;
    }

    /**
     * Constructs a deadline task with a given name, deadline, and completion status.
     *
     * @param taskName   The name of the deadline task.
     * @param deadline   The due date and time of the task.
     * @param isComplete The completion status of the task.
     */
    public Deadlines(String taskName, LocalDateTime deadline, boolean isComplete) {
        super(taskName);
        this.deadline = deadline;
        this.isComplete = isComplete;
    }

    /**
     * Returns the status icon for a deadline task.
     *
     * @return A formatted status string with "[D]" indicating a deadline.
     */
    public String getStatusIcon() {
        return ICON_DEADLINE + super.getStatusIcon(); // mark done task with X
    }

    public boolean isComplete() {
        return super.isComplete();
    }

    /**
     * Marks this deadline task as done.
     *
     * @param index The index of the task in the list.
     */
    void markDone(int index) {
        super.markDone(index);
    }

    /**
     * Marks this deadline task as not done.
     *
     * @param index The index of the task in the list.
     */
    void markUndone(int index) {
        super.markUndone(index);
    }

    /**
     * Converts the deadline task into a formatted string for storage.
     *
     * @return A string representation of the deadline task for saving.
     */
    public String convertToText() {
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return ICON_DEADLINE + " | " + super.convertToText() + " | " + formattedDeadline;
    }

    /**
     * Returns a string representation of the deadline task, including its due date.
     *
     * @return A formatted string describing the deadline task.
     */
    public String toString() {
        return super.toString()
                + " (by: "
                + this.deadline.format(dateTimeFormatter)
                + ")";
    }
}

