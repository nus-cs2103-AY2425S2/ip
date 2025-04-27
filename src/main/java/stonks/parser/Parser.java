package stonks.parser;

import stonks.command.*;
import stonks.exceptions.StonksException;
import stonks.task.Deadline;
import stonks.task.Event;
import stonks.task.Todo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Makes sense of the user command
 */
public class Parser {
    /**
     * Parses user input into an actionable command
     * @param input user input
     * @return command based on input
     */
    public static Command parse(String input) throws StonksException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
        case "todo":
            String regex = "^\\s*(\\w+)\\s+(.*?)\\s*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return new AddCommand(new Todo(matcher.group(2)));
            } else {
                return new ErrorCommand("     OHNOOOO, a todo should have a description");
            }
        case "deadline":
            regex = "^\\s*(\\w+)\\s+(.*?)\\s+/by\\s+(.+)\\s*$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                try {
                    LocalDate deadline = LocalDate.parse(matcher.group(3));
                    return new AddCommand(new Deadline(matcher.group(2), deadline));
                } catch (Exception e) {
                    return new ErrorCommand("     OHNOOOO, the deadline should be in the format yyyy-mm-dd");
                }
            } else {
                return new ErrorCommand(
                        "     OHNOOOO, a deadline should have a description and deadline");
            }
        case "event":
            regex = "^\\s*(\\w+)\\s+(.*?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)\\s*$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                try {
                    LocalDate start = LocalDate.parse(matcher.group(3));
                    LocalDate end = LocalDate.parse(matcher.group(4));
                    return new AddCommand(new Event(matcher.group(2), start, end));
                } catch (Exception e) {
                    return new ErrorCommand("     OHNOOOO, the start and end should be in the format yyyy-mm-dd");
                }
            } else {
                return new ErrorCommand(
                        "     OHNOOOO, an event should have a description, start and end");
            }
        case "delete":
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "find":
            return new FindCommand(parts[1]);
        default:
            return new ErrorCommand("     Sorry, I don't know what that means.");
        }
    }
}