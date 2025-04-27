package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task with a specific start and end time.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param timeStart   The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param timeEnd     The end time of the event in "yyyy-MM-dd HHmm" format.
     * @throws IllegalArgumentException If the provided date format is incorrect.
     */
    public Event(String description, String timeStart, String timeEnd) {
        super(description);
        try {
            this.timeStart = LocalDateTime.parse(timeStart, INPUT_FORMAT);
            this.timeEnd = LocalDateTime.parse(timeEnd, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use YYYY-MM-DD HHmm.");
        }
    }

    /**
     * Returns the string format of the event for file storage.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toFileFormat() {
        String isTaskMarkedAsDone = isDone ? "1" : "0";
        return "E | " + isTaskMarkedAsDone + " | " + description + " | "
                + timeStart.format(INPUT_FORMAT) + " | " + timeEnd.format(INPUT_FORMAT);
    }

    /**
     * Updates a specific component of an Event task.
     * The user can update the task description, start date, or end date.
     *
     * @param component The component to update ("description", "start", or "end").
     * @param newValue  The new value for the specified component.
     * @throws IllegalArgumentException If an invalid component is provided or if the date format is incorrect.
     */
    @Override
    public void updateComponent(String component, String newValue) {
        if (component.equalsIgnoreCase("description")) {
            updateDescription(newValue);
        } else if (component.equalsIgnoreCase("start")) {
            try {
                this.timeStart = LocalDateTime.parse(newValue, INPUT_FORMAT);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format for 'start'. Use YYYY-MM-DD HHmm.");
            }
        } else if (component.equalsIgnoreCase("end")) {
            try {
                this.timeEnd = LocalDateTime.parse(newValue, INPUT_FORMAT);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format for 'end'. Use YYYY-MM-DD HHmm.");
            }
        } else {
            throw new IllegalArgumentException("Invalid component '" + component
                    + "' for Event tasks. Available: description, start, end");
        }
    }

    /**
     * Returns a string representation of the event.
     *
     * @return The formatted string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeStart.format(OUTPUT_FORMAT)
                + " to: " + timeEnd.format(OUTPUT_FORMAT) + ")";
    }
}
