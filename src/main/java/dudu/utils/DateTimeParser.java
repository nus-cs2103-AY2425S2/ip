package dudu.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    /**
     * Returns parsed date and time from command arguments.
     * If only date is given, the parsed time will be midnight.
     *
     * @param input Date and time.
     * @return Parsed date and time in LocalDateTime.
     * @throws DuduException If date and time given is in the incorrect format.
     */
    public static LocalDateTime parseDateTime(String input) throws DuduException {
        assert input != null;
        DateTimeFormatter formatter;
        LocalDateTime dateTime;

        try {
            if (input.contains(" ")) {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                dateTime = LocalDateTime.parse(input, formatter);
            } else {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate date = LocalDate.parse(input, formatter);
                dateTime = date.atStartOfDay();
            }
        } catch (DateTimeException e) {
            throw new DuduException("Invalid date format. Please use d/M/yyyy or d/M/yyyy HHmm.");
        }
        return dateTime;
    }

    /**
     * Returns parsed date and time from save file.
     *
     * @param input Date and time.
     * @return Parsed date and time in LocalDateTime.
     */
    public static LocalDateTime parseFromFile(String input) {
        return LocalDateTime.parse(input);
    }

    /**
     * Returns DateTimeFormat according to input given.
     * If only time given is midnight, only the date will be printed.
     *
     * @param dateTime Date and time in LocalDateTime.
     * @return DateTimeFormat to be printed.
     */
    public static DateTimeFormatter checkFormat(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            return DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        }
    }

}
