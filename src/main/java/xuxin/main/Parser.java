package xuxin.main;

import xuxin.command.*;
import xuxin.exception.DukeException;

/**
 * Parser class to process and handle the input strings.
 */
public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String commandWord = fullCommand.split(" ")[0];

        switch (commandWord) {
        case "todo":
            return new AddToDoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "mark":
            return new MarkTaskCommand(fullCommand);
        case "unmark":
            return new UnmarkTaskCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "list":
            return new ListTaskCommand();
        case "bye":
            return new ExitCommand();
        case "stats":
            return new StatsCommand();
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("Unknown duke.command.");
        }
    }
}
