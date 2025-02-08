package julie;

import julie.command.*;
import julie.exception.WrongFormatException;

public class Parser {
    private static final String TODO_FORMAT = "Oops! The correct format for a todo is:\n" +
            "todo <description>";
    private static final String DEADLINE_FORMAT = "The correct format for a deadline is:\n" +
            "deadline <description> /by <DD-MM-YYYY HHMM>";

    private static final String EVENT_FORMAT = "The correct format for an event is:\n" +
            "event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>";

    private static final String NUMBER_FORMAT_ERROR = "julie.task.Task number must be an integer!\n" +
            "Correct format: <command> <task number>";

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

        case "bye":
            return new ExitCommand();

        default:
            throw new WrongFormatException("Sorry, I didn't understand what you said!");
    }
    }

    private static Command parseToDoCommand(String input) throws WrongFormatException {
        String desc = extractDescription(input, "todo");

        if (desc.isEmpty()) {
            throw new WrongFormatException(TODO_FORMAT);
        }

        return new AddToDoCommand(desc);
    }

    private static Command parseDeadlineCommand(String input) throws WrongFormatException {
        String desc = extractDescription(input, "deadline");
        String[] getDeadline = input.split("/by ", 2);
        String dateTime = (getDeadline.length > 1) ? getDeadline[1].trim() : "";

        if (desc.isEmpty() && dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing both the deadline description and due date/time!\n" + DEADLINE_FORMAT);
        }
        if (desc.isEmpty()) {
            throw new WrongFormatException("Oops! Missing deadline description!\n" + DEADLINE_FORMAT);
        }
        if (dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing due date and time!\n" + DEADLINE_FORMAT);
        }

        return new AddDeadlineCommand(desc, dateTime);
    }

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

    private static Command parseIndexCommand(String[] parts, String action) throws WrongFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new WrongFormatException("Oops! Missing task number for " + action + "!\n" +
                    "Correct format: " + action + " <task number>");
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

    private static String extractDescription(String input, String command) {
        int commandLength = command.length() + 1;  // +1 for space after command
        return (input.length() > commandLength)
                ? input.substring(commandLength).split("/", 2)[0].trim()
                : "";
    }
}
