package adam.parser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import adam.command.AddCommand;
import adam.command.ByeCommand;
import adam.command.Command;
import adam.command.DeleteCommand;
import adam.command.DoneCommand;
import adam.command.FindCommand;
import adam.command.ListCommand;
import adam.command.ListOnCommand;
import adam.command.UnmarkCommand;
import adam.exceptions.AdamException;
import adam.exceptions.InvalidDate;
import adam.exceptions.InvalidDuration;

/**
 * Static methods to parse user input and create commands.
 */
public class Parser {
    /** String DateTime format for date to be read */
    public static final String DATE_FORMAT_STRING = "dd-MM-yyyy";

    /** DateTime Format for dates to be read and output */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Parses the input string into a LocalDate object using the defined date format.
     *
     * @param date The input string representing the date, expected in the format "dd-MM-yyyy".
     * @return A LocalDate object parsed from the input date string.
     * @throws AdamException If the input string does not match the expected date format.
     */
    public static LocalDate parseInputDate(String date) throws AdamException {
        try {
            return LocalDate.parse(date, Parser.DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDate();
        }
    }

    /**
     * Converts a LocalDate to a string in the format dd MMM yyyy.
     *
     * @param date The date to convert.
     * @return The date as a string in the format dd MMM yyyy.
     */
    public static String formatOutputDate(LocalDate date) {
        return date.format(Parser.OUTPUT_DATE_FORMAT);
    }

    /**
     * Converts a LocalDate to a string in the format dd-MM-yyyy.
     *
     * @param date The date to convert.
     * @return The date as a string in the format dd-MM-yyyy.
     */
    public static String formatLogDate(LocalDate date) {
        return date.format(Parser.DATE_FORMAT);
    }

    /**
     * Parses the input string into a Duration object.
     *
     * @param input The input string representing the duration in minutes.
     * @return A Duration object parsed from the input duration string.
     * @throws AdamException If the input string is not valid.
     */
    public static Duration parseDuration(String input) throws AdamException {
        List<String> parts = List.of(input.split(" "));
        Duration result = Duration.ZERO;
        try {
            for (String part:parts) {
                if (part.endsWith("h")) {
                    result = result.plusHours(Long.parseLong(part.substring(0, part.length() - 1)));
                } else if (part.endsWith("m")) {
                    result = result.plusMinutes(Long.parseLong(part.substring(0, part.length() - 1)));
                } else {
                    throw new InvalidDuration();
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidDuration();
        }
        return result;
    }

    /**
     * Converts a Duration to a string in the format "xh ym" (x hours, y minutes).
     *
     * @param duration The duration to convert.
     * @return The duration as a string in the format "xh ym".
     */
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long mins = duration.toMinutes() % 60;
        if (mins == 0) {
            return String.format("%dh", hours);
        } else if (hours == 0) {
            return String.format("%dm", mins);
        } else {
            return String.format("%dh %dm", hours, mins);
        }
    }

    /**
     * Parses the input and returns the corresponding command.
     *
     * @param input The input to parse.
     * @return The corresponding command.
     * @throws AdamException If the input is invalid.
     */
    public static Command parseInput(String input) throws AdamException {
        if (ByeCommand.isMatch(input)) {
            return new ByeCommand();
        } else if (ListCommand.isMatch(input)) {
            return new ListCommand();
        } else if (ListOnCommand.isMatch(input)) {
            return new ListOnCommand(input);
        } else if (DoneCommand.isMatch(input)) {
            return new DoneCommand(input);
        } else if (UnmarkCommand.isMatch(input)) {
            return new UnmarkCommand(input);
        } else if (DeleteCommand.isMatch(input)) {
            return new DeleteCommand(input);
        } else if (FindCommand.isMatch(input)) {
            return new FindCommand(input);
        } else {
            // throws AdamException if invalid input
            return new AddCommand(input);
        }
    }
}
