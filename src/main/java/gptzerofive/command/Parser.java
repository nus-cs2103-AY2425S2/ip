package gptzerofive.command;

import gptzerofive.exception.GptException;
import gptzerofive.exception.InvalidDateFormatException;
import gptzerofive.task.Deadline;
import gptzerofive.task.Event;
import gptzerofive.task.Todo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses the input command.
     *
     * @param input The user input.
     * @return The command to be executed.
     * @throws GptException If the command is invalid.
     */
    public static Command parse(String input) {
        assert input != null : "Input command should not be null";
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        try {
            return parseCommand(command, details);
        } catch (GptException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    private static Command parseCommand(String command, String details) throws GptException {
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(details));
        case "todo":
            return parseTodoCommand(details);
        case "deadline":
            return parseDeadline(details.split(" /by "));
        case "event":
            return parseEvent(details.split(" /from | /to "));
        case "find":
            return new FindCommand(details);
        case "delete":
            return new DeleteCommand(Integer.parseInt(details));
        case "newNote":
            return new NewNoteCommand(Integer.parseInt(details.split(" ")[0]), details.split(" ", 2)[1]);
        case "showNote":
            return new ShowNoteCommand(Integer.parseInt(details));
        case "deleteNote":
            return new DeleteNoteCommand(Integer.parseInt(details));
        case "editNote":
            return new EditNoteCommand(Integer.parseInt(details.split(" ")[0]), details.split(" ", 2)[1]);
        default:
            throw new GptException("No such command found.");
        }
    }

    private static Command parseTodoCommand(String details) throws GptException {
        if (details.isEmpty()) {
            throw new GptException("Please supply a description. Format: todo <description>");
        }
        return new AddCommand(new Todo(details));
    }

    private static Command parseDeadline(String... details) throws GptException {
        assert details != null : "Details should not be null for deadline command";

        if (details.length < 2) {
            throw new GptException(
                    "Please supply a description and a deadline. Format: deadline <description> /by <deadline>");
        }
        try {
            return new AddCommand(new Deadline(details[0], details[1]));
        } catch (InvalidDateFormatException e) {
            throw new GptException("Wrong date format. Please use d/M/yyyy HHmm.");
        }
    }

    private static Command parseEvent(String... details) throws GptException {
        assert details != null : "Details should not be null for event command";

        if (details.length < 3) {
            String exceptionMessage = "Please supply a description, a /from time, and a /to time. "
                    + "Format: event <description> /from <start time> /to <end time>";
            throw new GptException(exceptionMessage);
        }
        return new AddCommand(new Event(details[0], details[1], details[2]));
    }
}
