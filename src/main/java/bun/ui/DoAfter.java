package bun.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * DoAfter is the main entity we'll be using to handle tasks of type DOAFTER.
 * @author OVOtter
 */
public class DoAfter extends Task {
    protected LocalDate date;

    /**
     * Constructs a DoAfter given the description and start date.
     *
     * @throws DateFormatException If the start date is in wrong format.
     */
    public DoAfter(String description, String date) throws DateFormatException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    @Override
    public String toString() {
        return String.format("[S]%s (by: %s)", super.toString(), this.date);
    }

    public String getStoredString() {
        return String.format("S | %s | %s", super.getStoredString(), this.date);
    }
}
