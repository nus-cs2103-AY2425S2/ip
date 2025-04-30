package yuki;

import yuki.command.AddCommand;
import yuki.command.BasicCommand;
import yuki.command.Command;
import yuki.command.DeleteCommand;
import yuki.command.ErrorCommand;
import yuki.command.ExitCommand;
import yuki.command.MarkCommand;
import yuki.command.UndoCommand;
import yuki.command.UnmarkCommand;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        String[] command = input.split(" ");
        return switch (command[0]) {
            case "bye" -> new ExitCommand(command, "Exiting Yuki...", true);
            case "list", "find" -> new BasicCommand(command, "Some basic command...", false);
            case "undo" -> new UndoCommand(command, "Undoing the last command...", false);
            case "mark" -> new MarkCommand(command, "Marking a task as done...", false);
            case "unmark" -> new UnmarkCommand(command, "Unmarking a task as done...", false);
            case "delete" -> new DeleteCommand(command, "Deleting a task...", false);
            case "todo", "deadline", "event" -> new AddCommand(command,
                    "Adding a task...", false);
            default -> new ErrorCommand(command, "Invalid command...", false);
        };
    }
}
