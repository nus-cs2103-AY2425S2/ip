package tasker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import tasker.command.AddCommand;
import tasker.command.ByeCommand;
import tasker.command.Command;
import tasker.command.CommandType;
import tasker.command.DeleteCommand;
import tasker.command.FindCommand;
import tasker.command.ListCommand;
import tasker.command.MarkCommand;
import tasker.command.UnmarkCommand;
import tasker.exception.TaskerException;
import tasker.task.DateTimeTask;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.task.FixedDuration;
import tasker.task.Task;
import tasker.task.TaskType;
import tasker.task.Todo;

/**
 * Parses tasks.
 */
class Parser {
    /**
     * Creates a Todo task from command.
     *
     * @param desc Command information to create the task.
     * @param desc Description of the task.
     */
    private static Todo createTodoTask(String desc) {
        return new Todo(desc);
    }

    /**
     * Gets the value of an argument from the split command.
     *
     * @param arg The argument string to get the value from.
     * @returns The string value of the argument.
     */
    private static String getArgValue(String arg) {
        return arg.replaceFirst("^\\S+\\s+", "");
    }

    /**
     * Creates a Deadline task from command.
     *
     * @param desc                Description of the task.
     * @param args                Arguments to the command.
     * @param dateTimeInputFormat The format of tasks with date and time.
     */
    private static Deadline createDeadlineTask(String desc, String[] args) throws TaskerException {
        TaskerException deadlineException = new TaskerException(
                String.format("Please provide a deadline with: \"/by %s\".", DateTimeTask.INPUT_FORMAT));

        if (args.length != 1 || !args[0].startsWith("/by ")) {
            throw deadlineException;
        }

        try {
            return new Deadline(desc, DateTimeTask.parseInput(getArgValue(args[0])));
        } catch (DateTimeParseException e) {
            throw deadlineException;
        }

    }

    /**
     * Creates an Event task from command.
     *
     * @param desc                Description of the task.
     * @param args                Arguments to the command.
     * @param dateTimeInputFormat The format of tasks with date and time.
     */
    private static Event createEventTask(String desc, String[] args) throws TaskerException {
        String dateTimeInputFormat = DateTimeTask.INPUT_FORMAT;
        TaskerException formatException = new TaskerException(
                String.format("Please provide a start and end time with: \"/from %s /to %s\".",
                        dateTimeInputFormat, dateTimeInputFormat));
        LocalDateTime from;
        LocalDateTime to;

        if (args.length != 2 || !args[0].startsWith("/from ") || !args[1].startsWith("/to ")) {
            throw formatException;
        }

        try {
            from = DateTimeTask.parseInput(getArgValue(args[0]));
            to = DateTimeTask.parseInput(getArgValue(args[1]));
        } catch (DateTimeParseException e) {
            throw formatException;
        }

        if (from.isAfter(to)) {
            throw new TaskerException("Start time cannot be after end time.");
        }

        return new Event(desc, from, to);
    }

    /**
     * Creates a FixedDuration task from command.
     *
     * @param desc Description of the task.
     * @param args Arguments to the command.
     */
    private static FixedDuration createFixedDuration(String desc, String[] args) throws TaskerException {
        TaskerException eventException = new TaskerException(
                "Please provide a start and end time with: \"/hr h /min m\".");
        int hr;
        int min;

        if (args.length != 2 || !args[0].startsWith("/hr ") || !args[1].startsWith("/min ")) {
            throw eventException;
        }

        try {
            hr = Integer.parseInt(getArgValue(args[0]));
            min = Integer.parseInt(getArgValue(args[1]));
        } catch (NumberFormatException e) {
            throw eventException;
        }

        if (hr < 0 || min < 0) {
            throw new TaskerException("Hours and minutes must be positive.");
        }

        return new FixedDuration(desc, Duration.ofHours(hr).plusMinutes(min));
    }

    /**
     * Creates a command to add a task.
     *
     * @param command     The command to determine the task type.
     * @param commandInfo Other parts of the command for information.
     * @returns A command to add the correct task when executed.
     * @throws TaskerException If there is an error with the command.
     */
    private static AddCommand createAddCommand(CommandType command, String commandInfo) throws TaskerException {
        if (commandInfo == null || commandInfo.startsWith("/")) {
            throw new TaskerException("Please provide a description for the task.");
        }

        String[] taskInfo = commandInfo.split("\\s+(?=/)");
        String desc = taskInfo[0];
        String[] args = Arrays.copyOfRange(taskInfo, 1, taskInfo.length);
        Task taskToAdd = null;

        if (desc.contains("|")) {
            throw new TaskerException("\"|\" is not allowed in task description.");
        }

        switch (command) {
        case TODO:
            taskToAdd = createTodoTask(desc);
            break;

        case DEADLINE:
            taskToAdd = createDeadlineTask(desc, args);
            break;

        case EVENT:
            taskToAdd = createEventTask(desc, args);
            break;

        case FIXED:
            taskToAdd = createFixedDuration(desc, args);
            break;

        default:
            throw new TaskerException("Not a command to add tasks.");
        }

        assert taskToAdd != null : "Task to add is null.";
        return new AddCommand(taskToAdd);
    }

