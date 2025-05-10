package bun.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deadline is the main entity we'll be using to handle tasks of type DEADLINE.
 * @author OVOtter
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs a Deadline given the description and due date.
     *
     * @throws DateFormatException If the due date is not in the correct format.
     */
    public Deadline(String description, String date) throws DateFormatException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }

    public String getStoredString() {
        return String.format("D | %s | %s", super.getStoredString(), this.date);
    }
}
