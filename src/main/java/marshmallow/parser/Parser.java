package marshmallow.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import marshmallow.MarshmallowException;
import marshmallow.commands.Command;
import marshmallow.commands.ExitCommand;
import marshmallow.commands.ListAddDeadlineCommand;
import marshmallow.commands.ListAddEventCommand;
import marshmallow.commands.ListAddTodoCommand;
import marshmallow.commands.ListDeleteTaskCommand;
import marshmallow.commands.ListDisplayCommand;
import marshmallow.commands.ListFindTaskCommand;
import marshmallow.commands.ListTaskMarkCommand;
import marshmallow.commands.ListTaskUnmarkCommand;

/**
 * Parses user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into a command.
     *
     * @param s Scanner to read user input from
     * @return Command representing the user input, or null if the input is invalid
     */
    public Command parse(Scanner s) {
        String cmd = s.next().toUpperCase();

        List<Keyword> candidates = Arrays.stream(Keyword.values()).filter(k -> k.toString().startsWith(cmd)).toList();
        if (candidates.size() != 1) {
            return null;
        }

        try {
            switch (candidates.get(0)) {
            case LIST:
                return new ListDisplayCommand();
            case TODO:
                return new ListAddTodoCommand(s.nextLine().trim());
            case DEADLINE:
                Map<String, String> deadlineSwitches = parseSwitches(s.nextLine());
                return new ListAddDeadlineCommand(deadlineSwitches.get(""), deadlineSwitches.get("by"));
            case EVENT:
                Map<String, String> eventSwitches = parseSwitches(s.nextLine());
                return new ListAddEventCommand(eventSwitches.get(""),
                        eventSwitches.get("from"),
                        eventSwitches.get("to"));
            case MARK:
                return new ListTaskMarkCommand(s.nextInt() - 1);
            case UNMARK:
                return new ListTaskUnmarkCommand(s.nextInt() - 1);
            case DELETE:
                return new ListDeleteTaskCommand(s.nextInt() - 1);
            case FIND:
                return new ListFindTaskCommand(s.nextLine().trim());
            case BYE:
                return new ExitCommand();
            default:
                // passthrough
            }
        } catch (InputMismatchException e) {
            s.nextLine();
            throw new MarshmallowException("Please follow the correct format for the command :(");
        } catch (NoSuchElementException e) {
            throw new MarshmallowException("Please provide the required information for the command :(");
        }

        return null;
    }

    /**
     * Parses the user input into a command.
     *
     * @param s String representing the user input
     * @return Command representing the user input, or null if the input is invalid
     */
    public Command parse(String s) {
        return parse(new Scanner(s));
    }

    private Map<String, String> parseSwitches(String line) {
        Map<String, String> switches = new HashMap<>();
        String[] parts = line.split("/");
        switches.put("", parts[0].trim());
        for (int i = 1; i < parts.length; i++) {
            String[] kv = parts[i].split(" ", 2);
            switches.put(kv[0], kv[1].trim());
        }
        return switches;
    }
}
