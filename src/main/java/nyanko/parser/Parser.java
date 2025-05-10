package nyanko.parser;

import nyanko.command.ByeCommand;
import nyanko.command.Command;
import nyanko.command.DeadlineCommand;
import nyanko.command.DeleteCommand;
import nyanko.command.EventCommand;
import nyanko.command.FindCommand;
import nyanko.command.InvalidCommand;
import nyanko.command.ListCommand;
import nyanko.command.MarkCommand;
import nyanko.command.SnoozeDeadlineCommand;
import nyanko.command.TodoCommand;
import nyanko.command.UnmarkCommand;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding Command.
     *
     * @param fullCommand The full input string entered by the user.
     * @return The corresponding {@link Command} object.
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        Command command;
        switch (commandWord.toUpperCase()) {
        case "BYE":
            command = new ByeCommand();
            break;
        case "LIST":
            command = new ListCommand();
            break;
        case "MARK":
            command = new MarkCommand(argument);
            break;
        case "UNMARK":
            command = new UnmarkCommand(argument);
            break;
        case "DELETE":
            command = new DeleteCommand(argument);
            break;
        case "DEADLINE":
            command = new DeadlineCommand(argument);
            break;
        case "TODO":
            command = new TodoCommand(argument);
            break;
        case "EVENT":
            command = new EventCommand(argument);
            break;
        case "FIND":
            command = new FindCommand(argument);
            break;
        case "SNOOZEDEADLINE":
            command = new SnoozeDeadlineCommand(argument);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }
}
