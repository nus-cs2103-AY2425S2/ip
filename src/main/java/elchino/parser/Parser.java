package elchino.parser;

import elchino.exceptions.*;
import elchino.commands.*;

/**
 * Parser to parse user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     * @throws ElchinoException if an error occurs during parsing.
     */
    public static Command parse(String input) throws ElchinoException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        assert !requiresArgument(command) || parts.length > 1 : "Command requires an argument.";

        return switch (command) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(parts[1]);
            case "unmark" -> new UnmarkCommand(parts[1]);
            case "todo" -> new AddTodoCommand(parts[1]);
            case "deadline" -> new AddDeadlineCommand(parts[1]);
            case "event" -> new AddEventCommand(parts[1]);
            case "delete" -> new DeleteCommand(parts[1]);
            case "bye" -> new ExitCommand();
            case "find" -> new FindCommand(parts[1]);
            case "help" -> new HelpCommand();
            default -> new InvalidCommand(command);
        };
    }
    /**
     * Checks if the command requires an argument.
     *
     * @param command The command to check.
     * @return true if the command requires an argument, false otherwise.
     */
    private static boolean requiresArgument(String command) {
        return switch (command) {
            case "mark", "unmark", "todo", "deadline", "event", "delete", "find" -> true;
            default -> false;
        };
    }
}