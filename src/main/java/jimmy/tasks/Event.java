package jimmy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jimmy.JimmyException;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It includes the start ({@code from}) and end ({@code to}) times for the event,
 * and provides methods to format the event for display and file storage.
 */
public class Event extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code Event} with the specified name, start time, and end time.
     *
     * @param name the name or description of the event.
     * @param from the start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to   the end time of the event in the format "yyyy-MM-dd HHmm".
     * @throws JimmyException if the date format is invalid.
     */
    public Event(String name, String from, String to) throws JimmyException {
        super(name);
        try {
            this.from = LocalDateTime.parse(from, inputFormatter);
            this.to = LocalDateTime.parse(to, inputFormatter);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
            throw new JimmyException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return the {@code LocalDateTime} representing when the event starts.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return the {@code LocalDateTime} representing when the event ends.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Converts the event to a file-friendly format for saving.
     * The format is:
     * <pre>
     * E | [1 or 0] | [event name] | [from date] | [to date]
     * </pre>
     *
     * @return the string representation of the event for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isCompleted ? "1" : "0") + " | " + name + " | " + from.format(inputFormatter)
                + " | " + to.format(inputFormatter);
    }

    /**
     * Returns the string representation of the event for display in the UI.
     * The format is:
     * <pre>
     * [E][] Event Name (from: Dec 02 2019, 6:00 PM to: Dec 02 2019, 8:00 PM)
     * </pre>
     *
     * @return the formatted string representation of the event.
     */
    @Override
    public String toString() {
        return "[E][" + getStatus() + "] " + name + " (from: " + from.format(outputFormatter)
                + " to: " + to.format(outputFormatter) + ")";
    }
}
