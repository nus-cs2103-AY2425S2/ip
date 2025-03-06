package paimon.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import paimon.commands.Command;
import paimon.commands.CommandCreate;
import paimon.commands.CommandDelete;
import paimon.commands.CommandEmpty;
import paimon.commands.CommandFind;
import paimon.commands.CommandGoodbye;
import paimon.commands.CommandInsert;
import paimon.commands.CommandList;
import paimon.commands.CommandMark;
import paimon.commands.CommandText;
import paimon.commands.CommandUnmark;
import paimon.exceptions.PaimonInvalidInputException;
import paimon.items.Deadline;
import paimon.items.Event;
import paimon.items.Todo;
import paimon.tasklist.TaskList;

/**
 * Parse class that parse the natural language input string into Command objects.
 */
public class Parser {

    private static Command reverseCommand = new CommandEmpty(); // execute this to reverse the last command
    private static TaskList items;

    /**
     * Parses the input string and returns the corresponding Command object.
     * 
     * @param str Input string from user.
     * @return Command object that can be executed.
     */
    public static Command parse(String str) {
        // remove tracling and leading whitespaces
        str = str.trim();

        try {
            if (str.equals("bye")) {
                return new CommandGoodbye();
            } else if (str.equals("list")) {
                return new CommandList();
            } else if (str.startsWith("mark")) {
                return parseMarkCommand(str);
            } else if (str.startsWith("unmark")) {
                return parseUnmarkCommand(str);
            } else if (str.startsWith("delete")) {
                return parseDeleteCommand(str);
            } else if (str.startsWith("todo")) {
                return parseTodoCommand(str);
            } else if (str.startsWith("deadline")) {
                return parseDeadlineCommand(str);
            } else if (str.startsWith("event")) {
                return parseEventCommand(str);
            } else if (str.startsWith("find")) {
                return parseFindCommand(str);
            } else if (str.startsWith("undo")) {
                return parseUndoCommand(str);
            } else {
                throw new PaimonInvalidInputException(str);
            }
        } catch (PaimonInvalidInputException e) {
            System.out.println(e.getMessage());
            return new CommandText(e.getMessage());
        }
        // return new CommandEmpty();
    }

    private static Command parseMarkCommand(String str) throws PaimonInvalidInputException {
        if (str.length() <= 5) {
            throw new PaimonInvalidInputException("Index of item to mark cannot be empty.");
        }
        String num = str.substring(5);
        int index = Integer.parseInt(num) - 1;
        
        if (index >= Parser.items.size()) {
            throw new PaimonInvalidInputException("Index out of range. There are only " 
                    + Parser.items.size() + " items in the current list.");
        }
        if (index < 0) {
            throw new PaimonInvalidInputException("Index out of range. Index should be non-negative.");
        }

        Parser.reverseCommand = new CommandUnmark(index);
        return new CommandMark(index);
    }

    private static Command parseUnmarkCommand(String str) throws PaimonInvalidInputException {
        if (str.length() <= 7) {
            throw new PaimonInvalidInputException("Index of item to unmark cannot be empty.");
        }
        String num = str.substring(7);
        int index = Integer.parseInt(num) - 1;
        if (index >= Parser.items.size()) {
            throw new PaimonInvalidInputException("Index out of range. There are only " 
                    + Parser.items.size() + " items in the current list.");
        }
        if (index < 0) {
            throw new PaimonInvalidInputException("Index out of range. Index should be non-negative.");
        }

        Parser.reverseCommand = new CommandMark(index);
        return new CommandUnmark(index);
    }

    private static Command parseDeleteCommand(String str) {
        if (str.length() <= 7) {
            return new CommandText("Index of item to delete cannot be empty.");
        }
        String num = str.substring(7);
        int index = Integer.parseInt(num) - 1;
        assert index >= 0 : "Index should be non-negative";

        // add the item back 
        Parser.reverseCommand = new CommandInsert(Parser.items.get(index), index);
        return new CommandDelete(index);
    }

    private static Command parseTodoCommand(String str) throws PaimonInvalidInputException {
        if (str.length() <= 5) {
            throw new PaimonInvalidInputException("Description of todo cannot be empty.");
        }
        String description = str.substring(5);
        Todo todo = new Todo(description);

        Parser.reverseCommand = new CommandDelete(Parser.items.size());
        return new CommandCreate(todo);
    }

    private static Command parseDeadlineCommand(String str) throws PaimonInvalidInputException {
        if (str.length() <= 9) {
            throw new PaimonInvalidInputException("Description of deadline cannot be empty.");
        }
        String description = str.substring(9);

        // check if the deadline contains /by
        if (!description.contains(" /by ")) {
            throw new PaimonInvalidInputException("Deadline should contain /by");
        }
        assert description.contains(" /by ") : "Deadline should contain /by";
        String[] arr = description.split(" /by ");
        
        // check format of date and time.
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(arr[1], formatter);
        } catch (DateTimeParseException e) {
            throw new PaimonInvalidInputException("date and time format is not correct! it should be d/M/yyyy HHmm");
        }
        Deadline deadline = new Deadline(arr[0], arr[1]);

        Parser.reverseCommand = new CommandDelete(Parser.items.size());
        return new CommandCreate(deadline);
    }

    private static Command parseEventCommand(String str) throws PaimonInvalidInputException {
        if (str.length() <= 6) {
            throw new PaimonInvalidInputException("Description of event cannot be empty.");
        }
        String description = str.substring(6);
        
        // check if the event contains /from and /to
        if (!description.contains(" /from ") || !description.contains(" /to ")) {
            throw new PaimonInvalidInputException("Event should contain /from and /to");
        }
        assert description.contains(" /from ") : "Event should contain /from";
        assert description.contains(" /to ") : "Event should contain /to";
        String[] arr = description.split(" /from ");
        String[] arr2 = arr[1].split(" /to ");

        // checking validity of data and time. 
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(arr2[0], formatter);
            LocalDateTime.parse(arr2[1], formatter);
        } catch (DateTimeParseException e) {
            throw new PaimonInvalidInputException("date and time format is not correct! it should be d/M/yyyy HHmm");
        }
        Event event = new Event(arr[0], arr2[0], arr2[1]);

        Parser.reverseCommand = new CommandDelete(Parser.items.size());
        return new CommandCreate(event);
    }

    private static Command parseFindCommand(String str) {
        if (str.length() <= 5) {
            return new CommandText("Keyword cannot be empty.");
        }
        String keyword = str.substring(5);

        Parser.reverseCommand = new CommandEmpty();
        return new CommandFind(keyword);
    }

    private static Command parseUndoCommand(String str) {
        Command res = Parser.reverseCommand;
        Parser.reverseCommand = new CommandEmpty();
        return res;
    }

    public static void setTasklist(TaskList items) {
        Parser.items = items;
    }
}
