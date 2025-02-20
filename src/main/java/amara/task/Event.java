package amara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task, a task with a start and end date.
 * <p>
 * A {@code Event} task stores a description, a completion status and a start and end date.
 * It can be serialized into a saveable format and displayed in a user-friendly string format.
 * </p>
 */
public class Event extends Task {
    private static final String STRING_FORMAT = "%s,%d,%s,%s,%s";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static final int SORTING_ORDER = 2;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an {@code Event} with the given description and time range.
     *
     * @param taskDescription The description of the event.
     * @param startDate       The start date and time of the event.
     * @param endDate         The end date and time of the event.
     */
    public Event(String taskDescription, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskDescription, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an {@code Event} with a specified completion status.
     *
     * @param status          The completion status of the event.
     * @param taskDescription The description of the event.
     * @param startDate       The start date and time of the event.
     * @param endDate         The end date and time of the event.
     */
    public Event(boolean status, String taskDescription,
            LocalDateTime startDate, LocalDateTime endDate) {
        super(taskDescription, status);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    @Override
    public String getSavedFormat() {
        return String.format(Event.STRING_FORMAT, "E", this.isMarked ? 1 : 0,
                this.taskDescription, this.startDate, this.endDate);
    }

    @Override
    public int getSortingOrder() {
        return Event.SORTING_ORDER;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.isMarked ? "X" : " ",
                super.taskDescription,
                this.startDate.format(DATE_FORMATTER),
                this.endDate.format(DATE_FORMATTER));
    }
}
