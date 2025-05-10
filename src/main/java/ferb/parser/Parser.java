package ferb.parser;

import ferb.command.*;
import ferb.exception.FerbException;
import ferb.filehandler.FerbFileHandler;
import ferb.tasklist.TaskList;
import ferb.task.*;

/**
 * Represents a parser that parses user input into commands.
 */
public class Parser {
    private TaskList tasks;
    private FerbFileHandler fileHandler;

    public Parser(TaskList tasks, FerbFileHandler fileHandler) {
        this.tasks = tasks;
        this.fileHandler = fileHandler;
    }

    /**
     * Parses the user input into a command.
     *
     * @param command the user input
     * @return the command to be executed
     * @throws FerbException if the command is not supported
     */
    public Command parse(String command) throws FerbException{
        if (command.equals("bye")) {
            return processExitCommand();
        } else if (command.equals("list")) {
            return processListCommand();
        } else if (command.startsWith("unmark")) {
            return processUnmarkCommand(command);
        } else if (command.startsWith("mark")) {
            return processMarkCommand(command);
        } else if (command.startsWith("todo")) {
            return processTodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return processDeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return processEventCommand(command);
        } else if (command.startsWith("delete")) {
            return processDeleteCommand(command);
        } else if (command.startsWith("find")) {
            return processFindCommand(command);
        } else if (command.startsWith("sort")) {
            return processSortCommand(command);
        } else {
            throw new FerbException("I'm so sorry! Command not supported!");
        }
    }

    private Command processListCommand() {
        return new ListCommand();
    }

    private Command processExitCommand() {
        return new ExitCommand();
    }

    private Command processTodoCommand(String command) throws FerbException {
        String description = ferbSubstring(command, 5, command.length());
        if (description.isEmpty()) {
            throw new FerbException("Description cannot be empty");
        }
        return new AddCommand(new ToDo(description));
    }

    private Command processDeadlineCommand(String command) throws FerbException {
        int byIndex = command.indexOf("/by");
        if (byIndex == -1) {
            throw new FerbException("Deadline command must contain '/by'");
        }
        String description = ferbSubstring(command, 9, byIndex - 1);
        String deadline = ferbSubstring(command, byIndex + 4, command.length());
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new FerbException("Description and deadline cannot be empty");
        }
        return new AddCommand(new Deadline(description, deadline));
    }

    private Command processEventCommand(String command) throws FerbException {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new FerbException("Event command must contain '/from' and '/to'");
        }
        String description = ferbSubstring(command, 6, fromIndex - 1);
        String from = ferbSubstring(command, fromIndex + 6, toIndex - 1);
        String to = ferbSubstring(command, toIndex + 4, command.length());
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new FerbException("Description, start date and end date cannot be empty");
        }
        return new AddCommand(new Event(description, from, to));
    }

    private Command processDeleteCommand(String command) throws FerbException {
        int index = getTaskIndex(command, 7, command.length());
        return new DeleteCommand(index);
    }

    private Command processFindCommand(String command) throws FerbException {
        String keyword = ferbSubstring(command, 5, command.length());
        return new FindCommand(keyword);
    }

    private Command processMarkCommand(String command) throws FerbException {
        int index = getTaskIndex(command, 5, command.length());
        return new MarkDoneCommand(index);
    }

    private Command processUnmarkCommand(String command) throws FerbException {
        int index = getTaskIndex(command, 7, command.length());
        return new UnmarkDoneCommand(index);
    }

    private Command processSortCommand(String command) throws FerbException{
        String[] splitCommand = command.split(" ");
        if (splitCommand.length != 2) {
            throw new FerbException("Please double check! Sort command should have exactly one type");
        }
        String sortType = splitCommand[1];
        if (!sortType.equals("date") && !sortType.equals("description")) {
            throw new FerbException("Please double check! Sort type should be either 'date' or 'description'");
        }
        return new SortCommand(sortType);
    }

    private String ferbSubstring(String command, int start, int end) throws FerbException {
        try {
            return command.substring(start, end).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new FerbException("Please double check! Arguments cannot be empty");
        }
    }

    private int getTaskIndex(String command, int start, int end) throws FerbException {
        int index;
        String stringIndex = ferbSubstring(command, start, end);
        try {
            index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new FerbException("Invalid index for command");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new FerbException("Index out of range for command");
        }
        return index;
    }


}