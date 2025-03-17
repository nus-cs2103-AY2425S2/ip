package buddy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user input and executing the corresponding commands.
 * This class is responsible for interpreting user commands and delegating the
 * appropriate operations to the TaskList and Storage classes. It supports commands
 * for listing tasks, adding tasks (ToDo, Deadline, Event), marking tasks as done,
 * unmarking tasks, deleting tasks, and finding tasks based on keywords.
 */
public class Parser {

    /**
     * Parses and executes the given command.
     *
     * Depending on the user's input, this method performs actions such as
     * listing tasks, marking tasks as done, unmarking tasks, deleting tasks,
     * or adding new tasks. For unsupported commands, an error message is returned.
     *
     * @param input    The user input command as a string.
     * @param taskList The task list to modify based on the command.
     * @param storage  The storage system to save changes to the task list.
     * @return A string response to be displayed to the user, indicating the result of the command.
     * @throws IOException If an error occurs while saving to the storage.
     */
    public static String parseCommand(String input, TaskList taskList, Storage storage) throws IOException {
        String command = input.split(" ")[0].toLowerCase();
        if (command.equals("list")) {
            return taskList.listTasks();
        } else if (command.equals("mark")) {
            int index = parseTaskIndex(input, "mark");
            String response = taskList.markTaskAsDone(index);
            storage.save(taskList);
            return response;
        } else if (command.equals("unmark")) {
            int index = parseTaskIndex(input, "unmark");
            String response = taskList.unmarkTaskAsDone(index);
            storage.save(taskList);
            return response;
        } else if (command.equals("delete")) {
            int index = parseTaskIndex(input, "delete");
            String response = taskList.deleteTask(index);
            storage.save(taskList);
            return response;
        } else if (input.isEmpty()) {
            return Ui.getErrorMessage("Please provide an input.");
        } else if (command.equals("todo") || command.equals("deadline")
                || command.equals("event")) {
            return parseTask(taskList, input, storage);
        } else if (command.equals("find")) {
            String keyword = input.substring(4).trim();
            if (keyword.isEmpty()) {
                return Ui.getErrorMessage("Please specify a keyword to search for.");
            } else {
                return taskList.findTasks(keyword);
            }
        }
        return Ui.getErrorMessage("Sorry, I'm not sure what you mean. Please check your input and try again.");
    }

    /**
     * Parses a task addition command and adds the corresponding task to the task list.
     *
     * Supports three types of tasks: ToDo, Deadline, and Event. Each type of task
     * requires specific formatting in the user input.
     *
     * @param taskList The task list to which the new task will be added.
     * @param input    The user input command specifying the task to be added.
     * @param storage  The storage system to save changes to the task list.
     * @return A string response confirming the addition of the task or an error message.
     * @throws IOException If an error occurs while saving to the storage.
     */
    private static String parseTask(TaskList taskList, String input, Storage storage) throws IOException {
        input = input.replaceAll("/", "");
        String command = input.split(" ")[0].toLowerCase();
        if (command.equals("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                return "The description of a todo cannot be empty.";
            } else {
                ToDo newTask = new ToDo(description);
                String output = taskList.addTask(newTask);
                storage.save(taskList);
                return output;
            }
        } else if (command.equals("deadline")) {
            try {
                Deadline newTask = parseDeadline(input);
                String output = taskList.addTask(newTask);
                storage.save(taskList);
                return output;
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Invalid date format. Please use yyyy-MM-dd HHmm.";
            }
        } else if (command.equals("event")) {
            try {
                Event newTask = parseEvent(input);
                String output = taskList.addTask(newTask);
                storage.save(taskList);
                return output;
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Invalid date format. Please use yyyy-MM-dd HHmm.";
            }
        }
        return "Unknown command. Please try again.";
    }

    private static Deadline parseDeadline(String input) throws DateTimeParseException {
        String[] parts = input.substring(8).split(" by ", 2);
        if (parts[0].trim().isEmpty() || parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The description or deadline must be provided.");
        }
        LocalDateTime deadline = parseDate(parts[1]);
        return new Deadline(parts[0].trim(), deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    private static Event parseEvent(String input) throws DateTimeParseException {
        String[] parts = input.substring(5).split(" from | to ", 3);
        if (parts[0].trim().isEmpty() || parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new IllegalArgumentException("The description, start time or end time of an event must be provided.");
        }
        LocalDateTime start = parseDate(parts[1]);
        LocalDateTime end = parseDate(parts[2]);
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Error: Start time must be before end time.");
        }
        return new Event(parts[0].trim(), start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    private static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateString.trim(), formatter);
    }


    /**
     * Parses the task index from a command input string.
     *
     * This method is used to extract the task index for commands like "mark",
     * "unmark", or "delete". If the input is invalid, an error message is displayed.
     *
     * @param input   The full command string entered by the user.
     * @param command The specific command (e.g., "mark", "delete").
     * @return The parsed task index, or -1 if the input is invalid.
     */
    private static int parseTaskIndex(String input, String command) {
        assert input != null : "Input should not be null.";
        assert command != null : "Command should not be null.";
        try {
            return Integer.parseInt(input.substring(command.length()).trim());
        } catch (NumberFormatException e) {
            Ui.getErrorMessage("Invalid task number.");
            return -1;
        }
    }
}
