package miki.parse;

import java.time.LocalDateTime;

// Commands
import miki.command.AddDeadlineCommand;
import miki.command.AddEventCommand;
import miki.command.AddToDoCommand;
import miki.command.Command;
import miki.command.DeleteCommand;
import miki.command.ExitCommand;
import miki.command.FindCommand;
import miki.command.ListCommand;
import miki.command.MarkCommand;
import miki.command.UnmarkCommand;
// Exceptions
import miki.exception.DeadlineException;
import miki.exception.EventException;
import miki.exception.InvalidTaskException;
import miki.exception.MikiException;
import miki.exception.NoCommandException;
import miki.exception.ToDoException;

/**
 * Parser class handles the parsing of user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the relevant command.
     *
     * @param line user input.
     *
     * @returns relevant command.
     *
     * @throws MikiException if the user input is invalid.
     */
    public static Command handleUserCommand(String line) throws MikiException {
        line = line.trim();
        // empty input
        if (line.isEmpty()) {
            throw new NoCommandException("No command received Dawg. Try that again.");
        }
        String command = line.split(" ")[0].toLowerCase();

        switch (command) {
        // exit
        case "bye":
            return handleExitCommand();
        // list
        case "list":
            return handleListCommand(line);
        // mark
        case "mark":
            return handleMarkCommand(line);
        // unmark
        case "unmark":
            return handleUnmarkCommand(line);
        // delete
        case "delete":
            return handleDeleteCommand(line);
        // find
        case "find":
            return handleFindCommand(line);
        // event
        case "event":
            return handlEventCommand(line);
        // deadline
        case "deadline":
            return handleDeadlineCommand(line);
        // todo
        case "todo":
            return handleToDoCommand(line);
        // no command
        case "":
            throw new NoCommandException("No command dawg.");
        // invalid command
        default:
            throw new InvalidTaskException("What are you sayin, dawg.");
        }
    }

    /**
     * Handles the exit command.
     *
     * @returns ExitCommand
     */
    public static ExitCommand handleExitCommand() {
        return new ExitCommand();
    }

    /**
     * Handles the List command.
     *
     * @param line user input
     *
     * @returns ListCommand
     */
    public static ListCommand handleListCommand(String line) throws MikiException {
        if (!line.toLowerCase().equals("list")) {
            throw new MikiException("List command has no arguments. Do you want to find something instead?");
        }
        return new ListCommand();
    }

    /**
     * Handles the mark command.
     *
     * @param line user input
     *
     * @returns MarkCommand for given task
     */
    public static MarkCommand handleMarkCommand(String line) throws MikiException {
        String[] tokens = line.split(" ");
        assert tokens.length == 2 : "Mark command should have 2 arguments";
        try {
            int taskNumber = Integer.parseInt(tokens[1]);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new MikiException("The task number must be a valid integer.");
        }
    }

    /**
     * Handles the unmark command.
     *
     * @param line user input
     *
     * @returns UnmarkCommand for given task
     */
    public static UnmarkCommand handleUnmarkCommand(String line) throws MikiException {
        String[] tokens = line.split(" ");
        assert tokens.length == 2 : "Unmark command should have 2 arguments";
        try {
            int taskNumber = Integer.parseInt(tokens[1]);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new MikiException("The task number must be a valid integer.");
        }
    }

    /**
     * Handles the delete command.
     *
     * @param line user input
     *
     * @returns DeleteCommand for given task
     */
    public static DeleteCommand handleDeleteCommand(String line) throws MikiException {
        String[] tokens = line.split(" ");
        assert tokens.length == 2 : "Delete command should have 2 arguments";
        try {
            int taskNumber = Integer.parseInt(tokens[1]);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new MikiException("The task number must be a valid integer.");
        }
    }

    /**
     * Handles the Find command.
     *
     * @param line user input
     *
     * @returns FindCommand for given search keyword
     */
    public static FindCommand handleFindCommand(String line) throws MikiException {
        String[] tokens = line.split(" ", 2);
        assert tokens[1] != null : "Find command should have a keyword";

        return new FindCommand(tokens[1]);
    }

    /**
     * Handles the Event command.
     *
     * @param line user input
     *
     * @returns EventCommand to create given event
     */
    public static AddEventCommand handlEventCommand(String line) throws MikiException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new EventException("Incorrect fromat for Event command");
        }
        String[] taskDetails = tokens[1].split("/from|/to", 3);
        assert taskDetails.length == 3 : "Event command must have a description, start date and end date";

        // parse start date
        String startDateString = taskDetails[1].trim();
        LocalDateTime startDate = null;

        // parse end date
        String endDateString = taskDetails[2].split("/tags", 2)[0].trim();
        System.out.println(endDateString);
        System.out.println(startDateString);
        LocalDateTime endDate = null;
        try {
            startDate = DateParser.parseDate(startDateString);
            endDate = DateParser.parseDate(endDateString);
            assert endDate.isAfter(startDate) : "End date must be after start date";
        } catch (Exception e) {
            throw new EventException(e.getMessage());
        }

        String description = parseDescription(taskDetails[0]);
        String[] tags = parseTags(taskDetails[2]);

        return new AddEventCommand(description,
                startDate,
                endDate,
                tags);
    }

    /**
     * Handles the Deadline command.
     *
     * @param line user input
     *
     * @returns DeadlineCommand to create given deadline
     */
    public static AddDeadlineCommand handleDeadlineCommand(String line) throws MikiException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new DeadlineException("Incorrect format for Deadline command");
        }
        String[] taskDetails = tokens[1].split("/by", 2);
        assert taskDetails.length == 2 : "Deadline command must have a description and deadline date";

        String deadlineDateString = taskDetails[1].split("/tags", 2)[0].trim();
        // parse deadline date
        LocalDateTime deadlineDate = null;
        try {
            deadlineDate = DateParser.parseDate(deadlineDateString);
        } catch (Exception e) {
            throw new DeadlineException(e.getMessage());
        }
        String description = parseDescription(taskDetails[0]);
        String[] tags = parseTags(taskDetails[1]);
        return new AddDeadlineCommand(description,
                deadlineDate,
                tags);
    }

    /**
     * Handles the ToDo command.
     *
     * @param line user input
     *
     * @returns ToDoCommand to create given todo
     */
    public static AddToDoCommand handleToDoCommand(String line) throws MikiException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new ToDoException("ToDo command needs a description");
        }
        String description = parseDescription(tokens[1]);
        String[] tags = parseTags(tokens[1]);

        return new AddToDoCommand(description, tags);
    }

    /**
     * Parses the tags from the user input.
     *
     * @param line
     * @return String array of tags
     */
    public static String[] parseTags(String line) {
        String[] tokens = line.split("/tags", 2);
        if (tokens.length == 1) {
            return new String[0];
        }
        return tokens[1].trim().split(" ");
    }

    /**
     * Parses the description from the user input.
     *
     * @param line
     * @return String of description
     */
    public static String parseDescription(String line) {
        String[] tokens = line.split("/tags", 2);
        return tokens[0].trim();
    }
}
