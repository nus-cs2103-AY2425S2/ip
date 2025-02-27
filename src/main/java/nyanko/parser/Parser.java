package nyanko.parser;

import nyanko.command.*;

public class Parser {
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
        case "SNOOZE":
            command = new SnoozeCommand(argument);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }
}