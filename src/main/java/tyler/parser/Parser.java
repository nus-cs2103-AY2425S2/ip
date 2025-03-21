package tyler.parser;

import tyler.command.Command;
import tyler.command.DateCommand;
import tyler.command.DeadlineCommand;
import tyler.command.DeleteCommand;
import tyler.command.EndCommand;
import tyler.command.EventCommand;
import tyler.command.FindCommand;
import tyler.command.ListCommand;
import tyler.command.MarkCommand;
import tyler.command.ToDoCommand;
import tyler.command.UnknownCommand;
import tyler.command.UnmarkCommand;

public class Parser {

    public static Command parse(String input) {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];
        return switch (command.toLowerCase()) {
            case "list" -> new ListCommand();
            case "bye" -> new EndCommand();
            case "date" -> new DateCommand(tokens);
            case "mark" -> new MarkCommand(tokens);
            case "unmark" -> new UnmarkCommand(tokens);
            case "find" -> new FindCommand(tokens);
            case "delete" -> new DeleteCommand(tokens);
            case "todo" -> new ToDoCommand(tokens);
            case "deadline" -> new DeadlineCommand(tokens);
            case "event" -> new EventCommand(tokens);
            default -> new UnknownCommand();
        };
    }
}
