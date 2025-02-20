package prophet.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import prophet.Prophet;
import prophet.command.AddDeadlineCommand;
import prophet.command.AddEventCommand;
import prophet.command.AddToDoCommand;
import prophet.command.Command;
import prophet.command.CommandType;
import prophet.command.DeleteTaskCommand;
import prophet.command.FindTaskCommand;
import prophet.command.ListCommand;
import prophet.command.MarkCommand;
import prophet.command.MarkNotDoneCommand;
import prophet.command.ScheduleCommand;
import prophet.command.UnknownCommand;
import prophet.exception.NoDescriptionException;

/**
 * The Parser class takes in user input and interprets it to perform the necessary actions.
 */
public class Parser {

    /**
     * Interprets command-line inputs and returns the command type according to keywords.
     * @param str the string representing the input
     * @return CommandType the type of command given in the input
     */
    public static Stream<Command> parse(String str)
            throws NoDescriptionException, NumberFormatException {

        String firstWord = Parser.separateStringByKeyword(str, " ", false)[0].toLowerCase();
        Stream<Command> commands = Stream.empty();

        switch (firstWord) {
        case "list":
            return Parser.addListCommand(commands);
        case "mark":
            return Parser.addMarkCommand(commands, str);
        case "unmark":
            return Parser.addMarkNotDoneCommand(commands, str);
        case "todo":
            return Parser.addToDoCommand(commands, str);
        case "t":
            return Parser.loadToDoCommandFromStorage(commands, str);
        case "deadline":
            return Parser.addDeadlineCommand(commands, str);
        case "d":
            return Parser.loadDeadlineCommandFromStorage(commands, str);
        case "event":
            return Parser.addEventCommand(commands, str);
        case "e":
            return Parser.loadEventCommandFromStorage(commands, str);
        case "delete":
            return Parser.addDeleteCommand(commands, str);
        case "find":
            return Parser.addFindCommand(commands, str);
        case "schedule":
            return Parser.addScheduleCommand(commands, str);
        default:
            Stream<Command> unknownCommand = Stream.of(new UnknownCommand(CommandType.UNKNOWN));
            return Stream.concat(commands, unknownCommand);
        }
    }

    /**
     * Separates a string by a keyword.
     * @param str the string to be separated
     * @param keyword the keyword to separate the string by
     * @param doChecks whether to do checks for the separated string
     * @return the separated string
     * @throws NoDescriptionException when the description provided is incomplete
     */
    public static String[] separateStringByKeyword(String str, String keyword, boolean doChecks)
            throws NoDescriptionException {
        String[] splitString = str.split(keyword, 2);
        if (doChecks) {
            if (splitString.length < 2 || splitString[1].trim().isEmpty()) {
                throw new NoDescriptionException();
            }
        }
        return splitString;
    }

