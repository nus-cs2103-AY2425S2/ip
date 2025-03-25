package buddy.parser;

import java.util.ArrayList;

import buddy.command.AddDeadlineCommand;
import buddy.command.AddEventCommand;
import buddy.command.AddTodoCommand;
import buddy.command.ByeCommand;
import buddy.command.Command;
import buddy.command.CommandType;
import buddy.command.DeleteCommand;
import buddy.command.FindCommand;
import buddy.command.HelpCommand;
import buddy.command.ListTasksCommand;
import buddy.command.MarkCommand;
import buddy.command.UnmarkCommand;
import buddy.command.UpdateCommand;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandException;
import buddy.exception.BuddyMissingCommandInfoException;

/**
 * Represent a parser for the input command
 */
public class Parser {

    /**
     * Returns command type parsed from user input.
     *
     * @param userInput Command given by the user.
     * @return Command type parsed from user input.
     * @throws BuddyInvalidCommandException If the command is invalid.
     */
    public static Command parseCommand(String userInput) throws BuddyException {
        userInput = userInput.trim().replaceAll("\\s+", " ");

        String command = userInput.split(" ")[0];
        String[] commandArgs = userInput.split(" ", 2);
        ArrayList<String> args = new ArrayList<>();
        try {
            switch (CommandType.valueOf(command.toUpperCase())) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListTasksCommand();
            case TODO:
                args.add(commandArgs[1]);
                return new AddTodoCommand(args);
            case DEADLINE:
                String byTime = commandArgs[1].split(" /by ")[1];
                args.add(commandArgs[1].split(" /by ")[0]);
                args.add(byTime);
                return new AddDeadlineCommand(args);
            case EVENT:
                String fromToTime = commandArgs[1].split(" /from ")[1];
                String fromTime = fromToTime.split(" /to ")[0];
                String toTime = fromToTime.split(" /to ")[1];
                args.add(commandArgs[1].split(" /from ")[0]);
                args.add(fromTime);
                args.add(toTime);
                return new AddEventCommand(args);
            case MARK:
                args.add(commandArgs[1]);
                return new MarkCommand(args);
            case UNMARK:
                args.add(commandArgs[1]);
                return new UnmarkCommand(args);
            case DELETE:
                args.add(commandArgs[1]);
                return new DeleteCommand(args);
            case FIND:
                args.add(commandArgs[1]);
                return new FindCommand(args);
            case UPDATE:
                String taskId = commandArgs[1].split(" ", 2)[0];
                String updateInfo = commandArgs[1].split(" ", 2)[1];
                String field = updateInfo.split(" ", 2)[0];
                String newInfo = updateInfo.split(" ", 2)[1];
                args.add(taskId);
                args.add(field);
                args.add(newInfo);
                return new UpdateCommand(args);
            case HELP:
                return new HelpCommand();
            default:
                throw new BuddyInvalidCommandException(userInput);
            }
        } catch (IllegalArgumentException e) {
            throw new BuddyInvalidCommandException(userInput);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BuddyMissingCommandInfoException(command);
        }
    }
}
