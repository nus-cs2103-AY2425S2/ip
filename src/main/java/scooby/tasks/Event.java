package scooby.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // Input format (with time)
    private static final DateTimeFormatter INPUTFORMATTER  = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    // Output format
    private static final DateTimeFormatter OUTPUTFORMATTER  = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a new Event task.
     *
     * @param description the name of the event task
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.startTime = parseDate(from); // Parse the date and time
        this.endTime = parseDate(to); // Parse the date and time
    }

    /**
     * Parses the date and time string from input format "yyyy-MM-dd HHmm" to LocalDateTime.
     *
     * @param dateStr the date and time in the form of String, in the format: "yyyy-MM-dd HHmm"
     * @return the formatted LocalDateTime object
     */
    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, INPUTFORMATTER); // Parse both date and time
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(OUTPUTFORMATTER) + " to: " + endTime.format(OUTPUTFORMATTER) + ")";
    }

    /**
     * Returns the raw representation of the String to be read from the txt file
     * @return returns the string to be added into the txt file
     */
    @Override
    public String toRawString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(INPUTFORMATTER) + " to: " + endTime.format(INPUTFORMATTER) + ")";
    }

    /**
     * Checks if the given object is equal to this task instance.
     *
     * @param obj The object to compare with this task.
     * @return {@code true} if both the description and type are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event otherEvent) {
            return this.description.equalsIgnoreCase(otherEvent.description)
                     && this.startTime.equals(otherEvent.startTime)
                     && this.endTime.equals(otherEvent.endTime);
        }
        return false;
    }

    /**
     * Updates the event task details.
     *
     * @param newDescription The new task description.
     * @param newStartTime The new start time.
     * @param newEndTime The new end time.
     */
    public void updateDetails(String newDescription, String newStartTime, String newEndTime) {
        this.description = newDescription.trim();
        this.startTime = LocalDateTime.parse(newStartTime, INPUTFORMATTER);
        this.endTime = LocalDateTime.parse(newEndTime, INPUTFORMATTER);
    }

}
