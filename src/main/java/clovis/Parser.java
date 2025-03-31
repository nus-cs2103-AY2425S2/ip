package clovis;

import clovis.command.AddDeadlineCommand;
import clovis.command.AddEventCommand;
import clovis.command.AddToDoCommand;
import clovis.command.Command;
import clovis.command.DeleteCommand;
import clovis.command.ExitCommand;
import clovis.command.FindCommand;
import clovis.command.ListCommand;
import clovis.command.MarkCommand;
import clovis.command.UnmarkCommand;

/**
 * The {@code Parser} class is responsible for interpreting user input and returning the corresponding commands.
 */
public class Parser {
    /**
     * Enum representing the different types of commands that can be parsed.
     */
    public enum CommandType {
        FIND, TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, BYE, UNKNOWN;

        /**
         * Converts a string into its corresponding {@code CommandType}.
         *
         * @param str the string representation of the command.
         * @return the corresponding {@code CommandType}, or {@code UNKNOWN} if the command is invalid.
         */
        public static CommandType fromString(String str) {
            try {
                return CommandType.valueOf(str.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param input the user input.
     * @return the corresponding parsed command.
     * @throws ClovisException if the input format is invalid or missing required arguments.
     */
    public static Command parse(String input) throws ClovisException {
        assert input != null : "User input should not be null!";
        String[] splitInputAssertion = input.trim().split(" ", 2);
        assert splitInputAssertion.length > 0 : "Command parsing failed, input split length should be at least 1.";
        String[] splitInput = input.trim().split(" ", 2);
        CommandType commandType = CommandType.fromString(splitInput[0]);
        String args = splitInput.length > 1 ? splitInput[1] : "";

        switch (commandType) {
        case FIND:
            if (args.isEmpty()) {
                throw new ClovisException("Please state what you would like to find.");
            }
            return new FindCommand(args);
        case TODO:
            if (args.isEmpty()) {
                throw new ClovisException("A description is required for a todo.");
            }
            return new AddToDoCommand(args);
        case DEADLINE:
            try {
                String[] splitArgsDeadline = args.split("/by ", 2);
                if (splitArgsDeadline[0].isEmpty()) {
                    throw new ClovisException("A description is required for an event.");
                }
                return new AddDeadlineCommand(splitArgsDeadline[0], splitArgsDeadline[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ClovisException("A description and a /by date are required for a deadline.");
            }
        case EVENT:
            try {
                String[] splitArgsEvent = args.split("/from | /to ");
                if (splitArgsEvent[0].isEmpty()) {
                    throw new ClovisException("A description is required for an event.");
                }
                return new AddEventCommand(splitArgsEvent[0], splitArgsEvent[1], splitArgsEvent[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ClovisException("A description, a /from date and, a /to date "
                        + "are required for an event is required for an event.");
            }
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(args);
        case UNMARK:
            return new UnmarkCommand(args);
        case DELETE:
            return new DeleteCommand(args);
        case BYE:
            return new ExitCommand();
        default:
            throw new ClovisException("I have no idea what that means...");
        }
    }
}
