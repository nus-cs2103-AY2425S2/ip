package taskmax.parser;

import taskmax.command.AddCommand;
import taskmax.command.Command;
import taskmax.command.DeleteCommand;
import taskmax.command.ExitCommand;
import taskmax.command.ListCommand;
import taskmax.command.MarkCommand;
import taskmax.command.UnmarkCommand;
import taskmax.command.FindCommand;
import taskmax.command.SortCommand;

import taskmax.exception.TaskmaxException;

import taskmax.task.Deadline;
import taskmax.task.Event;
import taskmax.task.ToDo;
import taskmax.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user commands into executable command objects.
 */
public class Parser {

    /**
     * Parses the user input into a corresponding Command object.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object.
     * @throws TaskmaxException If the input is invalid or incomplete.
     */
    public static Command parse(String input) throws TaskmaxException {
        assert input != null && !input.trim().isEmpty() : "Input command should not be null or empty";

        String[] text = input.split(" ", 2);
        String commandText = text[0];

        switch (commandText) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
            case "delete":
                return handleTaskModification(commandText, text);
            case "todo":
                return handleTodoCommand(text);
            case "deadline":
                return handleDeadlineCommand(text);
            case "event":
                return handleEventCommand(text);
            case "find":
                return handleFindCommand(text);
            case "sort": // Handle the sort command
                return handleSortCommand(text);
            default:
                throw new TaskmaxException(getHelpMessage());
        }
    }

    private static Command handleTaskModification(String command, String[] text) throws TaskmaxException {
        validateCommand(text, "Please enter a number of an existing task so I can find it in the list!");
        int index = Integer.parseInt(text[1]);

        switch (command) {
            case "mark":
                return new MarkCommand(index);
            case "unmark":
                return new UnmarkCommand(index);
            case "delete":
                return new DeleteCommand(index);
            default:
                throw new TaskmaxException("Invalid command.");
        }
    }

    private static Command handleTodoCommand(String[] text) throws TaskmaxException {
        validateCommand(text, "You have to include a task to add!\n"
                + "e.g. todo Assignment1 priority 2");
        String[] parts = text[1].split(" priority ");
        if (parts.length < 2) {
            throw new TaskmaxException("Priority not specified! Format: todo <task> priority <priority_level>");
        }
        int priority = Integer.parseInt(parts[1].trim()); // Extract priority value
        return new AddCommand(priority, new ToDo(parts[0].trim(), priority));
    }

    private static Command handleDeadlineCommand(String[] text) throws TaskmaxException {
        validateCommand(text, "Oops! You have to include a \"/by <deadline>\" \"priority <priority_number>\" after your task\n"
                + "e.g. deadline Assignment2 /by 2021-10-15 1800 priority 2\n");
        String[] deadlineParts = text[1].split(" /by ", 2);
        validateParts(deadlineParts, 2, "Oops! You have to include a \"/by deadline\" \"priority <priority_number>\" after your task\n"
                + "e.g. deadline Assignment2 /by 2021-10-15 1800 priority 2\n"
                + "Please try again!");
        String[] priorityParts = deadlineParts[1].split(" priority ");
        if (priorityParts.length < 2) {
            throw new TaskmaxException("Priority not specified! Format: deadline <task> /by <date> priority <priority_level>");
        }
        int priority = Integer.parseInt(priorityParts[1].trim());
        return new AddCommand(priority, new Deadline(deadlineParts[0].trim(), priorityParts[0].trim(), priority));
    }

    private static Command handleEventCommand(String[] text) throws TaskmaxException {
        validateCommand(text, "Oops! You have to include a \"/from start /to end\" \"priority <priority_number>\" after your task\n"
                + "e.g. event Concert /from 2021-10-15 1800 /to 2021-10-15 2200 priority 1");
        String[] eventParts = text[1].split(" /from | /to ", 3);
        validateParts(eventParts, 3, "Oops! You have to include a \"/from start /to end\" \"priority <priority_number>\" after your task\n"
                + "e.g. event Concert /from 2021-10-15 1800 /to 2021-10-15 2200 priority 1");
        String priorityPart = text[1].substring(text[1].lastIndexOf("priority") + 9).trim();
        int priority;

        try {
            priority = Integer.parseInt(priorityPart);  // Parse the priority level
        } catch (NumberFormatException e) {
            throw new TaskmaxException("Priority not specified correctly! Format: event <task> /from <start_time> /to <end_time> priority <priority_level>");
        }
        
        String startTime = eventParts[1].trim();
        String endTime = eventParts[2].substring(0, eventParts[2].lastIndexOf("priority")).trim();

        try {
            // Validate the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(startTime, formatter); // Just for validation
            LocalDateTime.parse(endTime, formatter);   // Just for validation

            return new AddCommand(priority, new Event(eventParts[0].trim(), startTime, endTime, priority));

        } catch (DateTimeParseException e) {
            throw new TaskmaxException("Invalid date/time format! Use 'yyyy-MM-dd HHmm' for start and end times.");
        }
    }


    private static Command handleFindCommand(String[] text) throws TaskmaxException {
        validateCommand(text, "Please provide a keyword to search for.");
        return new FindCommand(text[1].trim());
    }

    private static Command handleSortCommand(String[] text) throws TaskmaxException {
        validateCommand(text, "Please use \"sort priority\" instead.");
        return new SortCommand(text[1].trim());
    }

    /**
     * Validates that the user input contains the expected number of parts.
     *
     * @param text         The split input command.
     * @param errorMessage The error message to display if validation fails.
     * @throws TaskmaxException If the command is incomplete.
     */
    private static void validateCommand(String[] text, String errorMessage) throws TaskmaxException {
        assert text.length > 1 : "Missing argument in command";
        if (text.length < 2) {
            throw new TaskmaxException(errorMessage);
        }
    }

    /**
     * Validates that the provided parts array has the expected number of elements.
     *
     * @param parts          The split components of a command argument.
     * @param expectedLength The expected number of parts.
     * @param errorMessage   The error message to display if validation fails.
     * @throws TaskmaxException If the provided parts array is incomplete.
     */
    private static void validateParts(String[] parts, int expectedLength, String errorMessage) throws TaskmaxException {
        assert parts.length >= expectedLength : "Incomplete command arguments";
        if (parts.length < expectedLength) {
            throw new TaskmaxException(errorMessage);
        }
    }

    /**
     * Returns the help message when an invalid command is entered.
     *
     * @return The help message string.
     */
    private static String getHelpMessage() {
        return Ui.LINE + "\nHey there! There are 9 things I can help you with! \n"
                + "\n1. List: Enter \"list\" and I will list out all the tasks you have given me!\n"
                + "2. Todo: Enter \"todo <theTaskName> priority <number>\" to add a task you plan to do!\n"
                + "3. Deadlines: Enter \"deadline <theTaskName> /by <yyyy-mm-dd> <24hrTime> priority <number>\" to add a task with a \n   specific deadline!\n"
                + "4. Events: Enter \"event <theTaskName> /from <yyyy-mm-dd> <24hrTime> /to <yyyy-mm-dd> <24hrTime> priority\n    <number>\" to add an event!\n"
                + "5. Delete: Enter \"delete <theTaskListNumber>\" to delete a task from the list!\n"
                + "6. Mark as done: Enter \"mark <TaskListNumber>\" to mark the task as complete in the list!\n"
                + "7. Mark as undone: Enter \"unmark <TaskListNumber>\" to mark the task as incomplete in the list!\n"
                + "8. Find: Enter \"find <Word(s)YouWantToFind>\" to find tasks that match the keyword in the description.\n"
                + "9. Sort: Enter \"sort priority\" and I will sort the tasks by their priority!\n"  // Updated line for sort instruction
                + "\nIf you need a refresher, just enter any word!"
                + "\nIf you are satisfied with your service, enter \"bye\" to save your task list and exit!"
                + "\nDo remember that my input receptors are sensitive so please be careful with your spelling"
                + "\nand capital letters for commands!\n"
                + "\nThat is all and happy scheduling! ~Taskmax :D\n" + Ui.LINE;
    }
}
