package boink.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a class of utility methods to be used.
 */

public class Utils {

    /**
     * Converts LocalDateTime to String output in (dd MMM yyyy HH:mm) format
     * for printing out for tasks objects.
     * @param dt DateTime object.
     * @return DateTime (dd MMM yyyy HH:mm).
     */

    public static String getDateTime(LocalDateTime dt) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dt.format(outputFormat);
    }

    /**
     * Converts LocalDateTime to String output in (dd MMM yyyy HH:mm) format
     * for writing to file for task objects.
     * @param dt DateTime object.
     * @return DateTime (dd/MM/yyyy HHmm).
     */

    public static String saveDateTime(LocalDateTime dt) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dt.format(outputFormat);
    }

    /**
     * Creates LocalDatetime object from String input (dd/MM/yyyy HHmm).
     * @param input String datetime (dd/MM/yyyy HHmm).
     * @return LocalDateTime object.
     * @throws DateTimeParseException thrown if input format is incorrect.
     */

    public static LocalDateTime createDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, format);
    }

}
