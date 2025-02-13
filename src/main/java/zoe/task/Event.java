package zoe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which has a task name and a specific time period
 * defined by a start date (from) and an end date (to).
 * Extends the {@code Task} class to inherit common task properties.
 * Includes methods for getting the task type icon and formatted description.
 *
 * @author xumarcus
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an {@code Event} task with the specified task name, start date, and end date.
     *
     * @param taskName The name or description of the event task.
     * @param from The start date of the event, represented as a {@code LocalDate}.
     * @param to The end date of the event, represented as a {@code LocalDate}
     */
    public Event(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon of this task.
     *
     * @return A single-character string representing the type of the task, "E" for Event.
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a formatted description of the Event task.
     * The description includes the task name and the time period formatted as "MMM d yyyy".
     *
     * @return A string representing the description of the task.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("%s (from: %s to: %s)", taskName, from.format(formatter), to.format(formatter));
    }
}
