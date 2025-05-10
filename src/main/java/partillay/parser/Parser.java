package partillay.parser;

import partillay.command.AddCommand;
import partillay.command.ByeCommand;
import partillay.command.Command;
import partillay.command.DeleteCommand;
import partillay.command.FindCommand;
import partillay.command.MarkCommand;
import partillay.command.ShowTaskListCommand;
import partillay.command.UnmarkCommand;
import partillay.exception.PartillayException;
import partillay.exception.PartillayIncompleteDescriptionException;
import partillay.exception.PartillayInvalidCommandException;
import partillay.task.Deadline;
import partillay.task.Event;
import partillay.task.ToDo;

/**
 * Represents a parser that processes user inputs to return {@code Command} to be executed.
 */
public class Parser {

    /**
     * Parses the user's input string and returns the corresponding {@code Command}.
     *
     * @param userInput the raw input string from the user
     * @return a {@code Command} object representing the parsed command
     * @throws PartillayInvalidCommandException if the input does not match any valid command format
     */
    public static Command parse(String userInput) throws PartillayException {
        String trimmedInput = userInput.trim();
        trimmedInput = normalizeInput(trimmedInput);
        switch (getCommandWord(trimmedInput)) {
        case "bye":
            if (!trimmedInput.equals("bye")) {
                throw new PartillayInvalidCommandException("Bye should not have extra input, bestie!");
            }
            return new ByeCommand();
        case "list":
            if (!trimmedInput.equals("list")) {
                throw new PartillayInvalidCommandException("List should not have extra input, bestie!");
            }
            return new ShowTaskListCommand();
        case "find":
            return new FindCommand(parseTaskDescription(trimmedInput, 4, "keyword"));
        case "mark":
            return new MarkCommand(parseTaskIndex(trimmedInput, 5));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(trimmedInput, 7));
        case "delete":
            return new DeleteCommand(parseTaskIndex(trimmedInput, 7));
        case "todo":
            return new AddCommand(new ToDo(parseTaskDescription(trimmedInput, 4, "task")));
        case "deadline":
            return new AddCommand(handleDeadline(trimmedInput));
        case "event":
            return new AddCommand(handleEvent(trimmedInput));
        default:
            throw new PartillayInvalidCommandException("That's not a valid command, bestie!");
        }
    }

    /**
     * Normalizes the user input by replacing shorthand commands with their full versions.
     * This method ensures that input commands like 'b' or 'f' are correctly expanded
     * to 'bye' or 'find', respectively. It also handles the removal of extra spaces.
     *
     * @param input the raw user input string
     * @return the normalized input string with shorthand commands replaced by their full versions
     */
    private static String normalizeInput(String input) {
        if (input.equals("b")) {
            return "bye";
        }
        if (input.equals("l")) {
            return "list";
        }
        if (input.startsWith("f ")) {
            return "find" + input.substring(1);
        }
        if (input.startsWith("m ")) {
            return "mark" + input.substring(1);
        }
        if (input.startsWith("u ")) {
            return "unmark" + input.substring(1);
        }
        if (input.startsWith("d ")) {
            return "delete" + input.substring(1);
        }
        if (input.startsWith("t ")) {
            return "todo" + input.substring(1);
        }
        if (input.startsWith("dl ")) {
            return "deadline" + input.substring(2);
        }
        if (input.startsWith("e ")) {
            return "event" + input.substring(1);
        }
        return input;
    }

    /**
     * Extracts the command word from the user input.
     *
     * @param input the trimmed user input string
     * @return the command word (first word of the input)
     */
    private static String getCommandWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses and validates a task index from user input.
     *
     * @param input the user input string
     * @param prefixLength the length of the command word plus space (e.g., "mark " is 5)
     * @return the task index as an integer
     * @throws PartillayInvalidCommandException if the index is not a valid integer
     */
    private static int parseTaskIndex(String input, int prefixLength) throws PartillayException {
        try {
            return Integer.parseInt(input.substring(prefixLength).trim());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new PartillayInvalidCommandException("That's not a valid integer for me to interpret, bestie!");
        }
    }

    /**
     * Parses and validates a task description from user input.
     *
     * @param input the user input string
     * @param prefixLength the length of the command word plus space (e.g. "todo " is 4)
     * @param fieldName the part of command (i.e. "task" and "find" needs description) for error messages
     * @return the task description as a string
     * @throws PartillayIncompleteDescriptionException if the description is missing
     */
    private static String parseTaskDescription(
            String input, int prefixLength, String fieldName) throws PartillayException {
        String description = input.substring(prefixLength).trim();
        if (description.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, your " + fieldName + " cannot be empty!");
        }
        return description;
    }

    /**
     * Parses and constructs a {@code Deadline} task from user input.
     *
     * @param input the full user input string containing the deadline task description
     * @return a {@code Deadline} object representing the parsed deadline task
     * @throws PartillayException if the description or deadline date is missing or invalid
     */
    private static Deadline handleDeadline(String input) throws PartillayException {
        if (input.equals("deadline")) {
            throw new PartillayIncompleteDescriptionException("Bestie, your deadline task cannot be empty!");
        }
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length != 2) {
            throw new PartillayInvalidCommandException("Bestie, deadline task requires two components, "
                    + "description and deadline!");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, your task description cannot be empty!");
        }
        if (by.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, I need your deadline!");
        }
        return new Deadline(description, by);
    }

    /**
     * Parses and constructs an {@code Event} task from user input.
     *
     * @param input the full user input string containing the event task description
     * @return an {@code Event} object representing the parsed event task
     * @throws PartillayException if the description, start time, or end time is missing or invalid
     */
    private static Event handleEvent(String input) throws PartillayException {
        if (input.equals("event")) {
            throw new PartillayIncompleteDescriptionException("Bestie, your event task cannot be empty!");
        }
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length != 3) {
            throw new PartillayInvalidCommandException(
                    "Make sure you have the event description, when the event starts and when does it end, bestie!\n"
                    + "You're missing at least one of them!");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (description.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, your task cannot be empty!");
        }
        if (from.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, I need your starting time!");
        }
        if (to.isEmpty()) {
            throw new PartillayIncompleteDescriptionException("Bestie, I need your ending time!");
        }
        if (DateTimeFormatParser.isEarlierThan(to, from)) {
            throw new PartillayInvalidCommandException("The event can't end earlier than it starts!");
        }
        return new Event(description, from, to);
    }
}
