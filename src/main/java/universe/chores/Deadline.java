package universe.chores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Inherits from the abstract Chore class and provides implementation of methods specific to the Deadline chore type.
 *
 * @author yuqing-tham
 */
public class Deadline extends Chore {
    private final LocalDateTime deadlineDateTime;

    /**
     * Constructs a new Deadline chore with a specified description and a deadline which includes a date and a time.
     *
     * @param choreDescription String description of the Deadline Chore.
     * @param deadlineDateTime Deadline as LocalDateTime.
     */
    public Deadline(String choreDescription, LocalDateTime deadlineDateTime) {
        super(choreDescription);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Returns the date and time details.
     * Overrides the parent class Chore's abstract method.
     *
     * @return Date and time details of the Deadline chore.
     */
    @Override
    public LocalDateTime getDateTime() {
        return deadlineDateTime;
    }

    /**
     * Returns true as Deadline is a chore type with a date and time.
     */
    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    /**
     * Changes the LocalDateTime to a String.
     *
     * @return A String of the reformatted date and time.
     * @throws DateTimeParseException If there are errors during reformatting.
     */
    public String reformatDate() throws DateTimeParseException {
        return deadlineDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to show additional details specific to Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.reformatDate() + ")";
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to be written into the Checklist file.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.reformatDate();
    }
}
