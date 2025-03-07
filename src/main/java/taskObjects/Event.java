package taskObjects;

import exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@code Event} class to handle events with start and end dates
 */
public class Event extends AbstractTask {

    private LocalDateTime fromWhen;
    private LocalDateTime toWhen;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a {@code Event} class with all the information
     *
     * @param description Simple description of the task
     * @param isCompleted Completion status of the task
     * @param from        Start dateTime time of the event
     * @param to          End dateTime time of the event
     * @throws InvalidInputException
     */
    public Event(String description, boolean isCompleted, String from, String to)
            throws InvalidInputException {
        super(description, isCompleted, "E");
        try {
            this.fromWhen = LocalDateTime.parse(from, INPUT_FORMAT);
            this.toWhen = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format Commander. Pleaser use d/M/yyyy HHmm (e.g., 2/12/1989 1800).");
        }

        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new InvalidInputException("Sorry Commander, but there is missing data");
        }
    }

    /**
     * Return String format of event to be saved into storage
     *
     * @return String format of event to be saved into storage
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + this.fromWhen.format(OUTPUT_FORMAT) + " | "
                + this.toWhen.format(OUTPUT_FORMAT);
    }

    /**
     * Gets the end datetime for the event class
     * @return datetime of the end of the event
     */
    @Override
    public LocalDateTime getDeadline() {
        return this.toWhen;
    }

    /**
     * Gets String representation of event
     *
     * @return String represetation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromWhen.format(OUTPUT_FORMAT) + " to: "
                + this.toWhen.format(OUTPUT_FORMAT) + ")";
    }

}
