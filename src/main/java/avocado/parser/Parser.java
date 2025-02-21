package avocado.parser;

import avocado.command.*;
import avocado.exception.AvocadoException;
import avocado.task.*;

/**
 * Represents a parser to parse the user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The full command entered by the user.
     * @return The corresponding command.
     * @throws AvocadoException If an error occurs during the parsing of the command.
     */
    public static Command parse(String fullCommand) throws AvocadoException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("find ")) {
            return new FindCommand(fullCommand.substring(5).trim());
        } else if (fullCommand.startsWith("todo ")) {
            return parseTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline ")) {
            return parseDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event ")) {
            return parseEventCommand(fullCommand);
        } else if (fullCommand.startsWith("tag ")) {
            String[] parts = fullCommand.split(" ", 3);
            return new AddTagCommand(parseIndex(fullCommand), parts[2]);
        } else if (fullCommand.startsWith("untag ")) {
            String[] parts = fullCommand.split(" ", 3);
            return new RemoveTagCommand(parseIndex(fullCommand), parts[2]);
        } else {
            throw new AvocadoException("Oops! I don't understand this command.");
        }
    }

    /**
     * Parses the index from the full command.
     *
     * @param fullCommand The full command entered by the user.
     * @return The index parsed from the full command.
     */

    private static int parseIndex(String fullCommand) {
        return Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    }

    /**
     * Parses the todo command from the full command.
     * 
     * @param fullCommand The full command entered by the user.
     * @return The todo command parsed from the full command.
     * @throws AvocadoException If an error occurs during the parsing of the todo command.
     */

    private static Command parseTodoCommand(String fullCommand) throws AvocadoException {
        String description = fullCommand.substring(5).trim();
        if (description.isEmpty()) {
            throw new AvocadoException("Oops! The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses the deadline command from the full command.
     * 
     * @param fullCommand The full command entered by the user.
     * @return The deadline command parsed from the full command.
     * @throws AvocadoException If an error occurs during the parsing of the deadline command.
     */

    private static Command parseDeadlineCommand(String fullCommand) throws AvocadoException {
        String[] parts = fullCommand.split(" /by ", 2);
        if (parts.length < 2) {
            throw new AvocadoException("Oops! Deadline format should be: deadline <task> /by yyyy-MM-dd");
        }
        try {
            return new AddCommand(new Deadline(parts[0].substring(9).trim(), parts[1]));
        } catch (Exception e) {
            throw new AvocadoException("Oops! Date format should be: yyyy-MM-dd");
        }
    }

    /**
     * Parses the event command from the full command.
     * 
     * @param fullCommand The full command entered by the user.
     * @return The event command parsed from the full command.
     * @throws AvocadoException If an error occurs during the parsing of the event command.
     */

    private static Command parseEventCommand(String fullCommand) throws AvocadoException {
        String[] parts = fullCommand.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new AvocadoException("Oops! Event format should be: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        try {
            return new AddCommand(new Event(parts[0].substring(6).trim(), parts[1], parts[2]));
        } catch (Exception e) {
            throw new AvocadoException("Oops! Date format should be: yyyy-MM-dd HHmm");
        }
    }
}
