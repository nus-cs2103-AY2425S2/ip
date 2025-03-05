package g.parser;

import g.commands.*;
import g.tasks.Deadline;
import g.tasks.Event;
import g.tasks.Todo;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The user's raw input string.
     * @return The corresponding Command object.
     */
    public static Command parse(String userInput) {
        assert userInput != null : "User input should not be null!";
        userInput = userInput.trim();
        
        if (userInput.isEmpty()) {
            return new InvalidCommand("Input cannot be empty!");
        }

        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "list":
                return new ListCommand();
            
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    return new InvalidCommand("Todo description cannot be empty!");
                }
                return new AddCommand(new Todo(parts[1].trim()));
            
            case "deadline":
                if (parts.length < 2 || !parts[1].contains("/by")) {
                    return new InvalidCommand("Usage: deadline <task> /by <date>");
                }
                String[] deadlineParts = parts[1].split("/by", 2);
                if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    return new InvalidCommand("Deadline must have a valid date.");
                }
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));

            case "event":
                if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                    return new InvalidCommand("Usage: event <task> /from <start> /to <end>");
                }
                String[] eventParts = parts[1].split("/from", 2);
                String[] timeParts = eventParts[1].split("/to", 2);
                if (eventParts[0].trim().isEmpty() || timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                    return new InvalidCommand("Event must have a valid start and end time.");
                }
                return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
            
            case "mark":
                if (!isValidIndex(parts)) {
                    return new InvalidCommand("Usage: mark <task number>");
                }
                return new MarkCommand(Integer.parseInt(parts[1]) - 1);
            
            case "unmark":
                if (!isValidIndex(parts)) {
                    return new InvalidCommand("Usage: unmark <task number>");
                }
                return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
            
            case "delete":
                if (!isValidIndex(parts)) {
                    return new InvalidCommand("Usage: delete <task number>");
                }
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            
            case "find":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    return new InvalidCommand("Usage: find <keyword>");
                }
                return new FindCommand(parts[1].trim());

            case "bye":
                return new ExitCommand();

            default:
                return new InvalidCommand("Unknown command: " + command);
        }
    }

    /**
     * Helper method to check if the second part of the input is a valid number.
     *
     * @param parts The split input string.
     * @return True if valid index, otherwise false.
     */
    private static boolean isValidIndex(String[] parts) {
        if (parts.length < 2) return false;
        try {
            int index = Integer.parseInt(parts[1]);
            return index > 0; // Ensure it's a positive number
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
