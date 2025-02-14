package julie;

import julie.command.AddDeadlineCommand;
import julie.command.AddEventCommand;
import julie.command.AddToDoCommand;
import julie.command.Command;
import julie.command.DeleteCommand;
import julie.command.ExitCommand;
import julie.command.FindCommand;
import julie.command.ListCommand;
import julie.command.MarkCommand;
import julie.command.UnmarkCommand;
import julie.exception.WrongFormatException;

/**
 * Parses user input and returns the corresponding command.
 * Handles the extraction of relevant details from user commands
 * and ensures proper formatting before creating appropriate command objects.
 */
public class Parser {
    private static final String TODO_FORMAT = "Oops! The correct format for a todo is:\n"
            + "todo <description>";
    private static final String DEADLINE_FORMAT = "The correct format for a deadline is:\n"
            + "deadline <description> /by <DD-MM-YYYY HHMM>";

    private static final String EVENT_FORMAT = "The correct format for an event is:\n"
            + "event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>";

    private static final String NUMBER_FORMAT_ERROR = "Task number must be an integer!\n"
            + "Correct format: <command> <task number>";

    /**
     * Parses the user input string and returns the corresponding command.
     *
     * @param input The full command input from the user.
     * @return The corresponding {@code Command} object.
     * @throws WrongFormatException If the input format is incorrect or unrecognized.
     */
    public static Command parse(String input) throws WrongFormatException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "todo":
            return parseToDoCommand(input);

        case "deadline":
            return parseDeadlineCommand(input);

        case "event":
            return parseEventCommand(input);

        case "delete":
            return parseIndexCommand(parts, "delete");

        case "mark":
            return parseIndexCommand(parts, "mark");

        case "unmark":
            return parseIndexCommand(parts, "unmark");

        case "list":
            return new ListCommand();

        case "find":
            return parseFindCommand(parts);


        case "bye":
            return new ExitCommand();

        default:
            throw new WrongFormatException("Sorry, I didn't understand what you said!");
        }
    }

    /**
     * Parses a {@code ToDo} command and returns an {@code AddToDoCommand}.
     *
     * @param input The full user input.
     * @return An instance of {@code AddToDoCommand}.
     * @throws WrongFormatException If the todo description is missing.
     */
    private static Command parseToDoCommand(String input) throws WrongFormatException {
        String desc = extractDescription(input, "todo");

        if (desc.isEmpty()) {
            throw new WrongFormatException(TODO_FORMAT);
        }

        return new AddToDoCommand(desc);
    }

    /**
     * Parses a {@code deadline} command and returns an {@code AddDeadlineCommand}.
     *
     * @param input The full user input.
     * @return An instance of {@code AddDeadlineCommand}.
     * @throws WrongFormatException If the deadline description or date/time is missing.
     */
    private static Command parseDeadlineCommand(String input) throws WrongFormatException {
        String desc = extractDescription(input, "deadline");
        String[] getDeadline = input.split("/by ", 2);
        String dateTime = (getDeadline.length > 1) ? getDeadline[1].trim() : "";

        if (desc.isEmpty() && dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing both the deadline description and due date/time!\n"
                    + DEADLINE_FORMAT);
        }
        if (desc.isEmpty()) {
            throw new WrongFormatException("Oops! Missing deadline description!\n" + DEADLINE_FORMAT);
        }
        if (dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing due date and time!\n" + DEADLINE_FORMAT);
        }

        return new AddDeadlineCommand(desc, dateTime);
    }

    /**
     * Parses an {@code event} command and returns an {@code AddEventCommand}.
     *
     * @param input The full user input.
     * @return An instance of {@code AddEventCommand}.
     * @throws WrongFormatException If the event description, start, or end time is missing.
     */
    private static Command parseEventCommand(String input) throws WrongFormatException {
        String desc = extractDescription(input, "event");
        String[] getEvent = input.split("/from ", 2);
        String dateTimeFrom = (getEvent.length > 1) ? getEvent[1].split("/to", 2)[0].trim() : "";
        String dateTimeTo = (getEvent.length > 1 && getEvent[1].contains("/to"))
                ? getEvent[1].split("/to", 2)[1].trim()
                : "";

        if (desc.isEmpty() && dateTimeFrom.isEmpty() && dateTimeTo.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event description, start, and end times!\n" + EVENT_FORMAT);
        }
        if (desc.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event description!\n" + EVENT_FORMAT);
        }
        if (dateTimeFrom.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event start time!\n" + EVENT_FORMAT);
        }
        if (dateTimeTo.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event end time!\n" + EVENT_FORMAT);
        }

        return new AddEventCommand(desc, dateTimeFrom, dateTimeTo);
    }

    /**
     * Parses commands that require an integer index (e.g., delete, mark, unmark).
     *
     * @param parts  The split user input.
     * @param action The command action (e.g., "delete", "mark", "unmark").
     * @return The corresponding command object.
     * @throws WrongFormatException If the task number is missing or invalid.
     */
    private static Command parseIndexCommand(String[] parts, String action) throws WrongFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new WrongFormatException("Oops! Missing task number for " + action + "!\n"
                    + "Correct format: " + action + " <task number>");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            switch (action) {
            case "delete":
                return new DeleteCommand(index);
            case "mark":
                return new MarkCommand(index);
            case "unmark":
                return new UnmarkCommand(index);
            default:
                throw new WrongFormatException("Unknown action: " + action);
            }
        } catch (NumberFormatException e) {
            throw new WrongFormatException(NUMBER_FORMAT_ERROR);
        }
    }

    /**
     * Parses a {@code find} command and returns a {@code FindCommand}.
     *
     * @param parts The split user input.
     * @return An instance of {@code FindCommand}.
     * @throws WrongFormatException If the search keyword is missing.
     */
    private static Command parseFindCommand(String[] parts) throws WrongFormatException {
        String keyword = (parts.length > 1) ? parts[1].trim() : "";

        if (keyword.isEmpty()) {
            throw new WrongFormatException("Oops! The correct format for find is:\nfind <keyword>");
        }

        return new FindCommand(keyword);
    }


    /**
     * Extracts the task description from a user command.
     *
     * @param input   The full user input.
     * @param command The command keyword (e.g., "todo", "deadline", "event").
     * @return The extracted description.
     */
    private static String extractDescription(String input, String command) {
        int commandLength = command.length() + 1;
        return (input.length() > commandLength)
                ? input.substring(commandLength).split("/", 2)[0].trim()
                : "";
    }
}
