package miki.parse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import miki.exception.MikiException;

/**
 * Class for static method to parse any format of Date and Time
 */
public class DateParser {
    /**
     * List of formats that the date can be in
     */
    private static List<String> formats = List.of(
            "yyyy-MM-dd HH:mm",
            "MM-dd-yyyy HH:mm",
            "dd-MM-yyyy HH:mm",
            "HH:mm yyyy-MM-dd",
            "HH:mm MM-dd-yyyy",
            "HH:mm dd-MM-yyyy");
    private static List<DateTimeFormatter> listOfFormats = formats.stream()
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());

    /**
     * Parses the date from the input string, returning it in the LocalDateTime
     * format.
     *
     * @param date as a String
     *
     * @return date as a LocalDateTime
     *
     * @throws MikiException
     */
    public static LocalDateTime parseDate(String date) throws MikiException {
        for (DateTimeFormatter formatter : listOfFormats) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (Exception e) {
                // do nothing if it gets to here
            }
        }
        // throw error if no date can be parsed.
        throw new MikiException("The date format is invalid.");
    }
}
