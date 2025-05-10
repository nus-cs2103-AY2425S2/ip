package partillay.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import partillay.exception.PartillayDateFormatException;
import partillay.exception.PartillayException;

/**
 * Represents a parser for strings into {@code LocalDateTime} objects.
 * <p>
 *     Supports multiple date formats, including those with and without time components.
 *     If the input lacks a time component, it defaults to 23:59 (for {@code Deadline} tasks).
 * </p>
 */
public class DateTimeFormatParser {

    /**
     * Parses a string into a {@code LocalDateTime} object,
     *
     * @param dateStr the date string to be parsed
     * @return the {@code LocalDateTime} object interpreted from the string
     * @throws PartillayDateFormatException if the passed string does not follow list of supported formats
     */
    public static LocalDateTime parseDateTime(String dateStr) throws PartillayException {
        for (DateFormat format : DateFormat.values()) {
            try {
                return format.parse(dateStr);
            } catch (DateTimeParseException ignored) {
                // Ignore and try next format
            }
        }
        throw new PartillayDateFormatException("Invalid date time format, bestie!");
    }

    /**
     * Returns string format of a {@code LocalDateTime} object.
     *
     * @param dateTime the {@code LocalDateTime} object to be transformed to string upon returning it
     * @return string format of the LocalDateTime instance passed through
     */
    public static String getFormattedDateString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return dateTime.format(formatter);
    }

    public static boolean isEarlierThan(String dateStr1, String dateStr2) {
        return parseDateTime(dateStr2).isAfter(parseDateTime(dateStr1));
    }

    /**
     * Enum representing supported date and tie formats for parsing.
     */
    public enum DateFormat {
        YYYY_MM_DD_HH_MM_1("yyyy-MM-dd HH:mm"),
        YYYY_MM_DD_HH_MM_2("yyyy/MM/dd HH:mm"),
        YYYY_MM_DD_HH_MM_3("yyyy-MM-dd HHmm"),
        YYYY_MM_DD_HH_MM_4("yyyy/MM/dd HHmm"),

        YYYY_MM_DD_1("yyyy-MM-dd"),
        YYYY_MM_DD_2("yyyy/MM/dd"),

        D_MM_YYYY("d/MM/yyyy"),
        DD_MM_YYYY("dd/MM/yyyy"),

        DD_MM_YYYY_HH_MM_1("dd-MM-yyyy HH:mm"),
        DD_MM_YYYY_HH_MM_2("dd/MM/yyyy HH:mm"),
        DD_MM_YYYY_HH_MM_3("dd-MM-yyyy HHmm"),
        DD_MM_YYYY_HH_MM_4("dd/MM/yyyy HHmm"),

        YYYY_MM_DD_T_HH_MM("yyyy-MM-dd'T'HH:mm");

        private final DateTimeFormatter formatter;

        DateFormat(String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        /**
         * Returns the {@code LocalDateTime} object from a representative String
         *
         * @param dateStr the date (and time) string to be parsed
         * @return the corresponding {@code LocalDateTime} object
         */
        public LocalDateTime parse(String dateStr) {
            return this.name().contains("HH_MM")
                    ? LocalDateTime.parse(dateStr, formatter)
                    : LocalDate.parse(dateStr, formatter).atTime(23, 59);
        }
    }
}
