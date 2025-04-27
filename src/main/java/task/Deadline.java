package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific due date and time.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    protected LocalDateTime dateTime;

    /**
     * Constructs a Deadline task with a description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param dateTime    The due date and time of the task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(dateTime, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the string format of the deadline task for file storage.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toFileFormat() {
        String isTaskMarkedAsDone = isDone ? "1" : "0";
        return "D | " + isTaskMarkedAsDone + " | " + description + " | " + dateTime.format(INPUT_FORMAT);
    }

    /**
     * Updates a specific component of a Deadline task.
     * The user can update the task description or the due date/time.
     *
     * @param component The component to update ("description" or "by").
     * @param newValue  The new value for the specified component.
     * @throws IllegalArgumentException If an invalid component is provided or if the date format is incorrect.
     */
    @Override
    public void updateComponent(String component, String newValue) {
        if (component.equalsIgnoreCase("description")) {
            updateDescription(newValue);
        } else if (component.equalsIgnoreCase("date")) {
            try {
                this.dateTime = LocalDateTime.parse(newValue, INPUT_FORMAT);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format for 'date'. Use yyyy-MM-dd HHmm.");
            }
        } else {
            throw new IllegalArgumentException("Invalid component '" + component
                    + "' for Deadline tasks. Available: description, date");
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return The formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(OUTPUT_FORMAT) + ")";
    }
}