    /**
     * Adds a ListCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addListCommand(Stream<Command> commands) {
        Stream<Command> newCommand = Stream.of(new ListCommand(CommandType.LIST));
        return Stream.concat(commands, newCommand);
    }

    /**
     * Adds a MarkCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     * @throws NumberFormatException when the task number provided is not an integer
     */
    public static Stream<Command> addMarkCommand(Stream<Command> commands, String description)
            throws NumberFormatException {
        try {
            String[] mark = Parser.separateStringByKeyword(description, "mark ", true);
            int index = Integer.parseInt(mark[1]) - 1;
            Stream<Command> newCommand = Stream.of(new MarkCommand(CommandType.MARK, index));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds a MarkNotDoneCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     * @throws NumberFormatException when the task number provided is not an integer
     */
    public static Stream<Command> addMarkNotDoneCommand(Stream<Command> commands, String description)
            throws NumberFormatException {
        try {
            String[] mark = Parser.separateStringByKeyword(description, "unmark ", true);
            int index = Integer.parseInt(mark[1]) - 1;
            Stream<Command> newCommand = Stream.of(new MarkNotDoneCommand(CommandType.UNMARK, index));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds an AddToDoCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addToDoCommand(Stream<Command> commands, String description) {
        try {
            String[] todo = Parser.separateStringByKeyword(description, "todo ", true);
            Stream<Command> newCommand = Stream.of(new AddToDoCommand(CommandType.TODO, todo[1].trim()));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Loads a ToDoCommand from storage.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> loadToDoCommandFromStorage(Stream<Command> commands, String description) {
        try {
            String[] todo = Parser.separateStringByKeyword(description, "T \\| ", true);
            String status = todo[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(todo[1], "]", true);
            Stream<Command> newCommand = Stream.of(
                    new AddToDoCommand(CommandType.TODO, statusAndDescription[1].trim()));
            if (status.equals("[X]")) {
                Stream<Command> markCommand = Stream.of(
                        new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                newCommand = Stream.concat(newCommand, markCommand);
            }
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds an AddDeadlineCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addDeadlineCommand(Stream<Command> commands, String description) {
        try {
            String[] deadline = Parser.separateStringByKeyword(description, "deadline ", true);
            String[] todoAndDeadline = Parser.separateStringByKeyword(deadline[1], "/by", true);

            // formatting the date so that it is readable
            LocalDateTime deadlineDate = LocalDateTime.parse(todoAndDeadline[1].trim());

            Stream<Command> newCommand = Stream.of(
                    new AddDeadlineCommand(CommandType.DEADLINE, todoAndDeadline[0].trim(), deadlineDate));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException | DateTimeParseException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Loads a DeadlineCommand from storage.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> loadDeadlineCommandFromStorage(Stream<Command> commands, String description) {
        try {
            String[] deadline = Parser.separateStringByKeyword(description, "D \\| ", true);
            String status = deadline[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(deadline[1], "]", true);
            String[] todoAndDeadline = Parser.separateStringByKeyword(
                    statusAndDescription[1], "by: ", true);

            // formatting the date so that it is readable
            LocalDateTime deadlineDate = LocalDateTime.parse(
                    todoAndDeadline[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

            Stream<Command> newCommand = Stream.of(
                    new AddDeadlineCommand(CommandType.DEADLINE, todoAndDeadline[0].trim(), deadlineDate));
            if (status.equals("[X]")) {
                Stream<Command> markCommand = Stream.of(
                        new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                newCommand = Stream.concat(newCommand, markCommand);
            }
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException | DateTimeParseException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds an AddEventCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addEventCommand(Stream<Command> commands, String description) {
        try {
            String[] event = Parser.separateStringByKeyword(description, "event ", true);
            String[] remainingParts = Parser.separateStringByKeyword(event[1], "/from ", true);
            String[] timeline = Parser.separateStringByKeyword(remainingParts[1], "/to ", true);

            // formatting the date so that it is readable
            LocalDateTime from = LocalDateTime.parse(timeline[0].trim());
            LocalDateTime to = LocalDateTime.parse(timeline[1].trim());

            Stream<Command> newCommand = Stream.of(
                    new AddEventCommand(CommandType.EVENT, remainingParts[0].trim(), from, to));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException | DateTimeParseException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Loads an EventCommand from storage.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> loadEventCommandFromStorage(Stream<Command> commands, String description) {
        try {
            String[] event = Parser.separateStringByKeyword(description, "E \\| ", true);
            String status = event[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(event[1], "]", true);
            String[] remainingParts = Parser.separateStringByKeyword(
                    statusAndDescription[1], "from: ", true);
            String[] timeline = Parser.separateStringByKeyword(remainingParts[1], "to: ", true);

            // formatting the date
            LocalDateTime from = LocalDateTime.parse(
                    timeline[0].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(
                    timeline[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

            Stream<Command> newCommand = Stream.of(
                    new AddEventCommand(CommandType.EVENT, remainingParts[0].trim(), from, to));
            if (status.equals("[X]")) {
                Stream<Command> markCommand = Stream.of(
                        new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                newCommand = Stream.concat(newCommand, markCommand);
            }
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException | DateTimeParseException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds a DeleteTaskCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     * @throws NumberFormatException when the task number provided is not an integer
     */
    public static Stream<Command> addDeleteCommand(Stream<Command> commands, String description)
            throws NumberFormatException {
        try {
            String[] delete = Parser.separateStringByKeyword(description, "delete ", true);
            int index = Integer.parseInt(delete[1]) - 1;
            Stream<Command> newCommand = Stream.of(new DeleteTaskCommand(CommandType.DELETE, index));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds a FindTaskCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addFindCommand(Stream<Command> commands, String description) {
        try {
            String[] keyword = Parser.separateStringByKeyword(description, "find ", true);
            Stream<Command> newCommand = Stream.of(new FindTaskCommand(CommandType.FIND, keyword[1].trim()));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }

    /**
     * Adds a ScheduleCommand to the list of commands.
     * @param commands the {@link Stream} of commands to be run
     * @param description the description of the command
     * @return the updated {@link Stream} of commands to be run
     */
    public static Stream<Command> addScheduleCommand(Stream<Command> commands, String description) {
        try {
            String[] schedule = Parser.separateStringByKeyword(description, "schedule ", true);
            LocalDate date = LocalDate.parse(schedule[1].trim());
            Stream<Command> newCommand = Stream.of(new ScheduleCommand(CommandType.SCHEDULE, date));
            return Stream.concat(commands, newCommand);
        } catch (NoDescriptionException | DateTimeParseException e) {
            return Stream.concat(commands, Stream.of(new UnknownCommand(CommandType.UNKNOWN)));
        }
    }
}
