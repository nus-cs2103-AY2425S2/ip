package godbot.parser;

import godbot.task.TaskList;
import godbot.task.Task;
import godbot.task.ToDo;
import godbot.task.Deadline;
import godbot.task.Event;
import godbot.storage.Storage;
import godbot.ui.Ui;
import java.lang.StringBuilder;

/**
 * The Parser class is responsible for interpreting and processing user commands.
 * It parses actions from the user and maps them to the appropriate functions.
 */
public class Parser {

    /**
     * Processes the user's command and performs the corresponding action via the functions.
     *
     * @param input   The command input from the user.
     * @param tasks   The current task list to which tasks may be added or displayed.
     * @param storage The storage system used to save tasks persistently.
     * @param ui      The user interface for displaying messages and feedback.
     * @return A response string containing the chatbot's reply.
     */
    public static String processCommand(String input, TaskList tasks, Storage storage, Ui ui) {
        StringBuilder response = new StringBuilder();
        try {
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];
            String argument = (inputParts.length > 1) ? inputParts[1] : "";

            if (command.equals("bye")) {
                response.append("Begone, mortal.");
                return response.toString();
            } else if (command.equals("list")) {
                response.append(tasks.showTasks());
            } else if (command.equals("mark")) {
	        int markIndex = Integer.parseInt(argument) - 1;
		tasks.getTask(markIndex).markDone();
		storage.save(tasks.getAllTasks());
		response.append("Marked as done: ")
			.append(tasks.getTask(markIndex));
	    } else if (command.equals("unmark")) {
                int unmarkIndex = Integer.parseInt(argument) - 1;
		tasks.getTask(unmarkIndex).markNotDone();
		storage.save(tasks.getAllTasks());
		response.append("Marked as not done: ")
			.append(tasks.getTask(unmarkIndex));
	    } else if(command.equals("delete")) {
		int deleteIndex = Integer.parseInt(argument) - 1;
		Task removedTask = tasks.deleteTask(deleteIndex);
		storage.save(tasks.getAllTasks());
		response.append("Removed: ")
			.append(removedTask);
	    }
	      else if (command.equals("todo")) {
                Task task = new ToDo(argument);
                tasks.addTask(task);
                storage.save(tasks.getAllTasks());
                response.append("Added: ").append(task);
            } else if (command.equals("deadline")) {
                String[] parts = argument.split(" /by ");
                if (parts.length < 2) {
                    response.append("Invalid deadline format. Use: deadline <task> /by <time>");
                } else {
                    Task task = new Deadline(parts[0], parts[1]);
                    tasks.addTask(task);
                    storage.save(tasks.getAllTasks());
                    response.append("Added: ").append(task);
                }
            } else if (command.equals("event")) {
                String[] parts = argument.split(" /from | /to", 3);
                if (parts.length < 3) {
                    response.append("Invalid event format. Use: event <task> /from <start> /to <end>");
                } else {
                    Task task = new Event(parts[0], parts[1], parts[2]);
                    tasks.addTask(task);
                    storage.save(tasks.getAllTasks());
                    response.append("Added: ").append(task);
                }
            } else if (command.equals("find")) {
                response.append(tasks.findTasks(argument));
            } else if (command.equals("remind")) {
                response.append("Here is what you need to do mortal: \n" + tasks.showTasks());
            } else {
                response.append("Speak properly, mortal. I do not understand you.");
            }
        } catch (Exception e) {
            response.append("Invalid command, mortal.");
        }
        return response.toString();
    }
}

