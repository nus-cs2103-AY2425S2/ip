package parser;

import java.util.Arrays;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import dew.DewException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * The Parser class processes user input and returns the corresponding command.
 */
public class Parser {
    /**
     * Parses user input and returns the appropriate Command object.
     *
     * @param userInput The raw user input string.
     * @return The corresponding Command object.
     * @throws DewException If the command is invalid or incorrectly formatted.
     */
    public static Command parse(String userInput) throws DewException {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0].trim();

        switch (command) {
        case "list":
            return handleList(inputParts);
        case "mark":
            return handleMark(inputParts);
        case "unmark":
            return handleUnmark(inputParts);
        case "todo":
            return handleTodo(inputParts);
        case "deadline":
            return handleDeadline(inputParts);
        case "event":
            return handleEvent(inputParts);
        case "edit":
            return handleEdit(inputParts);
        case "delete":
            return handleDelete(inputParts);
        case "find":
            return handleFind(inputParts);
        case "bye":
            return handleBye();
        default:
            throw new DewException("Unknown command.");
        }
    }

    /**
     * Handles the "list" command, which lists all tasks.
     * Ensures that the command is exactly "list" with no additional arguments.
     *
     * @param inputParts The user input split into parts.
     * @return A ListCommand object.
     * @throws DewException If additional arguments are provided.
     */
    private static Command handleList(String[] inputParts) throws DewException {
        if (inputParts.length > 1 && !inputParts[1].trim().isEmpty()) {
            throw new DewException("The 'list' command should not have any arguments.");
        }
        return new ListCommand();
    }

    /**
     * Handles the "mark" command, which marks a task as completed.
     * Ensures that the input is strictly an integer.
     *
     * @param inputParts The user input split into parts.
     * @return A MarkCommand object.
     * @throws DewException If the command is missing a task number or the input is not a valid integer.
     */
    private static Command handleMark(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Mark command must include a task number.");
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1].trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DewException("Invalid task number! Please enter a valid integer.");
        }
    }

    /**
     * Handles the "unmark" command, which marks a task as incomplete.
     * Ensures that the input is strictly an integer.
     *
     * @param inputParts The user input split into parts.
     * @return An UnmarkCommand object.
     * @throws DewException If the command is missing a task number or the input is not a valid integer.
     */
    private static Command handleUnmark(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Unmark command must include a task number.");
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1].trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DewException("Invalid task number! Please enter a valid integer.");
        }
    }

    /**
     * Handles the "todo" command, which adds a new todo task.
     *
     * @param inputParts The user input split into parts.
     * @return An AddCommand object containing the new todo task.
     * @throws DewException If the task description is missing.
     */
    private static Command handleTodo(String[] inputParts) throws DewException {
        boolean isInputLengthInvalid = inputParts.length < 2;
        boolean isDescriptionEmpty = Arrays.stream(inputParts)
                .skip(1)
                .map(String::trim)
                .allMatch(String::isEmpty);
        if (isInputLengthInvalid || isDescriptionEmpty) {
            throw new DewException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(inputParts[1]));
    }

    /**
     * Handles the "deadline" command, which adds a new deadline task.
     *
     * @param inputParts The user input split into parts.
     * @return An AddCommand object containing the new deadline task.
     * @throws DewException If the task description or date is missing, or if the date format is incorrect.
     */
    private static Command handleDeadline(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Deadline command must include a task description and a deadline.");
        }
        if (!inputParts[1].contains(" /by ")) {
            throw new DewException("Deadline format: deadline <task> /by <YYYY-MM-DD HHmm>");
        }

        String[] deadlineParts = inputParts[1].split(" /by ", 2);
        String taskDescription = deadlineParts[0].trim();
        String dateTimeString = deadlineParts[1].trim();

        try {
            return new AddCommand(new Deadline(taskDescription, dateTimeString));
        } catch (IllegalArgumentException e) {
            throw new DewException("Invalid date format! Use YYYY-MM-DD HHmm.");
        }
    }
    /**
     * Handles the "event" command, which adds a new event task.
     *
     * @param inputParts The user input split into parts.
     * @return An AddCommand object containing the new event task.
     * @throws DewException If the task description, start time, or end time is missing.
     */
    private static Command handleEvent(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Event command must include a task description and time range.");
        }
        if (!inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
            throw new DewException("task.Event format: event <task> /from <start> /to <end>");
        }
        String[] eventParts = inputParts[1].split(" /from ", 2);
        String[] timeParts = eventParts[1].split(" /to ", 2);
        return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
    }

    /**
     * Handles the "edit" command, which modifies an existing task.
     *
     * @param inputParts The user input split into parts.
     * @return An EditCommand object.
     * @throws DewException If the command format is incorrect.
     */
    private static Command handleEdit(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Invalid edit format! Use: edit <task_index> <component> <new_value>");
        }

        try {
            String[] editParts = inputParts[1].split("\\s+", 3);
            if (editParts.length < 3) {
                throw new DewException("Incomplete edit command! Use: edit <task_index> <component> <new_value>");
            }

            int taskIndex = Integer.parseInt(editParts[0]) - 1;
            String component = editParts[1].trim();
            String newValue = editParts[2].trim();

            if (newValue.isEmpty()) {
                throw new DewException("The new value for the edit command cannot be empty.");
            }

            return new EditCommand(taskIndex, component, newValue);

        } catch (NumberFormatException e) {
            throw new DewException("Invalid task number! Task index must be a valid number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DewException("Invalid edit format! Use: edit <task_index> <component> <new_value>");
        }
    }

    /**
     * Handles the "delete" command, which removes a task.
     *
     * @param inputParts The user input split into parts.
     * @return A DeleteCommand object.
     * @throws DewException If the task number is missing.
     */
    private static Command handleDelete(String[] inputParts) throws DewException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new DewException("Delete command must include a task number.");
        }
        return new DeleteCommand(Integer.parseInt(inputParts[1]) - 1);
    }

    /**
     * Handles the "find" command, which searches for tasks containing a keyword.
     *
     * @param inputParts The user input split into parts.
     * @return A FindCommand object.
     * @throws DewException If the keyword is missing.
     */
    private static Command handleFind(String[] inputParts) throws DewException {
        boolean isInputLengthInvalid = inputParts.length < 2;
        boolean isDescriptionEmpty = Arrays.stream(inputParts)
                .skip(1)
                .map(String::trim)
                .allMatch(String::isEmpty);
        if (isInputLengthInvalid || isDescriptionEmpty) {
            throw new DewException("The keyword for find cannot be empty.");
        }
        return new FindCommand(inputParts[1]);
    }

    /**
     * Handles the "bye" command, which exits the application.
     *
     * @return An ExitCommand object.
     */
    private static Command handleBye() {
        return new ExitCommand();
    }
}
