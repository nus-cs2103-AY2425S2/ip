package amara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, a task with a single dateline to complete.
 * <p>
 * A {@code Deadline} task stores a description, a ompletion status and a due date.
 * It can be serialized into a saveable format and displayed in a user-friendly
 * string format.
 * </p>
 */
public class Deadline extends Task {
    private static final String STRING_FORMAT = "%s,%d,%s,%s";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static final int SORTING_ORDER = 1;
    private LocalDateTime dueDate;

    /**
     * Constructs a {@code Deadline} task with the given description and due date.
     *
     * @param taskDescription The description of the deadline task.
     * @param dueDate         The due date and time for the task.
     */
    public Deadline(String taskDescription, LocalDateTime dueDate) {
        super(taskDescription, false);
        this.dueDate = dueDate;
    }
    /**
     * Constructs a {@code Deadline} task with a specified completion status.
     *
     * @param status          The completion status of the task.
     * @param taskDescription The description of the deadline task.
     * @param dueDate         The due date and time for the task.
     */
    public Deadline(boolean status, String taskDescription, LocalDateTime dueDate) {
        super(taskDescription, status);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Override
    public String getSavedFormat() {
        return String.format(Deadline.STRING_FORMAT, "D", this.isMarked ? 1 : 0,
                this.taskDescription, this.dueDate);
    }

    @Override
    public int getSortingOrder() {
        return Deadline.SORTING_ORDER;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.isMarked ? "X" : " ",
                super.taskDescription,
                this.dueDate.format(Deadline.DATE_FORMATTER));
    }
}
