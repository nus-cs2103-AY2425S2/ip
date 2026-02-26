package carolyn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//reference to https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
//reference to https://stackoverflow.com/questions/20961617/get-the-current-index-of-a-for-each-loop-iterating-an-arraylist
//https://www.tutorialspoint.com/java/java_string_matches.htm
//https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/

public class Parser {
    static final int MAX_NUM_OF_ARGS = 3;
    /**
     * Parses a user input string and converts it into a {@link Command} object.
     *
     * @param s The input command string entered by the user.
     * @return A {@link Command} object representing the parsed command and its associated arguments.
     * @throws CarolynException If the input command is invalid, incomplete, or does not match any supported command patterns.
     */
    public Command parse(String s) throws CarolynException {
        Object[] args = new Object[MAX_NUM_OF_ARGS];
        String[] command = s.split(" ");

        switch (command[0]) {
            case "list", "bye" -> {
                return new Command(command[0], args);
            }
            case "mark", "unmark", "delete" -> {
                return parseSingleIndexCommand(command, args);
            }
            case "find" -> {
                return parseFindCommand(s, args);
            }
            case "tag" -> {
                return parseTagCommand(command, args);
            }
            case "todo" -> {
                return parseTodoCommand(s, args);
            }
            case "deadline" -> {
                return parseDeadlineCommand(s, args);
            }
            case "event" -> {
                return parseEventCommand(s, args);
            }
            default -> throw new CarolynException("invalid task type");
        }
    }



    private Command parseSingleIndexCommand(String[] command, Object[] args) throws CarolynException {
        if (command.length < 2) {
            throw new CarolynException("Invalid command format");
        }
        int index = Integer.parseInt(command[1]) - 1;
        args[0] = index;
        return new Command(command[0], args);
    }

    private Command parseFindCommand(String s, Object[] args) {
        args[0] = s.substring(s.indexOf(" ") + 1);
        return new Command("find", args);
    }

    private Command parseTagCommand(String[] command, Object[] args) throws CarolynException {
        if (command.length < 3) {
            throw new CarolynException("Invalid tag format");
        }
        args[0] = Integer.parseInt(command[1]) - 1;
        args[1] = command[2];
        return new Command("tag", args);
    }

    private Command parseTodoCommand(String s, Object[] args) throws CarolynException {
        int firstSpace = s.indexOf(" ");
        if (firstSpace == -1) {
            throw new CarolynException("no description for todo");
        }
        args[0] = s.substring(firstSpace + 1);
        return new Command("todo", args);
    }

    private Command parseDeadlineCommand(String s, Object[] args) {
        int firstSpace = s.indexOf(" ");
        int firstSlash = s.indexOf("/");
        int indexOfBy = s.indexOf("by");
        LocalDate date = LocalDate.parse(s.substring(indexOfBy + 3));
        args[0] = s.substring(firstSpace + 1, firstSlash - 1);
        args[1] = date;
        return new Command("deadline", args);
    }

    private Command parseEventCommand(String s, Object[] args) {
        int firstSpace = s.indexOf(" ");
        int indexOfFrom = s.indexOf("/from ");
        int indexOfTo = s.indexOf("/to ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        args[0] = s.substring(firstSpace + 1, indexOfFrom - 1);
        args[1] = LocalDateTime.parse(s.substring(indexOfFrom + 6, indexOfTo - 1), formatter);
        args[2] = LocalDateTime.parse(s.substring(indexOfTo + 4), formatter);

        return new Command("event", args);
    }

}