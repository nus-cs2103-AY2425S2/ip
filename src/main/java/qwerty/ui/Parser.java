package qwerty.ui;

import qwerty.commands.BotCommand;
import qwerty.commands.ByeCommand;
import qwerty.commands.CommandsEnum;
import qwerty.commands.DeadlineCommand;
import qwerty.commands.DeleteCommand;
import qwerty.commands.EventCommand;
import qwerty.commands.FindCommand;
import qwerty.commands.ListCommand;
import qwerty.commands.LoanCommand;
import qwerty.commands.MarkCommand;
import qwerty.commands.TodoCommand;
import qwerty.commands.UnmarkCommand;
import qwerty.exceptions.BotException;
import qwerty.exceptions.InvalidIndexException;
import qwerty.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for parsing user commands and creating appropriate BotCommand objects.
 * It validates and processes the user input to trigger specific actions for tasks.
 */
public class Parser {

    /**
     * Validates the index provided by the user.
     *
     * @param desc The string representation of the index.
     * @return The index as an integer, adjusted for zero-based indexing.
     * @throws BotException If the index is not a valid integer or is out of bounds.
     */
    public static int validateIndex(String desc) throws BotException {
        int index;
        try {
            index = Integer.parseInt(desc) - 1; // Adjusts for zero-based indexing.
        } catch (NumberFormatException n) {
            throw new InvalidIndexException(); // Throws exception if index is invalid.
        }
        return index;
    }

    /**
     * Extracts the command and description from the full command string.
     *
     * @param fullCommand The full command input.
     * @return A String array where index 0 is the command and index 1 is the description.
     */
    public static String[] extractCommandAndDesc(String fullCommand) {
        int spaceIndex = fullCommand.indexOf(' ');
        String command = (spaceIndex != -1) ? fullCommand.substring(0, spaceIndex) : fullCommand;
        String desc = (spaceIndex != -1) ? fullCommand.substring(spaceIndex + 1) : "";
        return new String[]{command, desc};
    }

    /**
     * Creates the appropriate BotCommand based on the command and description.
     *
     * @param command The extracted command.
     * @param desc The extracted description.
     * @return A BotCommand instance corresponding to the given command.
     * @throws BotException If the command is unknown or validation fails.
     */
    public static BotCommand createCommand(String command, String desc) throws BotException {
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(validateIndex(desc));
        case "unmark":
            return new UnmarkCommand(validateIndex(desc));
        case "delete":
            return new DeleteCommand(validateIndex(desc));
        case "todo":
            return new TodoCommand(desc);
        case "deadline":
            return new DeadlineCommand(desc);
        case "event":
            return new EventCommand(desc);
        case "loan":
            return new LoanCommand(desc);
        case "find":
            return new FindCommand(desc);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses a user command and returns the corresponding BotCommand object.
     *
     * @param fullCommand The full user input command.
     * @return A BotCommand object corresponding to the user command.
     * @throws BotException If the command is unknown or incorrectly formatted.
     */
    public static BotCommand parse(String fullCommand) throws BotException {
        String[] commandParts = extractCommandAndDesc(fullCommand);
        String command = commandParts[0];
        String desc = commandParts[1];
        CommandsEnum.contains(command); // Validates the command.
        return createCommand(command, desc);
    }
}
