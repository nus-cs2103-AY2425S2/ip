package pluto;

import java.util.regex.Pattern;

/**
 * Represents a Parser class. This class processes
 * and handles user commands
 */
public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_SCHEDULE = "schedule";
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    private TaskList taskList;

    /**
     * Creates a new Parser to process user commands
     * @param taskList the Task List that contains
     *                 the tasks added by users
     */
    public Parser(TaskList taskList) {
        assert taskList != null : "taskList should not be null";
        this.taskList = taskList;
    }

    /**
     * Parses all the user commands to be
     * handled by the chatbot
     * @param input the String that is the user's commands
     * @return a response String based on the parsed command
     * @throws PlutoException if the command format is incorrect
     */
    public String parse(String input) throws PlutoException {
        String[] parts = input.split(" ", 2);
        assert parts.length > 0 : "Input split should produce at least one part";
        String command = parts[0].trim().toLowerCase();

        switch (command) {
        case COMMAND_LIST:
            return taskList.listTasks();
        case COMMAND_MARK:
            return handleMark(parts);
        case COMMAND_UNMARK:
            return handleUnmark(parts);
        case COMMAND_TODO:
            return handleTodo(parts);
        case COMMAND_DEADLINE:
            return handleDeadline(parts);
        case COMMAND_EVENT:
            return handleEvent(parts);
        case COMMAND_DELETE:
            return handleDelete(parts);
        case COMMAND_FIND:
            return handleFind(parts);
        case COMMAND_SCHEDULE:
            return handleSchedule(parts);
        default:
            throw new PlutoException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the mark command
     * @param parts the command parts, where the second part is
     *              the task index
     * @return A response message to show that the task is marked
     * @throws PlutoException If the task number is missing or invalid
     */
    private String handleMark(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("Please provide a task number to mark.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        assert index >= 0 : "Mark index should be non-negative";
        return taskList.markTask(index);
    }

    /**
     * Parses the unmark command
     * @param parts the command parts, where the second part is
     *              the task index
     * @return A response message to show that the task is unmarked
     * @throws PlutoException if the task number is missing or invalid
     */
    private String handleUnmark(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("Please provide a task number to unmark.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        assert index >= 0 : "Unmark index should be non-negative";
        return taskList.unmarkTask(index);
    }

    /**
     * Parses the ToDo command
     * @param parts the command parts, where the second part
     *              is the task description
     * @return A response message indicating the task is added
     * @throws PlutoException if the task description is missing
     */
    private String handleTodo(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("The description of a todo cannot be empty.");
        }
        return taskList.addTask(new ToDo(parts[1]));
    }

    /**
     * Parses the Deadline command
     * @param parts the command parts, where the second part
     *              contains the task description and deadline
     * @return a response message indicating the task has been added
     * @throws PlutoException if the format is incorrect
     */
    private String handleDeadline(String[] parts) throws PlutoException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new PlutoException("The deadline format is incorrect. "
                    + "Please use: deadline <task> /by <yyyy-mm-dd>");
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (!DATE_PATTERN.matcher(deadlineParts[1]).matches()) {
            throw new PlutoException("Invalid date format. Please use yyyy-mm-dd.");
        }
        return taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    /**
     * Parses the Event command
     * @param parts the command parts, where the second part
     *              contains the task description, start and end date
     * @return a response message indicating the task has been added
     * @throws PlutoException if the format is incorrect
     */
    private String handleEvent(String[] parts) throws PlutoException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new PlutoException("The event format is incorrect. Please use: "
                    + "event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        String[] eventParts = parts[1].split(" /from | /to ", 3);
        if (!DATE_PATTERN.matcher(eventParts[1]).matches() || !DATE_PATTERN.matcher(eventParts[2]).matches()) {
            throw new PlutoException("Invalid date format. Please use yyyy-mm-dd.");
        }
        return taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    /**
     * Parses the delete command
     * @param parts the command parts, where the second part
     *              is the task index of the task to be deleted
     * @return a response indicating that the task has been removed
     * @throws PlutoException if the task number is missing or invalid
     */
    private String handleDelete(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("Please provide a task number to delete.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        assert index >= 0 : "Delete index should be non-negative";
        return taskList.removeTask(index);
    }

    /**
     * Parses the find command
     * @param parts the command parts, where the second part
     *              contains the keyword to be searched
     * @return a list of tasks matching the keyword
     * @throws PlutoException if no keyword is provided
     */
    private String handleFind(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("Please provide a keyword for searching.");
        }
        return taskList.findTasks(parts[1]);
    }

    /**
     * Parses the schedule command
     * @param parts the command parts, where the second part
     *              contains the date
     * @return A list of tasks scheduled for the given date
     * @throws PlutoException if no date is provided
     */
    private String handleSchedule(String[] parts) throws PlutoException {
        if (parts.length < 2) {
            throw new PlutoException("Please provide a date for searching.\n" +
                    "Use: schedule <yyyy-mm-dd>");
        }
        if (!DATE_PATTERN.matcher(parts[1]).matches()) {
            throw new PlutoException("Invalid date format. Please use yyyy-mm-dd.");
        }
        return taskList.scheduleTasks(parts[1]);
    }
}