    /**
     * Creates a command to modify the task list based on a provided index.
     *
     * @param command     The command to determine the task type.
     * @param commandInfo Other parts of the command for information.
     * @returns A command to modify the task list based on the index in the command.
     * @throws TaskerException If there is an error with the command.
     */
    private static Command createIndexedCommand(CommandType command, String commandInfo) throws TaskerException {
        if (commandInfo == null) {
            throw new TaskerException("Please provide a task number.");
        }

        int index;

        try {
            index = Integer.parseInt(commandInfo) - 1;
        } catch (NumberFormatException e) {
            throw new TaskerException("Please provide a valid number.");
        }

        switch (command) {
        case DELETE:
            return new DeleteCommand(index);

        case MARK:
            return new MarkCommand(index);

        case UNMARK:
            return new UnmarkCommand(index);

        default:
            throw new TaskerException("Not a command to modify tasks.");
        }
    }

    /**
     * Creates a command to find a task with a string.
     *
     * @param commandInfo Other parts of the command for information.
     * @returns A command to find a task with the string.
     * @throws TaskerException If there is an error with the command.
     */
    private static FindCommand createFindCommand(String commandInfo) throws TaskerException {
        if (commandInfo == null) {
            throw new TaskerException("Please provide a search term.");
        }

        return new FindCommand(commandInfo);
    }

    /**
     * Creates a command to list contents of the task list.
     *
     * @returns A command that lists tasks.
     */
    private static ListCommand createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a command to exit the program.
     *
     * @returns A command that exits the program.
     */
    private static ByeCommand createByeCommand() {
        return new ByeCommand();
    }

    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user.
     * @return The command to handle the user input.
     * @throws TaskerException If there is an error with the command.
     */
    public static Command parseCommand(String command) throws TaskerException {
        String[] commandParts = command.strip().split("\\s+", 2);
        CommandType mainPart;
        String commandInfo = commandParts.length == 2 ? commandParts[1] : null;
        TaskerException unknownCommandException = new TaskerException(String.format("""
                Unknown command: %s
                %s""", commandParts[0], CommandType.listCommands()));

        try {
            mainPart = CommandType.valueOf(commandParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw unknownCommandException;
        }

        switch (mainPart) {
        // Fallthrough
        case DEADLINE:
        case EVENT:
        case TODO:
        case FIXED:
            return createAddCommand(mainPart, commandInfo);

        // Fallthrough
        case DELETE:
        case MARK:
        case UNMARK:
            return createIndexedCommand(mainPart, commandInfo);

        case FIND:
            return createFindCommand(commandInfo);

        case LIST:
            return createListCommand();

        case BYE:
            return createByeCommand();

        default:
            throw unknownCommandException;
        }
    }

    /**
     * Creates a Todo task from storage string.
     *
     * @param description The description of the task.
     * @param isDone      If the task is complete.
     * @return a todo task based on the storage information.
     */
    private static Todo createTodoFromStorage(String description, boolean isDone) {
        return new Todo(description, isDone);
    }

    /**
     * Creates a Deadline task from storage string.
     *
     * @param description The description of the task.
     * @param isDone      If the task is complete.
     * @param args        Additional information from storage for task.
     * @return A dealine task based on the storage information.
     */
    private static Deadline createDeadlineFromStorage(String description, boolean isDone, String[] args)
            throws TaskerException {
        TaskerException exception = new TaskerException("Incorrect deadline task storage format.");

        if (args.length != 1) {
            throw exception;
        }

        try {
            return new Deadline(description, isDone, LocalDateTime.parse(args[0]));
        } catch (DateTimeParseException e) {
            throw exception;
        }

    }

    /**
     * Creates an Event task from storage string.
     *
     * @param description The description of the task.
     * @param isDone      If the task is complete.
     * @param args        Additional information from storage for task.
     * @return An event task based on the storage ingormation.
     */
    private static Event createEventFromStorage(String description, boolean isDone, String[] args)
            throws TaskerException {
        TaskerException exception = new TaskerException("Incorrect event task storage format.");

        if (args.length != 2) {
            throw exception;
        }

        try {
            return new Event(description, isDone, LocalDateTime.parse(args[0]), LocalDateTime.parse(args[1]));
        } catch (DateTimeParseException e) {
            throw exception;
        }
    }

    /**
     * Creates a FixedDuration task from storage string.
     *
     * @param description The description of the task.
     * @param isDone      If the task is complete.
     * @param args        Additional information from storage for task.
     * @return An event task based on the storage ingormation.
     */
    private static FixedDuration createFixedDurationFromStorage(String description, boolean isDone, String[] args)
            throws TaskerException {
        TaskerException exception = new TaskerException("Incorrect fixed duration task storage format.");

        if (args.length != 1) {
            throw exception;
        }

        try {
            return new FixedDuration(description, isDone, Duration.parse(args[0]));
        } catch (DateTimeParseException e) {
            throw exception;
        }
    }

    /**
     * Parses tasks from the storages into task objects.
     *
     * @param line The line to parse.
     * @returns The task represented by the line.
     * @throws TaskerException If there is an error parsing the task.
     */
    public static Task parseStorage(String line) throws TaskerException {
        String[] parts = line.split("\\|");
        TaskerException incorrectFormat = new TaskerException("Incorrect storage format");

        if (parts.length < 3) {
            throw incorrectFormat;
        }

        TaskType type = TaskType.valueOf(parts[0]);
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];
        String[] args = Arrays.copyOfRange(parts, 3, parts.length);

        switch (type) {
        case T:
            return createTodoFromStorage(description, isDone);

        case D:
            return createDeadlineFromStorage(description, isDone, args);

        case E:
            return createEventFromStorage(description, isDone, args);

        case F:
            return createFixedDurationFromStorage(description, isDone, args);

        default:
            throw new TaskerException(String.format("Unkown task type from storage: %s.", type));
        }
    }
}
