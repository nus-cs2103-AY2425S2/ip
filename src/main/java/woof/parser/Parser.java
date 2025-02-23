package woof.parser;

import woof.command.AddDeadlineCommand;
import woof.command.AddEventCommand;
import woof.command.AddTodoCommand;
import woof.command.ClearCommand;
import woof.command.Command;
import woof.command.DeleteCommand;
import woof.command.ExitCommand;
import woof.command.FindCommand;
import woof.command.HelpCommand;
import woof.command.ListCommand;
import woof.command.MarkCommand;
import woof.command.UnmarkCommand;
import woof.task.TaskList;

/**
 * Parses the input by users through CLI.
 */
public class Parser {
    /**
     * Parses the line input by users through CLI. Those without further parsing is directly returned as a command.
     * Those with further arguments required is passed on to another parse function.
     *
     * @param input Input by users.
     * @return A corresponding command function.
     * @throws Exception Illegal inputs.
     */
    public static Command parse(String input) throws Exception {
        input = input.trim();
        if (input.contains(" ")) {
            String[] parts = input.split(" ", 2);
            assert parts.length == 2 : "Input should be split into exactly two parts";

            // MARK
            return switch (parts[0].toLowerCase()) {
            case "mark" -> parseMark(parts[1]);
            case "unmark" -> parseUnmark(parts[1]);
            case "delete" -> parseDelete(parts[1]);
            case "todo" -> parseCreateTodo(parts[1]);
            case "deadline" -> parseCreateDeadline(parts[1]);
            case "event" -> parseCreateEvent(parts[1]);
            case "find" -> parseFind(parts[1]);
            default -> throw new IllegalArgumentException("woof woof woof?");
            };
        } else {
            if (input.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (input.equalsIgnoreCase("clear")) {
                return new ClearCommand();
            } else if (input.equalsIgnoreCase("help")) {
                return new HelpCommand();
            } else {
                throw new IllegalArgumentException("woof woof woof?");
            }
        }
    }

    /**
     * Parses the description argument for creating a todo task.
     *
     * @param description Second part of the CLI input.
     * @return A corresponding command for creating a todo task.
     * @throws Exception Illegal inputs such as empty description.
     */
    public static Command parseCreateTodo(String description) throws Exception {
        description = description.trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("WERWER! Content of todo cannot be empty!");
        }
        return new AddTodoCommand(new String[] { description });
    }

    /**
     * Parses the input for finding tasks that matches the keywords.
     *
     * @param input Second part of the CLI input.
     * @return A corresponding command for finding tasks.
     * @throws Exception Illegal inputs such as empty keywords.
     */
    public static Command parseFind(String input) throws Exception {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("WERWER! What do you want to find?");
        }
        return new FindCommand(new String[] { input });
    }

    /**
     * Parses the description and due date arguments for creating a deadline task.
     *
     * @param input Second part of the CLI input.
     * @return A corresponding command for creating a deadline task.
     * @throws Exception Illegal inputs such as empty description.
     */
    public static Command parseCreateDeadline(String input) throws Exception {
        if (!input.contains("/by")) {
            throw new IllegalArgumentException("""
                WERWER! Something in deadline is missing!
                Follow this template woof: deadline (your task) /by (deadline)""");
        }
        String[] parts = input.split("/by");
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("""
                WERWER! Something in deadline is missing!
                Follow this template woof: deadline (your task) /by (deadline)""");
        }
        return new AddDeadlineCommand(parts);
    }

    /**
     * Parses the description, and start and end time arguments for creating an event task.
     *
     * @param input Second part of the CLI input.
     * @return A corresponding command for creating an event task.
     * @throws Exception Illegal inputs such as empty description.
     */
    public static Command parseCreateEvent(String input) throws Exception {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new IllegalArgumentException("""
                WERWER! Something in event is missing!
                Follow this template woof: event (your task) /from (start time) /to (end time)""");
        }
        String[] parts = input.split("/from|/to");
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()
                || parts[2].trim().isEmpty()) {
            throw new IllegalArgumentException("""
                WERWER! Something in event is missing!
                Follow this template woof: event (your task) /from (start time) /to (end time)""");
        }
        return new AddEventCommand(parts);
    }

    /**
     * Parses the index for marking.
     *
     * @param index Second part of the CLI input.
     * @return A corresponding command for marking a task as done.
     * @throws Exception Illegal inputs such as non-integers.
     */
    public static Command parseMark(String index) throws Exception {
        validateInt(index);
        return new MarkCommand(new String[] { index });
    }

    /**
     * Parses the index for unmarking.
     *
     * @param index Second part of the CLI input.
     * @return A corresponding command for unmarking a task as done.
     * @throws Exception Illegal inputs such as non-integers.
     */
    public static Command parseUnmark(String index) throws Exception {
        validateInt(index);
        return new UnmarkCommand(new String[] { index });
    }

    /**
     * Parses the index for deleting.
     *
     * @param index Second part of the CLI input.
     * @return A corresponding command for deleting a task.
     * @throws Exception Illegal inputs such as non-integers.
     */
    public static Command parseDelete(String index) throws Exception {
        validateInt(index);
        return new DeleteCommand(new String[] { index });
    }

    /**
     * Validates the index such that it is a positive integer that is within the size of the current task list.
     *
     * @param index Index to be validated
     * @throws Exception Illegal inputs such as non-integers.
     */
    public static void validateInt(String index) throws Exception {
        assert index != null : "Index should not be null";

        if (TaskList.size() == 0) {
            throw new IllegalStateException("WERWER! You have no tasks yet!");
        }
        index = index.trim();
        if (!isPositiveInteger(index)) {
            throw new NumberFormatException("WERWER! Make sure you have input a valid index!");
        }
        int i = Integer.parseInt(index);
        if (i > TaskList.size()) {
            throw new IndexOutOfBoundsException("WERWER! The index you have entered is too large!");
        }
    }

    private static boolean isPositiveInteger(String s) {
        assert s != null : "String should not be null";
        return s.matches("\\d+") && Integer.parseInt(s) > 0;
    }
}
