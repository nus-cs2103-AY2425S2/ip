package eve.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Utility class to handle date and time.
 */
public class DateTimeUtil {
    /**
     * Parses a string into a LocalDateTime object.
     * Supports multiple format for date and time.
     *
     * @param dateTimeString String to be parsed.
     * @return LocalDateTime object.
     * @throws DateTimeParseException If the input string cannot be parsed.
     */
    public static LocalDateTime parseString(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME) // "2007-12-03T10:15:30"
                .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE) // "2007-12-03"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) // "2007/12/03 10:15:30"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) // "2007/12/03 10:15"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) // "2007/12/03 1015"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd")) // "2007/12/03"
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) // "03/12/2007 10:15:30"
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) // "03/12/2007 10:15"
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) // "03/12/2007 1015"
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy")) // "03/12/2007"

                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) // "2007-12-03 10:15:30"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) // "2007-12-03 10:15"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) // "2007-12-03 1015"
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd")) // "2007-12-03"
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) // "03-12-2007 10:15:30"
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) // "03-12-2007 10:15"
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) // "03-12-2007 1015"
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy")) // "03-12-2007"
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 23) // Default if no time provided
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .toFormatter();
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
