package clank.utility;

import clank.command.AddCommand;
import clank.command.ByeCommand;
import clank.command.Command;
import clank.command.DeleteCommand;
import clank.command.FindCommand;
import clank.command.ListCommand;
import clank.command.MarkCommand;
import clank.command.ReminderCommand;
import clank.command.SaveCommand;
import clank.command.UnmarkCommand;
import clank.exception.ClankException;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the given input string and returns the appropriate command object.
     *
     * @param input The user input string.
     * @return The corresponding {@code Command} object.
     * @throws ClankException If the command is not recognized.
     */
    public static Command parse(String input) throws ClankException {
        String[] parts = input.split(" ", 2);
        String mainCommand = parts[0].toLowerCase();

        switch (mainCommand) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "save":
            return new SaveCommand();
        case "find":
            return new FindCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "reminder":
            return new ReminderCommand(input, false);
        case "todo", "deadline", "event":
            return new AddCommand(input);
        default:
            throw new ClankException(ClankException.ErrorType.UNKNOWN_COMMAND, "");
        }
    }
}
