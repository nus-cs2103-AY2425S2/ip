package laffy.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the API for date time operations.
 */
public class TaskDateProvider {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(
            "dd-MM-yy HHmm");

    // outputFormats
    private static final DateTimeFormatter outputNoYearFormatter = DateTimeFormatter.ofPattern(
            "EEEE, dd/MM, hh:mma");
    private static final DateTimeFormatter outputWithYearFormatter = DateTimeFormatter.ofPattern(
            "EEEE, dd/MM/yyyy, hh:mma");

    /**
     * Parses a date time string into a LocalDateTime object.
     * If the time is not provided, it is set to 0000.
     * Format of the date time string is "dd-MM-yy HHmm".
     *
     * @param dateTime The date time string to be parsed.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        if (dateTime.split(" ").length != 2) {
            dateTime += " 0000";
        }
        return LocalDateTime.parse(dateTime, inputFormatter);
    }

    /**
     * Checks if a date time string is valid.
     * Format of the date time string is "dd-MM-yy HHmm".
     *
     * @param dateTime The date time string to be checked.
     * @return True if the date time string is valid, false otherwise.
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            parseDateTime(dateTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats a LocalDateTime object into a string.
     * Format used is "EEEE, dd/MM/yyyy, hh:mma".
     * If the year of the date time is the same as the current year,
     * the year is omitted.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime.getYear() != LocalDateTime.now().getYear()) {
            return dateTime.format(outputWithYearFormatter);
        }
        return dateTime.format(outputNoYearFormatter);
    }

    /**
     * Formats a LocalDateTime object into a string for storage.
     * Format used is ISO_LOCAL_DATE_TIME.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted string.
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Parses a date time string from storage into a LocalDateTime object.
     * Format used is ISO_LOCAL_DATE_TIME.
     *
     * @param dateTime The date time string to be parsed.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseFromStorage(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
