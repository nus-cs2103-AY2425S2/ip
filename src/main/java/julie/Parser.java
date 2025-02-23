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
import julie.command.SetPriorityCommand;
import julie.command.UnmarkCommand;
import julie.exception.WrongFormatException;
import julie.task.Task;

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
    private static final String INVALID_NUMBER_FORMAT = "Task number must be an integer!\n"
            + "Correct format: <command> <task number>";

    /**
     * Parses the user input string and returns the corresponding command.
     *
     * @param input The full command input from the user.
     * @return The corresponding {@code Command} object.
     * @throws WrongFormatException If the input format is incorrect or unrecognized.
     */
    public static Command parse(String input) throws WrongFormatException {
        assert input != null : "Input command string is null.";
        assert !input.trim().isEmpty() : "Input command string is empty.";

        String[] parts = input.split(" ");
        assert parts.length > 0 : "Command word extraction failed.";

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
        case "priority":
            return parsePriorityCommand(parts);
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
        assert desc != null : "Extracted ToDo description is null.";

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

        validateDeadlineInput(desc, dateTime);

        return new AddDeadlineCommand(desc, dateTime);
    }

    /**
     * Validates the input for a deadline task.
     * Ensures that both the description and due date/time are provided.
     *
     * @param desc     The description of the deadline task.
     * @param dateTime The due date and time of the deadline task.
     * @throws WrongFormatException If either the description or due date/time is missing.
     */
    private static void validateDeadlineInput(String desc, String dateTime) throws WrongFormatException {
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

        validateEventInput(desc, dateTimeFrom, dateTimeTo);

        return new AddEventCommand(desc, dateTimeFrom, dateTimeTo);
    }

    /**
     * Validates the input for an event task.
     * Ensures that the description, start date/time, and end date/time are provided.
     *
     * @param desc         The description of the event task.
     * @param dateTimeFrom The start date and time of the event.
     * @param dateTimeTo   The end date and time of the event.
     * @throws WrongFormatException If any of the required fields (description, start time, or end time) is missing.
     */
    private static void validateEventInput(String desc, String dateTimeFrom, String dateTimeTo)
            throws WrongFormatException {
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
    }

    /**
     * Parses a command that requires an integer index (e.g., "delete", "mark", "unmark").
     * Extracts the task index from user input and returns the corresponding command.
     *
     * @param parts  The split user input containing the command and index.
     * @param action The action to perform (e.g., "delete", "mark", "unmark").
     * @return The corresponding {@code Command} object.
     * @throws WrongFormatException If the task number is missing, invalid, or the action is unrecognized.
     */
    private static Command parseIndexCommand(String[] parts, String action) throws WrongFormatException {
        assert parts != null : "Input parts array is null.";
        assert parts.length > 0 : "Command parts array is empty.";

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new WrongFormatException("Oops! Missing task number for " + action + "!\n"
                    + "Correct format: " + action + " <task number>");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return createIndexCommand(action, index);
        } catch (NumberFormatException e) {
            throw new WrongFormatException(INVALID_NUMBER_FORMAT);
        }
    }

    /**
     * Creates a command that operates on tasks by index.
     * Supports "delete", "mark", and "unmark" actions.
     *
     * @param action The action to perform (e.g., "delete", "mark", "unmark").
     * @param index  The index of the task to operate on.
     * @return The corresponding {@code Command} object.
     * @throws WrongFormatException If the action is unrecognized.
     */
    private static Command createIndexCommand(String action, int index) throws WrongFormatException {
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
    }

    /**
     * Parses a {@code find} command and returns a {@code FindCommand}.
     *
     * @param parts The split user input.
     * @return An instance of {@code FindCommand}.
     * @throws WrongFormatException If the search keyword is missing.
     */
    private static Command parseFindCommand(String[] parts) throws WrongFormatException {
        assert parts != null : "Input parts array is null.";
        assert parts.length > 0 : "Command parts array is empty.";

        String keyword = (parts.length > 1) ? parts[1].trim() : "";
        assert keyword != null : "Extracted find keyword is null.";

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
        assert input != null : "Input string for extraction is null.";
        assert command != null : "Command keyword for extraction is null.";
        assert !input.isEmpty() : "Input string for extraction is empty.";
        assert !command.isEmpty() : "Command keyword for extraction is empty.";

        int commandLength = command.length() + 1;
        return (input.length() > commandLength)
                ? input.substring(commandLength).split("/", 2)[0].trim()
                : "";
    }

    /**
     * Parses a priority command.
     *
     * @param parts The split user input.
     * @return A {@code SetPriorityCommand} instance.
     * @throws WrongFormatException If the priority command is incorrectly formatted.
     */
    private static Command parsePriorityCommand(String[] parts) throws WrongFormatException {
        if (parts.length != 3) {
            throw new WrongFormatException("Oops! The correct format for setting priority is:\n"
                    + "priority <task_number> <H/M/L>");
        }

        try {
            int index = Integer.parseInt(parts[1]);
            Task.Priority priority = Task.Priority.valueOf(parts[2].toUpperCase());
            return new SetPriorityCommand(index, priority);
        } catch (NumberFormatException e) {
            throw new WrongFormatException("Task number must be an integer!");
        } catch (IllegalArgumentException e) {
            throw new WrongFormatException("Invalid priority! Please use H, M, or L.");
        }
    }
}
