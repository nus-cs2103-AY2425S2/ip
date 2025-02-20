package prophet.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task that spans a period of time.
 */
public class EventTask extends Task {
    private static final String TYPE = "E | ";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Initialises a newly created EventTask object with a description and a time period.
     * @param description the description of the task
     * @param from the start of the time period
     * @param to the end of the time period
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the task is due between the event's duration.
     * @param date the date to check against
     */
    @Override
    public boolean isDueOn(LocalDate date) {
        return (this.from.toLocalDate().isBefore(date) && this.to.toLocalDate().isAfter(date))
                || this.from.toLocalDate().equals(date) || this.to.toLocalDate().equals(date);
    }

    /**
     * Returns the due date on a task.
     * For a {@link ToDoTask}, there is no deadline
     */
    @Override
    public LocalDateTime getDueDateTime() {
        return this.to;
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return EventTask.TYPE + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the event.
     * @return the string that represents the event description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription()
                + " from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + "\n";
    }
}
