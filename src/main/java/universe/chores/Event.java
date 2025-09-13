package universe.chores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Inherits from the abstract Chore class and provides implementation of methods specific to the Event chore type.
 *
 * @author yuqing-tham
 */
public class Event extends Chore {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Event chore with a specified description, a start date/time and end date/time.
     *
     * @param choreDescription String description of the Event chore.
     * @param startDateTime Starting date/time of the Event.
     * @param endDateTime Ending date/time of the Event.
     */
    public Event(String choreDescription, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(choreDescription);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        if (startDateTime.isAfter(endDateTime) || startDateTime.equals(endDateTime)) {
            throw new IllegalArgumentException("Start date/time must be before end date/time.");
        }
    }

    /**
     * Returns the date and time details.
     * Overrides the parent class Chore's abstract method.
     *
     * @return Start date and time details of the Event chore.
     */
    @Override
    public LocalDateTime getDateTime() {
        return startDateTime;
    }

    /**
     * Returns true as Event is a chore type with a date and time.
     */
    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    /**
     * Changes the start LocalDateTime to a String.
     *
     * @return A String of the reformatted date and time.
     * @throws DateTimeParseException If there are errors during reformatting.
     */
    public String reformatStartDateTime() throws DateTimeParseException {
        return startDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Changes the end LocalDateTime to a String.
     *
     * @return A String of the reformatted date and time.
     * @throws DateTimeParseException If there are errors during reformatting.
     */
    public String reformatEndDateTime() throws DateTimeParseException {
        return endDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to show additional details specific to Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.reformatStartDateTime()
                + " to: " + this.reformatEndDateTime() + ")";
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to be written into the Checklist file.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.reformatStartDateTime()
                + " to " + this.reformatEndDateTime();
    }
}
