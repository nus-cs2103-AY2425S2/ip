package parakeet;

import parakeet.command.*;
import parakeet.task.Task;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing user input commands and creating
 * appropriate command objects to interact with the system.
 * It can handle various commands such as "bye", "list", "mark", "unmark",
 * "delete", as well as commands related to adding tasks, such as "todo", "event",
 * and "deadline". It also validates the correctness of the input.
 *
 * <p>It parses input for task descriptions, dates, and times, and ensures that
 * all necessary information is provided in the correct format.</p>
 *
 * @author Yang Qingru
 * @version 1.0
 * @since 2025-01-29
 */
public class Parser {
    public ArrayList<Task> list;
    public Parser() {
        this.list = new ArrayList<>();

    }

    /**
     * Parses a given command string and returns the appropriate Command object.
     * It handles various command types, such as:
     * - "bye" for exiting the application
     * - "list" for listing tasks
     * - "mark" for marking a task as done
     * - "unmark" for unmarking a task
     * - "delete" for deleting a task
     * - "todo", "deadline", and "event" for adding tasks
     *
     * @param inputCommand The input command string to be parsed.
     * @return The Command object corresponding to the parsed input.
     * @throws InvalidInputError If the command is invalid or in an incorrect format.
     */
    public Command parse(String inputCommand) throws InvalidInputError {
        String command = inputCommand.trim();
        if (command.equalsIgnoreCase("bye")) {
            return getExitCommand();
        }else if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            return getMarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return getUnmarkCommand(command);
        } else if (command.startsWith("delete")) {
            return getDeleteCommand(command);
        } else if (command.startsWith("find")){
            return getFindCommand(command);
        } else if (command.startsWith(("todo"))) {
            return getTodoCommand(command);
        }else if (command.startsWith(("deadline"))) {
            return getDeadlineCommand(command);
        } else if (command.startsWith(("event"))) {
            return getEventCommand(command);
        } else {
            throw new InvalidInputError("Sorry, I don't understand what you mean");
        }
    }

    private static EventCommand getEventCommand(String command) throws InvalidInputError {
        String processedCommand = processCommand(command);
        EventInfo eventInfo = extractEventInfo(processedCommand);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime timeFrom = LocalDateTime.parse(eventInfo.from(), formatter);
            LocalDateTime timeTo = LocalDateTime.parse(eventInfo.to(), formatter);
            return new EventCommand(eventInfo.description(), timeFrom, timeTo);
        } catch (DateTimeException e) {
            throw new InvalidInputError(
                    "Error: Invalid date format or invalid date. valid: yyyy-MM-dd HH:mm.");
        }
    }

    private static EventInfo extractEventInfo(String processedCommand) throws InvalidInputError {
        Matcher matcherDescription = getEventDescription(processedCommand);
        Matcher matcherFrom = getStartDate(processedCommand);
        Matcher matcherTo = getEndDate(processedCommand);

        boolean isValidInput = matcherFrom.find() && matcherTo.find() && matcherDescription.find();

        if (!isValidInput) {
            //check if time and description are given
            throw new InvalidInputError("Sorry, this is invalid input, you need to provide description and exact time");
        }
        //extract all the date and description of task
        String from = matcherFrom.group(1).trim();
        String to = matcherTo.group(1).trim();
        String description = matcherDescription.group(1).trim();

        if (description.isEmpty()) {
            throw new InvalidInputError("Sorry, the description can not be empty");
        }
        EventInfo eventInfo = new EventInfo(from, to, description);
        return eventInfo;
    }

    private record EventInfo(String from, String to, String description) {
    }

    private static Matcher getEndDate(String processedCommand) {
        String toRegex = "/to\\s+(.*)";
        Pattern patternT = Pattern.compile(toRegex);
        Matcher matcherTo = patternT.matcher(processedCommand);
        return matcherTo;
    }

    private static Matcher getStartDate(String processedCommand) {
        String fromRegex = "/from\\s+(.*?)(?=\\s*/to|$)";
        Pattern patternFrom = Pattern.compile(fromRegex);
        Matcher matcherFrom = patternFrom.matcher(processedCommand);
        return matcherFrom;
    }

    private static Matcher getEventDescription(String processedCommand) {
        String eventDescriptionRegex = "^(.*?)(?=\\s*/from|$)";
        Pattern patternDescription = Pattern.compile(eventDescriptionRegex);
        Matcher matcherDescription = patternDescription.matcher(processedCommand);
        return matcherDescription;
    }


    private static DeadlineCommand getDeadlineCommand(String command) throws InvalidInputError {
        String processedCommand = processCommand(command);
        DeadlineInfo deadlineInfo = extractDeadlineInfo(processedCommand);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(deadlineInfo.deadlineTime(), formatter);
            return new DeadlineCommand(deadlineInfo.description(), deadline);
        } catch (DateTimeException e) {
            throw new InvalidInputError("Error: Invalid date format or invalid date." +
                    " valid: yyyy-MM-dd HH:mm.");
        }
    }

    private static DeadlineInfo extractDeadlineInfo(String processedCommand) throws InvalidInputError {
        Matcher matcherDeadline = getDeadline(processedCommand);
        Matcher matcherDescription = getDeadlineDescription(processedCommand);

        boolean isValidInput = matcherDeadline.find() && matcherDescription.find();

        if(!isValidInput) {
            //check for case where deadline are not provided
            throw new InvalidInputError("Sorry, this is invalid input," +
                    " you need to provide description and deadline");
        }
        String deadlineTime = matcherDeadline.group(1).trim();
        String description = matcherDescription.group(1).trim();

        //check for input like "deadline /by Sunday"
        if(description.isEmpty()) {
            throw new InvalidInputError("Sorry, the description can not be empty");
        }

        DeadlineInfo deadlineInfo = new DeadlineInfo(deadlineTime, description);
        return deadlineInfo;
    }

    private record DeadlineInfo(String deadlineTime, String description) {
    }

    private static Matcher getDeadlineDescription(String processedCommand) {
        String eventDescriptionRegex = "^(.*?)(?=\\s*/by|$)";
        Pattern patternDescription = Pattern.compile(eventDescriptionRegex);
        Matcher matcherDescription = patternDescription.matcher(processedCommand);
        return matcherDescription;
    }

    private static Matcher getDeadline(String processedCommand) {
        String deadlineRegex = "/by\\s+(.*)";
        Pattern patternDeadline = Pattern.compile(deadlineRegex);
        Matcher matcherDeadline = patternDeadline.matcher(processedCommand);
        return matcherDeadline;
    }

    private static String processCommand(String command) throws InvalidInputError {
        //split the command , remove command word
        String[] splitCom = Arrays.copyOfRange(command.split(" "),1,
                command.split(" ").length);
        String commandOne= String.join(" ", splitCom);

        //check for invalid input like "deadline " or "deadline"
        if(splitCom.length == 0 || commandOne.trim().isEmpty()) {
            throw new InvalidInputError("Sorry, the description can not be empty");
        }

        assert splitCom.length > 0: "the length of description should be greater than zero";
        return commandOne;
    }

    private static TodoCommand getTodoCommand(String command) throws InvalidInputError {
        String processedCommand = processCommand(command);
        return new TodoCommand(processedCommand);
    }

    private static ExitCommand getExitCommand() {
        return new ExitCommand();
    }

    private static FindCommand getFindCommand(String command) throws InvalidInputError {
        String keyword = command.substring(5).trim();

        if(keyword.isEmpty()) {
            throw new InvalidInputError("the search keyword cannot be empty");
        }
        return new FindCommand(keyword);
    }

    private static DeleteCommand getDeleteCommand(String command) {
        int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        return new DeleteCommand(taskIndex);
    }

    private static UnmarkCommand getUnmarkCommand(String command) {
        int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        return new UnmarkCommand(taskIndex);
    }

    private static MarkCommand getMarkCommand(String command) {
        int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        return new MarkCommand(taskIndex);
    }
}
