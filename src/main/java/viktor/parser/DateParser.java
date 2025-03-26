package viktor.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing and formatting dates and times, including relative dates.
 */
public class DateParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter ALTERNATIVE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    private static final Pattern RELATIVE_DATE_PATTERN = Pattern.compile("(today|tomorrow|next \\w+)\\s+(\\d{4})?",
            Pattern.CASE_INSENSITIVE);

    /**
     * Parses a date and time string into a LocalDateTime object, supporting both absolute and relative dates.
     *
     * @param dateTimeStr the date and time string to parse
     * @return the parsed LocalDateTime object
     * @throws DateTimeParseException if the date and time string cannot be parsed
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        Matcher matcher = RELATIVE_DATE_PATTERN.matcher(dateTimeStr.toLowerCase());

        if (matcher.matches()) {
            String relativeDate = matcher.group(1);
            String timePart = matcher.group(2); // Can be null

            LocalDate date = parseRelativeDate(relativeDate);
            int hour = 0;
            int minute = 0;

            if (timePart != null) {
                hour = Integer.parseInt(timePart.substring(0, 2));
                minute = Integer.parseInt(timePart.substring(2));
            }

            return LocalDateTime.of(date, java.time.LocalTime.of(hour, minute));
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
            assert dateTime != null : "DateTime is null for parseDateTime";
            return dateTime;
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateTimeStr, ALTERNATIVE_FORMATTER);
        }
    }

    /**
     * Parses a relative date string into a LocalDate.
     *
     * @param relativeDate the relative date string (e.g., "tomorrow", "next Monday")
     * @return the computed LocalDate
     */
    private static LocalDate parseRelativeDate(String relativeDate) {
        LocalDate today = LocalDate.now();

        if (relativeDate.equals("today")) {
            return today;
        }
        if (relativeDate.equals("tomorrow")) {
            return today.plusDays(1);
        }
        if (relativeDate.startsWith("next")) {
            String dayOfWeekStr = relativeDate.substring(5).toLowerCase();
            DayOfWeek targetDay = getDayOfWeek(dayOfWeekStr);

            if (targetDay != null) {
                int daysUntilNext = (targetDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
                if (daysUntilNext == 0) {
                    daysUntilNext = 7; // Ensure "next Monday" means the next occurrence
                }
                return today.plusDays(daysUntilNext);
            }
        }

        throw new DateTimeParseException("Invalid relative date: " + relativeDate, relativeDate, 0);
    }

    /**
     * Converts a day of the week string (e.g., "monday") to a DayOfWeek enum.
     *
     * @param day the lowercase day of the week
     * @return the corresponding DayOfWeek or null if invalid
     */
    private static DayOfWeek getDayOfWeek(String day) {
        try {
            return DayOfWeek.valueOf(day.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Formats a LocalDateTime object into a string with the format MMM d yyyy, HH:mm.
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "DateTime is null for formatDateTime";
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateInput the date string to parse
     * @return the parsed LocalDate object
     * @throws DateTimeParseException if the date string cannot be parsed
     */
    public static LocalDate parseDateOnly(String dateInput) throws DateTimeParseException {
        Matcher matcher = RELATIVE_DATE_PATTERN.matcher(dateInput.toLowerCase());

        if (matcher.matches()) {
            String relativeDate = matcher.group(1);
            if (relativeDate != null) {
                return parseRelativeDate(relativeDate);
            }
        }

        return LocalDate.parse(dateInput, INPUT_DATE_FORMATTER);
    }


    /**
     * Formats a LocalDate object into a string with the format MMM d yyyy.
     *
     * @param date the LocalDate object to format
     * @return the formatted string
     */
    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_DATE_FORMATTER);
    }
}
