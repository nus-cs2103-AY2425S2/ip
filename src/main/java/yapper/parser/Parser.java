package yapper.parser;

import yapper.exceptions.MissingTaskArgs;
import yapper.tasktypes.Deadline;
import yapper.tasktypes.Event;
import yapper.tasktypes.Task;
import yapper.tasktypes.TaskList;
import yapper.tasktypes.ToDo;

/**
 * The {@code Parser} class is responsible for interpreting user commands
 * and executing the corresponding actions on the task list.
 */
public class Parser {

    /**
     * Private constructor to prevent instantiation of the {@code Parser} class.
     */
    private Parser() {
    }

    /**
     * @param request The user input string containing command and relevant task details.
     * @return The command word extracted from the user input.
     */
    public static String parseCommand(String request) {
        return request.split(" ")[0];
    }

    /**
     * Executes the user input request on the provided {@code TaskList}.
     * <p>
     * Supported commands:
     * <ul>
     *   <li>{@code list} - Prints the task list.</li>
     *   <li>{@code todo} - Adds a new ToDo task.</li>
     *   <li>{@code deadline} - Adds a new Deadline task.</li>
     *   <li>{@code event} - Adds a new Event task.</li>
     *   <li>{@code mark} - Marks a task as completed.</li>
     *   <li>{@code unmark} - Unmarks a completed task.</li>
     *   <li>{@code delete} - Deletes a task from the list.</li>
     * </ul>
     * If an unsupported command is entered, an error message is displayed.
     *
     * @param request  The user input command string.
     * @param taskList The current list of tasks.
     * @return The updated task list after executing the command.
     */
    public static String executeCommand(String request, TaskList taskList) {
        String[] splitRequest = request.split(" ");
        String command = splitRequest[0];
        String response = "";
        try {
            switch (command) {
            case "list" -> {
                response = taskList.toString();
            }
            case "todo" -> {
                Task newTask = new ToDo(request);
                response = taskList.addTask(newTask);
            }
            case "deadline" -> {
                Task newTask = new Deadline(request);
                response = taskList.addTask(newTask);
            }
            case "event" -> {
                Task newTask = new Event(request);
                response = taskList.addTask(newTask);
            }
            case "mark" -> {
                int index = splitRequest[1].charAt(0) - '0' - 1;
                response = taskList.markItem(index);
            }
            case "unmark" -> {
                int index = splitRequest[1].charAt(0) - '0' - 1;
                response = taskList.unmarkItem(index);
            }
            case "delete" -> {
                response = taskList.deleteTask(splitRequest[1]);
            }
            case "find" -> {
                // Splits the request into "find" "Keyword / keywords"
                String keyword = request.split(" ", 2)[1];
                response = taskList.findTask(keyword);
            }
            case "reset" -> {
                response = taskList.deleteAllTasks();
            }
            default -> {
                response = "Come on we've been through this. "
                        + "I only understand these 3 commands: 'todo', 'deadline', 'event'.\n"
                        + "Please give it in this format {Command taskname}";
            }
            }
        } catch (MissingTaskArgs e) {
            response = e.getMessage();
        } catch (NumberFormatException e) {
            response = "Please enter a valid index to remove task according to the list.";
        }
        return response;
    }
}
