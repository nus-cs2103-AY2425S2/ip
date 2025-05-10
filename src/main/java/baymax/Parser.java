package baymax;

import baymax.command.*;

/**
 * Parses user input and returns the corresponding {@code Command} object.
 * The {@code Parser} class is responsible for analyzing user input and
 * determining the appropriate action to be executed.
 */
public class Parser {
    /**
     * Parses the user input and returns a {@code Command} object corresponding to the input command.
     *
     * @param input The full command entered by the user.
     * @return A {@code Command} object that corresponds to the user input.
     */
    public static Command parse(String input) {
        assert (input != null && !input.trim().isEmpty()) : "Command input should not be null or empty";

        String[] words = input.trim().split(" ", 2);
        String commandWord = words[0].toLowerCase();
        String args = words.length > 1 ? words[1].trim() : "";

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(args);
        case "unmark":
            return parseUnmarkCommand(args);
        case "todo":
            return parseTodoCommand(args);
        case "deadline":
            return parseDeadlineCommand(args);
        case "event":
            return parseEventCommand(args);
        case "delete":
            return parseDeleteCommand(args);
        case "find":
            return parseFindCommand(args);
        case "help":
            return new HelpCommand(true); // Explicitly call help
        default:
            return new HelpCommand(false); // Triggered due to invalid input
        }
    }

    /** Parses and validates a mark command */
    private static Command parseMarkCommand(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand("Oops! You need to provide a task number to mark.");
        }
        try {
            int taskNumber = Integer.parseInt(args);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid task number! Please provide a valid number.");
        }
    }

    /** Parses and validates an unmark command */
    private static Command parseUnmarkCommand(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand("Oops! You need to provide a task number to unmark.");
        }
        try {
            int taskNumber = Integer.parseInt(args);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid task number! Please provide a valid number.");
        }
    }

    /** Parses and validates a todo command */
    private static Command parseTodoCommand(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand("Oops! A to-do task needs a name.");
        }
        return new AddTodoCommand(args);
    }

    /** Parses and validates a deadline command */
    private static Command parseDeadlineCommand(String args) {
        if (!args.toLowerCase().contains(" /by ")) {
            return new ErrorCommand("Oops! A deadline must be in the format: deadline <task> /by <yyyy-MM-dd optional:HHmm>.");
        }
        String[] parts = args.split("(?i)\\s*/by\\s*", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return new ErrorCommand("Oops! A deadline must have a name and a valid date.");
        }
        return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    /** Parses and validates an event command */
    private static Command parseEventCommand(String args) {
        if (!args.toLowerCase().contains(" /from ") || !args.toLowerCase().contains(" /to ")) {
            return new ErrorCommand("Oops! An event must be in the format: event <task> /from <yyyy-MM-dd optional:HHmm> /to <yyyy-MM-dd optional:HHmm>.");
        }
        String[] parts = args.split("(?i)\s*/from\s*", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            return new ErrorCommand("Oops! An event must have a name.");
        }
        String[] timeParts = parts[1].split("(?i)\\s*/to\\s*", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            return new ErrorCommand("Oops! An event must have a start and end time.");
        }
        return new AddEventCommand(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }

    /** Parses and validates a delete command */
    private static Command parseDeleteCommand(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand("Oops! You need to provide a task number to delete.");
        }
        try {
            int taskNumber = Integer.parseInt(args);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid task number! Please provide a valid number.");
        }
    }

    /** Parses and validates a find command */
    private static Command parseFindCommand(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand("Oops! You need to provide a keyword to search for.");
        }
        return new FindCommand(args);
    }
}
