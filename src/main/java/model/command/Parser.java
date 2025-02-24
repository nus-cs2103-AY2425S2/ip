package model.command;

import java.time.LocalDateTime;

import model.exception.AliceException;
import model.exception.CommandFormatException;
import utils.ArrayUtils;
import utils.DateTimeUtils;

/**
 * Parses user input into commands.
 */
public class Parser {

    private static Command parseByeCommand(String[] args) throws AliceException {
        if (args.length != 1) {
            throw new CommandFormatException();
        }
        return new ExitCommand();
    }

    private static Command parseListCommand(String[] args) throws AliceException {
        if (args.length != 1) {
            throw new CommandFormatException();
        }
        return new ListCommand();
    }

    private static Command parseMarkCommand(String[] args) throws AliceException {
        if (args.length != 2) {
            throw new CommandFormatException();
        }
        try {
            int index = Integer.parseInt(args[1]);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new CommandFormatException();
        }
    }

    private static Command parseUnmarkCommand(String[] args) throws AliceException {
        if (args.length != 2) {
            throw new CommandFormatException();
        }
        try {
            int index = Integer.parseInt(args[1]);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new CommandFormatException();
        }
    }

    private static Command parseDeleteCommand(String[] args) throws AliceException {
        if (args.length != 2) {
            throw new CommandFormatException();
        }
        try {
            int index = Integer.parseInt(args[1]);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new CommandFormatException();
        }
    }

    private static Command parseTodoCommand(String[] args) throws AliceException {
        if (args.length < 2) {
            throw new CommandFormatException();
        }
        String todoName = ArrayUtils.joinFromFind(args, " ", "todo");
        return new TodoCommand(todoName);
    }

    private static Command parseDeadlineCommand(String[] args) throws AliceException {
        if (args.length < 4) {
            throw new CommandFormatException();
        }
        String deadlineName = ArrayUtils.joinFromFind(args, " ", "deadline", "/by");
        String byString = ArrayUtils.joinFromFind(args, " ", "/by");
        try {
            LocalDateTime by = DateTimeUtils.parseDateTime(byString);
            return new DeadlineCommand(deadlineName, by);
        } catch (Exception e) {
            throw new CommandFormatException();
        }
    }

    private static Command parseEventCommand(String[] args) throws AliceException {
        if (args.length < 6) {
            throw new CommandFormatException();
        }
        String eventName = ArrayUtils.joinFromFind(args, " ", "event", "/from");
        String fromString = ArrayUtils.joinFromFind(args, " ", "/from", "/to");
        String toString = ArrayUtils.joinFromFind(args, " ", "/to");
        try {
            LocalDateTime from = DateTimeUtils.parseDateTime(fromString);
            LocalDateTime to = DateTimeUtils.parseDateTime(toString);
            return new EventCommand(eventName, from, to);
        } catch (Exception e) {
            throw new CommandFormatException();
        }
    }

    private static Command parseFindCommand(String[] args) throws AliceException {
        if (args.length < 2) {
            throw new CommandFormatException();
        }
        String keyword = ArrayUtils.joinFromIndex(args, " ", 1);
        return new FindCommand(keyword);
    }

    /**
     * Parses the input string and returns the corresponding Command object.
     *
     * @param input The input string.
     * @return The corresponding Command object.
     * @throws AliceException If the input format is incorrect.
     */
    public static Command parseCommand(String input) throws AliceException {
        String[] args = input.split(" ");
        String command = args[0];
        switch (command) {
            case "bye" -> {
                return parseByeCommand(args);
            }
            case "list" -> {
                return parseListCommand(args);
            }
            case "mark" -> {
                return parseMarkCommand(args);
            }
            case "unmark" -> {
                return parseUnmarkCommand(args);
            }
            case "delete" -> {
                return parseDeleteCommand(args);
            }
            case "todo" -> {
                return parseTodoCommand(args);
            }
            case "deadline" -> {
                return parseDeadlineCommand(args);
            }
            case "event" -> {
                return parseEventCommand(args);
            }
            case "find" -> {
                return parseFindCommand(args);
            }
            default ->
                throw new CommandFormatException();
        }
    }

}
