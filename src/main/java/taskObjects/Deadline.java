package taskObjects;

import exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@code Deadline} class representing a task that has deadline or due dates
 */
public class Deadline extends AbstractTask {

    private LocalDateTime byWhen;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a {@code Deadline} instance with all details
     *
     * @param description Simple description of the Task
     * @param isCompleted Completion status of the Task
     * @param byWhen      The DateTime due date of the task
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    public Deadline(String description, boolean isCompleted, String byWhen) throws InvalidInputException {
        super(description, isCompleted, "D");
        try {
            this.byWhen = LocalDateTime.parse(byWhen, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format Commander. Pleaser use d/M/yyyy HHmm (e.g., 2/12/1989 1800).");
        }

        if (description.isBlank() || byWhen.isBlank()) {
            throw new InvalidInputException("Sorry Commander, but there is missing data");
        }
    }

    /**
     * Returns String format to be saved into storage
     *
     * @return String format to be saved into storage
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + this.byWhen;
    }

    /**
     * Gets the deadline of the Deadline class
     * @return Deadline of task
     */
    @Override
    public LocalDateTime getDeadline() {
        return this.byWhen;
    }

    /**
     * Gets the String representation of the deadline
     *
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byWhen.format(OUTPUT_FORMAT) + ")";
    }
}
