package jimmy;

import java.util.HashMap;
import java.util.Map;

import jimmy.commands.AddDeadlineCommand;
import jimmy.commands.AddEventCommand;
import jimmy.commands.AddTodoCommand;
import jimmy.commands.Command;
import jimmy.commands.DeleteCommand;
import jimmy.commands.ExitCommand;
import jimmy.commands.FindCommand;
import jimmy.commands.ListCommand;
import jimmy.commands.MarkCommand;
import jimmy.commands.UnmarkCommand;

/**
 * The {@code Parser} class handles the interpretation of user input.
 * It converts raw input strings into corresponding {@code Command} objects
 * that can be executed by the program.
 */
public class Parser {

    private static final String ERROR_INVALID_COMMAND = "Invalid command. "
        + "Please enter a valid command like 'list', 'todo', 'find', etc.";
    private static final Map<String, String> COMMAND_ALIASES = new HashMap<>();
    static {
        COMMAND_ALIASES.put("t", "todo");
        COMMAND_ALIASES.put("d", "deadline");
        COMMAND_ALIASES.put("e", "event");
        COMMAND_ALIASES.put("m", "mark");
        COMMAND_ALIASES.put("u", "unmark");
        COMMAND_ALIASES.put("del", "delete");
        COMMAND_ALIASES.put("f", "find");
        COMMAND_ALIASES.put("li", "list");
    }

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     * @param input the user's input string.
     * @return a {@code Command} representing the user's request.
     * @throws JimmyException if the command is invalid or the input is incomplete.
     */
    public static Command parse(String input) throws JimmyException {
        assert input != null : "Input command should not be null";
        input = input.trim();
        assert !input.isEmpty() : "Command should not be empty";

        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        if (COMMAND_ALIASES.containsKey(commandWord)) {
            commandWord = COMMAND_ALIASES.get(commandWord);
        }

        switch (commandWord) {
        case "bye":
            return new ExitCommand(arguments);
        case "list":
            return new ListCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new AddTodoCommand(arguments);
        case "deadline":
            return new AddDeadlineCommand(arguments);
        case "event":
            return new AddEventCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new JimmyException(ERROR_INVALID_COMMAND);
        }
    }
}
