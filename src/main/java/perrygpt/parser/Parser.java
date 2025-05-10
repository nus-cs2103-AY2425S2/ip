package perrygpt.parser;

import perrygpt.commands.*;
import perrygpt.exceptions.PerryGPTException;

/**
 * Parses user input and returns the corresponding {@code Command} object.
 * This class is responsible for interpreting user commands and mapping them
 * to their respective execution logic.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding {@code Command} object.
     *
     * @param input The user input string.
     * @return The corresponding {@code Command} based on the user input.
     * @throws PerryGPTException If the input is invalid or the command is unknown.
     */
    public static Command parse(String input) throws PerryGPTException {
        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "todo":
                return new AddTodoCommand(arguments);

            case "find":
                return new FindCommand(arguments);

            case "deadline":
                return parseDeadline(arguments);

            case "updateevent":
                return parseUpdateEvent(arguments);

            case "event":
                return parseEvent(arguments);

            case "mark":
                int markIndex = parseIndex(arguments);
                return new MarkCommand(markIndex);

            case "unmark":
                int unmarkIndex = parseIndex(arguments);
                return new UnmarkCommand(unmarkIndex);

            case "delete":
                int deleteIndex = parseIndex(arguments);
                return new DeleteCommand(deleteIndex);

            default:
                throw new PerryGPTException("Unknown command: " + commandWord);
        }
    }

    /**
     * Parses the arguments for adding a deadline task.
     *
     * @param arguments The user input arguments for the deadline task.
     * @return An {@code AddDeadlineCommand} containing the parsed description and due date.
     * @throws PerryGPTException If the input format is incorrect.
     */
    private static Command parseDeadline(String arguments) throws PerryGPTException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new PerryGPTException("Invalid deadline format! Use: deadline <task_description> /by " +
                    "<yyyy-MM-dd HHmm>");
        }
        return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the arguments for adding an event task.
     *
     * @param arguments The user input arguments for the event task.
     * @return An {@code AddEventCommand} containing the parsed description, start time, and end time.
     * @throws PerryGPTException If the input format is incorrect.
     */
    private static Command parseEvent(String arguments) throws PerryGPTException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new PerryGPTException("Invalid event format! Use: event <task_description> " +
                    "/from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        return new AddEventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Parses the arguments for updating an event task.
     *
     * @param arguments The user input arguments for updating the event.
     * @return An {@code UpdateEventCommand} containing the parsed index and new event details.
     * @throws PerryGPTException If the input format is incorrect or the task index is invalid.
     */
    private static Command parseUpdateEvent(String arguments) throws PerryGPTException {
        // Extract the first word (index) from the rest
        String[] firstSplit = arguments.split(" ", 2);
        if (firstSplit.length < 2) {
            throw new PerryGPTException("Invalid format! Use: updateevent <event_task_index> " +
                    "/from <new_from_date_and_time> /to <new_to_date_and_time>");
        }

        // Convert index (1-based to 0-based)
        int index;
        try {
            index = Integer.parseInt(firstSplit[0].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new PerryGPTException("Invalid task index. Please provide a valid number.");
        }

        // Split remaining arguments by "/from" and "/to"
        String remainingArgs = firstSplit[1];

        // Extract new from/to times
        String newFrom = null;
        String newTo = null;

        if (remainingArgs.contains("/from")) {
            String[] fromParts = remainingArgs.split("/from ", 2);
            if (fromParts.length > 1) {
                newFrom = fromParts[1].contains("/to") ?
                        fromParts[1].split(" /to ")[0].trim() : fromParts[1].trim();
            }
        }

        if (remainingArgs.contains("/to")) {
            String[] toParts = remainingArgs.split(" /to ", 2);
            if (toParts.length > 1) {  // Ensure there's something after /to
                newTo = toParts[1].trim();
            }
        }

        return new UpdateEventCommand(index, newFrom, newTo);
    }

    /**
     * Parses an index from the user input and converts it from a 1-based to a 0-based index.
     *
     * @param argument The user input containing the task index.
     * @return The 0-based index of the task.
     * @throws PerryGPTException If the input is not a valid number.
     */
    private static int parseIndex(String argument) throws PerryGPTException {
        try {
            return Integer.parseInt(argument) - 1; // Convert 1-based to 0-based index
        } catch (NumberFormatException e) {
            throw new PerryGPTException("Invalid task index.");
        }
    }
}