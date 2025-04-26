package parser;

import exception.SunpterException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Class for parsing and formatting dates used in tasks
 */
public class DateParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^(\\d{1,2})/(\\d{1,2})/(\\d{4})\\s(\\d{4})$"
    );


    /**
     * Validates the date string format before parsing
     * @param dateStr the date string to validate
     * @throws SunpterException if the format is invalid
     */
    private static void validateDateFormat(String dateStr) throws SunpterException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new SunpterException("Date string cannot be null or empty");
        }

        if (!DATE_PATTERN.matcher(dateStr).matches()) {
            throw new SunpterException(
                    "Invalid date format. Expected format: DD/MM/YYYY HHMM (e.g., 25/3/2024 1430)"
            );
        }
    }

    /**
     * Parses a date string in the format "d/M/yyyy HHmm"
     * @throws SunpterException if the date format is invalid
     */
    public static LocalDateTime parseDate(String dateStr) throws SunpterException {
        validateDateFormat(dateStr);
        return LocalDateTime.parse(dateStr, INPUT_FORMATTER);
    }

    /**
     * Formats a LocalDateTime object to store
     */
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Formats the storage string representation
     * @return a String value of INPUT_FORMATTER
     */
    public static String parseStorage(LocalDateTime dateTime) {
        return dateTime.format(INPUT_FORMATTER);
    }
}