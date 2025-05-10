package kyrie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date and time.
 */
public class DateTime {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mma, MMM dd yyyy");
    private LocalDateTime dateTime;

    /**
     * Constructor for DateTime.
     * 
     * @param dateTime The date and time in the format dd/MM/yyyy HHmm.
     * @throws KyrieException If the date and time is in an invalid format.
     */
    public DateTime(String dateTime) throws KyrieException {
        try {
            assert dateTime != null : "Date/time cannot be null";
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new KyrieException("Invalid date/time format. Please use the format dd/MM/yyyy HHmm");
        }
    }

    /**
     * Returns the date and time in the format dd/MM/yyyy HHmm.
     * 
     * @return The date and time in the format dd/MM/yyyy HHmm.
     */
    public String toInputFormatString() {
        return this.dateTime.format(formatter);
    }

    /**
     * Returns the date and time in the format hh:mma, MMM dd yyyy.
     * 
     * @return The date and time in the format hh:mma, MMM dd yyyy.
     */
    public String toString() {
        return this.dateTime.format(outputFormatter);
    }
}