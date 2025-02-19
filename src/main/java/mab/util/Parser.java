package mab.util;

import mab.command.*;
import mab.MabException;

/**
 * Converts user input strings into executable command objects.
 */
public class Parser {

    /**
     * Parses raw command input into corresponding Command implementation.
     * 
     * @param c Full command input string
     * @return Executable command object
     * @throws MabException For unrecognized commands
     * 
     * @implSpec Supported commands:
     * <pre>list, todo, deadline, event, mark, unmark, delete</pre>
     */
    public static Command parse(String c) throws MabException {
        String[] tokens = c.trim().split("\\s+", 2);//split the command using spaces into 2 parts 
        String comm = tokens[0].toLowerCase(), args = tokens.length > 1 ? tokens[1].trim() : "";

        return switch (comm) {
            case "list" -> new ListCommand(args);
            case "todo" -> new ToDosCommand(args);
            case "deadline" -> new DeadlinesCommand(args);
            case "event" -> new EventsCommand(args);
            case "mark" -> new MarkingCommand(args, true);
            case "unmark" -> new MarkingCommand(args, false);
            case "delete" -> new DeleteCommand(args);
            case "find" -> new FindCommand(args);
            case "sort" -> new SortCommand(args);
            default -> throw new MabException("Hmmmmmm i didn't get that please try again :)");
        };
    }
}
