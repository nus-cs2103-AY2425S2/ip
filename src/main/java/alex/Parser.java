package alex;

import alex.command.AddCommand;
import alex.command.Command;
import alex.command.DeleteCommand;
import alex.command.DisplayCommand;
import alex.command.ExitCommand;
import alex.command.FindCommand;
import alex.command.MarkCommand;
import alex.exceptions.AlexException;
import alex.exceptions.CommandFormatException;
import alex.exceptions.InvalidCommandException;
import alex.task.Deadline;
import alex.task.Event;
import alex.task.TaskList;
import alex.task.ToDo;

/**
 * Parser parses user input string into Commands
 */
public class Parser {
    private static enum CommandType {
        DISPLAY, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, EXIT, FIND
    }

    private static final String exitCommand = "bye";
    private static final String displayCommand = "list";

    private static CommandType extractCommand(String input) throws InvalidCommandException {
        input = input.trim().toLowerCase();
        if (input.equals(exitCommand)) {
            return CommandType.EXIT;
        } else if (input.equals(displayCommand)) {
            return Parser.CommandType.DISPLAY;
        } else if (input.length() > 4 && input.substring(0, 5).equals("mark ")) {
            return CommandType.MARK;
        } else if (input.length() > 4 && input.substring(0, 5).equals("todo ")) {
            return CommandType.TODO;
        } else if (input.length() > 4 && input.substring(0, 5).equals("find ")) {
            return CommandType.FIND;
        } else if (input.length() > 5 && input.substring(0, 6).equals("event ")) {
            return CommandType.EVENT;
        } else if (input.length() > 6 && input.substring(0, 7).equals("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.length() > 6 && input.substring(0, 7).equals("delete ")) {
            return CommandType.DELETE;
        } else if (input.length() > 8 && input.substring(0, 9).equals("deadline ")) {
            return CommandType.DEADLINE;
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses string into commands
     * @param inputStr original user input
     * @param tasks the task list that are used in generating some commands
     * @return the command that can be executed
     * @throws Exception from parsing the string, mostly AlexException
     */
    public static Command parse(String inputStr, TaskList tasks) throws AlexException {
        try {
            CommandType commandType;
            commandType = Parser.extractCommand(inputStr);

            switch (commandType) {
            case DISPLAY:
                return new DisplayCommand();
            case MARK:
                return MarkCommand.parseMark(inputStr, tasks);
            case UNMARK:
                return MarkCommand.parseUnmark(inputStr, tasks);
            case TODO:
                return AddCommand.parseTodo(inputStr);
            case FIND:
                return FindCommand.parseFind(inputStr);
            case DEADLINE:
                return AddCommand.parseDeadline(inputStr);
            case EVENT:
                return AddCommand.parseEvent(inputStr);
            case DELETE:
                return DeleteCommand.parseDelete(inputStr, tasks);
            case EXIT:
                return new ExitCommand();
            default:
                throw new InvalidCommandException();
            }
        } catch (AlexException e) {
            throw e;
        } catch (Exception e) {
            throw new CommandFormatException();
        }
    }

    /**
     * Parses the user input representing a range
     * @param rangeStr the range input in format of from-to
     * @param tasks the task list
     * @return a pair of starting and ending index
     * @throws AlexException when the range is out of bound
     */
    public static int[] parseRange(String rangeStr, TaskList tasks) throws AlexException {
        String[] range = rangeStr.split("-");
        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);
        tasks.checkInBound(start);
        tasks.checkInBound(end);

        if (start > end) {
            return new int[]{end, start};
        } else {
            return new int[]{start, end};
        }
    }
}