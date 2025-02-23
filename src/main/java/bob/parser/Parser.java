package bob.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TodoCommand;
import bob.command.UnmarkCommand;
import bob.command.WrongCommandException;
import bob.util.Helper;

/**
 * This class contains a singular {@code parse()} method to parse and validate user input,
 * throw exceptions if necessary, and return the respective commands.
 */
public class Parser {

    private static final Map<String, ParseFunction> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put("bye", Parser::parseBye);
        COMMAND_MAP.put("list", Parser::parseList);
        COMMAND_MAP.put("mark", Parser::parseMark);
        COMMAND_MAP.put("unmark", Parser::parseUnmark);
        COMMAND_MAP.put("find", Parser::parseFind);
        COMMAND_MAP.put("todo", Parser::parseTodo);
        COMMAND_MAP.put("deadline", Parser::parseDeadline);
        COMMAND_MAP.put("event", Parser::parseEvent);
        COMMAND_MAP.put("delete", Parser::parseDelete);
    }

    private static Command parseBye(String userInput) {
        return new ExitCommand();
    }

    private static Command parseList(String userInput) {
        return new ListCommand();
    }

    private static Command parseMark(String userInput) throws NumberFormatException {
        int number = Integer.parseInt(userInput.split(" ")[1]);
        return new MarkCommand(number);
    }

    private static Command parseUnmark(String userInput) throws NumberFormatException {
        int number = Integer.parseInt(userInput.split(" ")[1]);
        return new UnmarkCommand(number);
    }

    private static Command parseFind(String userInput) throws WrongCommandException {
        String[] parts = userInput.split(" ");

        // if user gives empty search term
        if (parts.length == 1) {
            throw new WrongCommandException("Uh oh! Bob says...the search term cannot be empty.");
        }

        // if user gives more than 1 word as search term
        if (parts.length > 2) {
            throw new WrongCommandException("Uh oh! Bob says...the search term must only be 1 word.");
        }

        String searchTerm = parts[1];
        return new FindCommand(searchTerm);
    }

    private static Command parseTodo(String userInput) throws WrongCommandException {
        String[] parts = userInput.split(" ", 2);

        // if user gives empty description
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new WrongCommandException("Uh oh! Bob says...the description of a todo cannot be empty.");
        }

        String description = parts[1];
        return new TodoCommand(description);
    }

    private static Command parseDeadline(String userInput) throws WrongCommandException {
        String[] command = userInput.split(" ", 2);

        // if user gives empty description
        if (command.length == 1 || command[1].isBlank()) {
            throw new WrongCommandException("Uh oh! Bob says...the description of a deadline cannot be empty.");
        }

        String[] parts = command[1].split(" /by ");

        // if user gives empty '/by'
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new WrongCommandException("""
                    Uh oh! Bob says...a deadline task needs a '/by'.
                    e.g. deadline return book /by 18/02/2025 1800
                    """);
        }

        String description = parts[0];
        String byInput = parts[1];
        LocalDateTime by = Helper.inputToDateTime(byInput);
        return new DeadlineCommand(description, by);
    }

    private static Command parseEvent(String userInput) throws WrongCommandException {
        String[] command = userInput.split(" ", 2);

        // if user gives empty description
        if (command.length == 1 || command[1].isBlank()) {
            throw new WrongCommandException("Uh oh! Bob says...the description of an event cannot be empty.");
        }

        String[] parts = command[1].split(" /from ");

        // if user gives empty 'from'
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new WrongCommandException("""
                    Uh oh! Bob says...an event task needs a '/from' and '/to'.
                    e.g. event project meeting /from 17/04/2025 1400 /to 17/04/2025 1600
                    """);
        }

        String description = parts[0];
        String[] dates = parts[1].split(" /to ");

        // if user gives empty 'to'
        if (dates.length == 1 || dates[1].isBlank()) {
            throw new WrongCommandException("""
                    Uh oh! Bob says...an event task needs a '/to'.
                    e.g. event project meeting /from 17/04/2025 1400 /to 17/04/2025 1600
                    """);
        }

        String fromInput = dates[0];
        String toInput = dates[1];

        LocalDateTime from = Helper.inputToDateTime(fromInput);
        LocalDateTime to = Helper.inputToDateTime(toInput);

        return new EventCommand(description, from, to);
    }

    private static Command parseDelete(String userInput) throws
            WrongCommandException,
            IndexOutOfBoundsException,
            NumberFormatException {

        String[] parts = userInput.split(" ");

        // if user gives no task number(s)
        if (parts.length == 1) {
            throw new WrongCommandException("Uh oh! Bob says...the task number(s) to delete cannot be empty.");
        }

        ArrayList<Integer> taskNumbers = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            int number = Integer.parseInt(parts[i]);
            taskNumbers.add(number);
        }
        return new DeleteCommand(taskNumbers);
    }

    /**
     * Parses the user input and returns the respective command.
     *
     * @param userInput String.
     * @return Command object.
     * @throws WrongCommandException if the user input is invalid.
     * @throws IOException if there is an error saving to file.
     * @throws NumberFormatException if the user inputs NaN.
     * @throws IndexOutOfBoundsException if the user inputs a task beyond the list.
     */
    public static Command parse(String userInput) throws
            WrongCommandException,
            IOException,
            NumberFormatException,
            IndexOutOfBoundsException {

        String commandKey = userInput.split(" ")[0];
        ParseFunction commandFunction = COMMAND_MAP.get(commandKey);

        if (commandFunction == null) {
            throw new WrongCommandException("Unrecognised command!");
        }

        Command c = commandFunction.apply(userInput);
        return c;
    }
}
