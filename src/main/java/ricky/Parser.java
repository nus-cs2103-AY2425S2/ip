package ricky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ricky.command.AddCommand;
import ricky.command.Command;
import ricky.command.DeleteCommand;
import ricky.command.ExitCommand;
import ricky.command.FindCommand;
import ricky.command.InvalidCommand;
import ricky.command.ListCommand;
import ricky.command.MarkCommand;
import ricky.task.Deadline;
import ricky.task.Event;
import ricky.task.TaskList;
import ricky.task.ToDo;


/**
 * Parses user input into commands.
 */
public class Parser {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The command corresponding to the user input.
     * @throws RickyException If the user input is invalid.
     */
    public static Command parse(String input) throws RickyException {
        String[] inputs = input.split(" ");
        int INPUT_LENGTH = inputs.length;
        assert INPUT_LENGTH > 0 : "Input should not be empty.";
        String command = inputs[0];
        switch (command) {
        case "b":
        case "bye":
            return new ExitCommand();
        case "l":
        case "list":
            return new ListCommand();
        case "m":
        case "mark":
        case "um":
        case "unmark":
            if (inputs.length != 2 || !inputs[1].matches("\\d+")) {
                throw new RickyException("Please follow the format: unmark [task number]");
            }
            return new MarkCommand(Integer.parseInt(inputs[1]), command.equals("mark") || command.equals("m"));
        case "d":
        case "delete":
            if (inputs.length != 2 || !inputs[1].matches("\\d+")) {
                throw new RickyException("Please follow the format: delete [task number]");
            }
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        case "td":
        case "todo":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(input.substring(command.length() + 1)));
        case "ddl":
        case "deadline":
            if (inputs.length <= 3) {
                throw new RickyException("Please follow the format: deadline [task] /by [" + DATE_TIME_FORMAT + "]");
            }
            String[] deadlineInputs = input.substring(9).split(" /by ");
            LocalDateTime byDate;
            try {
                byDate = LocalDateTime.parse(deadlineInputs[1], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException("Please follow the format: deadline [task] /by [" + DATE_TIME_FORMAT + "]");
            }
            return new AddCommand(new Deadline(deadlineInputs[0], byDate));
        case "e":
        case "event":
            if (inputs.length <= 5) {
                throw new RickyException(String.format("Please follow the format: event [task] /from [%s] /to [%s]",
                        DATE_TIME_FORMAT, DATE_TIME_FORMAT));
            }
            String[] eventInputs = input.substring(6).split(" /from | /to ");
            LocalDateTime fromDate;
            LocalDateTime toDate;
            try {
                fromDate = LocalDateTime.parse(eventInputs[1], DATE_TIME_FORMATTER);
                toDate = LocalDateTime.parse(eventInputs[2], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException(String.format("Please follow the format: deadline [task] /by [%s]",
                        DATE_TIME_FORMAT));
            }
            return new AddCommand(new Event(eventInputs[0], fromDate, toDate));
        case "f":
        case "find":
            if (inputs.length <= 1) {
                throw new RickyException("Please follow the format: find [keyword]");
            }
            return new FindCommand(input.substring(command.length() + 1));
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses the data from the saved file and adds the tasks to the task list.
     *
     * @param data  The data from the saved file.
     * @param tasks The task list to add the tasks to.
     * @throws RickyException If the data is invalid.
     */
    public static void parseSavedTask(String[] data, TaskList tasks) throws RickyException {
        switch (data[0]) {
        case "T":
            if (data.length != 3 || (!data[1].equals("0") && !data[1].equals("1"))) {
                throw new RickyException("Invalid task in file.");
            }
            tasks.add(new ToDo(data[2]));
            if (data[1].equals("1")) {
                tasks.get(tasks.size() - 1).markDone();
            }
            break;
        case "D":
            if (data.length != 4 || (!data[1].equals("0") && !data[1].equals("1"))) {
                throw new RickyException("Invalid task in file.");
            }
            tasks.add(new Deadline(data[2], LocalDateTime.parse(data[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
            if (data[1].equals("1")) {
                tasks.get(tasks.size() - 1).markDone();
            }
            break;
        case "E":
            if (data.length != 5 || (!data[1].equals("0") && !data[1].equals("1"))) {
                throw new RickyException("Invalid task in file.");
            }
            tasks.add(new Event(data[2], LocalDateTime.parse(data[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                    LocalDateTime.parse(data[4], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
            if (data[1].equals("1")) {
                tasks.get(tasks.size() - 1).markDone();
            }
            break;
        default:
            throw new RickyException("Invalid task type in file.");
        }
    }
}
