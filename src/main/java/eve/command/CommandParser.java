package eve.command;

import eve.exception.EveException;
import eve.exception.IncompleteCommandException;
import eve.exception.InvalidCommandException;
import eve.exception.NotIntException;

/**
 * Parses user input into commands that can be executed by the program.
 */
public class CommandParser {
    /**
     * Parses a string from user input into commands that can be executed by the program.
     *
     * @param fullCommand Full string from user input.
     * @return Command to be executed.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public static Command parseString(String fullCommand) throws EveException {
        String[] tokens = fullCommand.split(" ", 2);
        String command = tokens[0];
        switch (command) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "sort":
            return new SortCommand();
        case "clear":
            return new ClearCommand();
        case "close":
            return new CloseWindowCommand();
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "find":
            try {
                String description = tokens[1];
                if (description.isBlank()) {
                    throw new IncompleteCommandException();
                }
                switch (command) {
                case "mark":
                    try {
                        return new MarkCommand(description);
                    } catch (NumberFormatException e) {
                        throw new NotIntException();
                    }
                case "unmark":
                    try {
                        return new UnmarkCommand(description);
                    } catch (NumberFormatException e) {
                        throw new NotIntException();
                    }
                case "todo":
                case "deadline":
                case "event":
                    return new AddCommand(command, description);
                case "delete":
                    try {
                        return new DeleteCommand(description);
                    } catch (NumberFormatException e) {
                        throw new NotIntException();
                    }
                case "find":
                    return new FindCommand(description);
                default:
                    throw new InvalidCommandException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IncompleteCommandException();
            }
        default:
            throw new InvalidCommandException();
        }
    }
}
