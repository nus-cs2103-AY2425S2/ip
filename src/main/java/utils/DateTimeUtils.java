package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    //This specifies the DateTime format for all DateTime objects in the program
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses a string representation of a date and time into a LocalDateTime
     * object.
     *
     * @param dateTime the string representation of the date and time
     * @return the parsed LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    /**
     * Formats a LocalDateTime object into a string representation.
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string representation of the date and time
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMATTER);
    }

    /**
     * Formats raw date and time inputs into a string representation according
     * to DATETIME_FORMATTER.
     *
     * @param date the date part of the input
     * @param hour the hour part of the input
     * @param minute the minute part of the input
     * @return the formatted string representation of the date and time
     */
    public static String formatRawDateTime(String date, String hour, String minute) {
        LocalDateTime dateTime = LocalDateTime.parse(date + " " + hour + ":" + minute, DATETIME_FORMATTER);
        return dateTime.format(DATETIME_FORMATTER);
    }
}
