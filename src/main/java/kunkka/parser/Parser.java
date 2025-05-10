package kunkka.parser;

import kunkka.components.Task;
import kunkka.components.Todo;
import kunkka.components.Deadline;
import kunkka.components.Event;
import kunkka.components.KunkkaException;

import kunkka.command.Command;
import kunkka.command.List;
import kunkka.command.Mark;
import kunkka.command.Unmark;
import kunkka.command.TodoCommand;
import kunkka.command.DeadlineCommand;
import kunkka.command.EventCommand;
import kunkka.command.Delete;
import kunkka.command.Find;
import kunkka.command.FindPriority;
import kunkka.command.SetPriority;
import kunkka.command.InvalidCommand;

/**
 * Parser class to parse user input and return the corresponding command or task object.
 */
public class Parser {
    /**
     * Parses a task from a string.
     * @param taskLine the string to parse
     * @return the task object
     */
    public static Task parseTask(String taskLine) {
        String[] parts = taskLine.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String name = parts[2];
        switch (type) {
            case "T":
                return new Todo(name, isDone, Integer.parseInt(parts[3]));
            case "D":
                return new Deadline(name, parts[4], isDone, Integer.parseInt(parts[3]));
            case "E":
                return new Event(name, parts[4], parts[5], isDone, Integer.parseInt(parts[3]));
            default:
                return null;
        }
    }

    /**
     * Parses a command from a string.
     * @param command the string to parse
     * @return the command object
     */
    public static Command parseCommand(String command) {
        command = command.trim();   
        if (command.equals("list")) {
            return parseListCommand();
        } else if (command.matches("mark -?\\d+")) {
            return parseMarkCommand(command);
        } else if (command.matches("unmark \\d+")) {
            return parseUnmarkCommand(command);
        } else if (command.matches("todo .*")) {
            return parseTodoCommand(command);
        } else if (command.matches("deadline .* /by .*")) {
            return parseDeadlineCommand(command);
        } else if (command.matches("event .* /from.*/to.*")) {
            return parseEventCommand(command);
        } else if (command.matches("delete -?\\d+")) {
            return parseDeleteCommand(command);
        } else if (command.matches("find -p -?\\d+")) {
            return parseFindPriorityCommand(command);
        } else if (command.matches("find -priority -?\\d+")) {
            return parseFindPriorityCommand(command);
        } else if (command.matches("find .*")) {
            return parseFindCommand(command);
        } else if (command.matches("setpriority -?\\d+ -?\\d+")) {
            return parseSetPriorityCommand(command);
        } else {
            return parseInvalidCommand();
        }
    }

    /**
     * Parses a list command.
     * @return the list command object
     */
    private static Command parseListCommand() {
        return new List();
    }

    /**
     * Parses a mark command.
     * @param command the string to parse
     * @return the mark command object
     */
    private static Command parseMarkCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Mark(index);
    }

    /**
     * Parses an unmark command.
     * @param command the string to parse
     * @return the unmark command object
     */
    private static Command parseUnmarkCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Unmark(index);
    }

    /**
     * Parses a todo command.
     * @param command the string to parse
     * @return the todo command object
     */
    private static Command parseTodoCommand(String command) {
        String[] parts = command.split(" /priority ");
        String description = parts[0].split(" ", 2)[1];
        int priority = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        return new TodoCommand(description, priority);
    }

    /**
     * Parses a deadline command.
     * @param command the string to parse
     * @return the deadline command object
     */
    private static Command parseDeadlineCommand(String command) {
        String[] parts = command.split("/by");
        String description = parts[0].split(" ", 2)[1].trim();
        String[] deadlineParts = parts[1].trim().split(" /priority ");
        String deadline = deadlineParts[0];
        int priority = deadlineParts.length > 1 ? Integer.parseInt(deadlineParts[1]) : 0;
        return new DeadlineCommand(description, deadline, priority);
    }

    /**
     * Parses an event command.
     * @param command the string to parse
     * @return the event command object
     */
    private static Command parseEventCommand(String command) {
        String name = command.split("/from")[0].split(" ", 2)[1].trim();
        String from = command.split("/from")[1].split("/to")[0].trim();
        String[] toParts = command.split("/to")[1].trim().split(" /priority ");
        String to = toParts[0];
        int priority = toParts.length > 1 ? Integer.parseInt(toParts[1]) : 0;
        return new EventCommand(name, from, to, priority);
    }

    /**
     * Parses a delete command.
     * @param command the string to parse
     * @return the delete command object
     */
    private static Command parseDeleteCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        return new Delete(index);
    }

    /**
     * Parses a find command.
     * @param command the string to parse
     * @return the find command object
     */
    private static Command parseFindCommand(String command) {
        return new Find(command.split(" ", 2)[1]);
    }

    /**
     * Parses a find priority command.
     * @param command the string to parse
     * @param priority the priority to search for
     * @return the find priority command object
     */
    private static Command parseFindPriorityCommand(String command) {
        return new FindPriority(Integer.parseInt(command.trim().split(" ")[2]));
    }

    /**
     * Parses a set priority command.
     * @param command the string to parse
     * @return the set priority command object
     */
    private static Command parseSetPriorityCommand(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        int priority = Integer.parseInt(command.split(" ")[2]);
        return new SetPriority(priority, index - 1);
    }

    /**
     * Parses an invalid command.
     * @return the invalid command object
     */
    private static Command parseInvalidCommand() {
        try {
            throw new KunkkaException("Error: Invalid command");
        } catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand();
        }
    }
}