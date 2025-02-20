package core;


import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TagCommand;
import command.UnmarkCommand;
import exception.BaimiException;
import exception.EmptyDescriptionException;
import exception.InvalidFormatException;
import exception.UnknownCommandException;

/**
 * Parses user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into a command.
     *
     * @param command The user input.
     * @return The command corresponding to the user input.
     * @throws BaimiException If the user input is invalid.
     */
    public static Command parse(String command) throws BaimiException {
        assert command != null : "Command cannot be null";
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("todo ")) {
            if (command.length() <= 5) {
                throw new EmptyDescriptionException("todo");
            }
            return new AddTodoCommand(command.substring(5));
        } else if (command.startsWith("deadline ")) {
            String[] parts = command.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new InvalidFormatException("deadline [description] /by YYYY-MM-DD HHMM");
            }
            return new AddDeadlineCommand(parts[0], parts[1]);
        } else if (command.startsWith("event ")) {
            String[] parts = command.substring(6).split(" /from ");
            if (parts.length < 2) {
                throw new InvalidFormatException("event [description] /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM");
            }
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new InvalidFormatException("event [description] /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM");
            }
            return new AddEventCommand(parts[0], timeParts[0], timeParts[1]);
        } else if (command.startsWith("mark ")) {
            int index = Integer.parseInt(command.substring(5)) - 1;  // 1-based to 0-based index
            return new MarkCommand(index);
        } else if (command.startsWith("unmark ")) {
            int index = Integer.parseInt(command.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (command.startsWith("delete ")) {
            int index = Integer.parseInt(command.substring(7)) - 1;
            return new DeleteCommand(index);
        }  else if (command.startsWith("find ")) {
            return new FindCommand(command.substring(5));
        }  else if (command.startsWith("tag ")) {
            String[] parts = command.substring(4).split(" ");
            if (parts.length < 2) {
                throw new InvalidFormatException("tag [index] [tag]");
            }
            int index = Integer.parseInt(parts[0]) - 1;
            return new TagCommand(index, parts[1], true);
        } else if (command.startsWith("untag ")) {
            String[] parts = command.substring(6).split(" ");
            if (parts.length < 2) {
                throw new InvalidFormatException("untag [index] [tag]");
            }
            int index = Integer.parseInt(parts[0]) - 1;
            return new TagCommand(index, parts[1], false);
        } else {
            throw new UnknownCommandException();
        }
    }
}