package nguyen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/**
 * Parses date string input into local date
 */
public class DateParser {
    // List of supported date patterns in various formats
    private static final List<String> DATE_PATTERNS = Arrays.asList(
            "yyyy-MM-dd HHmm",
            "yyyy/MM/dd HHmm",
            "dd/MM/yyyy HHmm",
            "MM-dd-yyyy HHmm",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd/MM/yyyy",
            "MM-dd-yyyy",
            "dd MMM yyyy",
            "d MMM yyyy",
            "MMM d yyyy",
            "MMM dd yyyy",
            "MMM dd, yyyy"
    );
    /**
     * Parses a date string into a LocalDate object.
     * Tries to match the input string with multiple date patterns and returns the corresponding LocalDate.
     * If no valid date format is found, returns null.
     *
     * @param input The date string to be parsed.
     * @return A LocalDate object if a valid format is found, otherwise null.
     */
    public static LocalDate parseDate(String input) throws NguyenException {
        input = input.trim();
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                if (pattern.contains("HHmm")) {
                    LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
                    return dateTime.toLocalDate();
                }
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // If there's any error in DateTimeParse:
                // it's what I expected
                // just continue to handle next possible time format of input
            }
        }
        throw new NguyenException("Invalid Date, expected format: dd/MM/yyyy HHmm");
    }
}
