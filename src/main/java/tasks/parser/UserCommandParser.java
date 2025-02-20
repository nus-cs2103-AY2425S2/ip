package tasks.parser;

import java.io.IOException;

import exceptions.MarkException;
import exceptions.TaskException;
import javafx.application.Platform;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskManager;
import tasks.Todo;
import ui.Greeting;

/**
 * This class provides methods to parse user-related input. It is primarily used to
 * execute commands based on the given user input to add, create, mark, unmark and view
 * instances of the {@link tasks.Todo Todo}, {@link Deadline Deadline}
 * and {@link Event Event} classes.
 *
 * @author Yashvan
 */
public class UserCommandParser {

    /**
     * Parses command from user and executes appropriate command.
     *
     * @param input The user input.
     * @param taskManager The TaskManager class used to execute the input.
     * @param isRun The condition to continue running the program.
     */
    public static String parseCommand(String input, TaskManager taskManager, boolean[] isRun) {
        assert input != null && !input.isBlank() : "Input should not be null or empty";
        assert taskManager != null : "TaskManager should not be null";
        assert isRun != null && isRun.length > 0 : "isRun should be a valid boolean array";

        try {
            if (input.equals("exit")) {
                isRun[0] = false;
                Platform.exit();
                return "";

            } else if (input.equals("help")) {
                return Greeting.help();

            } else if (input.equals("list")) {
                return taskManager.displayList();

            } else if (input.startsWith("delete")) {
                return TaskManager.deleteTask(input, taskManager);

            } else if (input.startsWith("mark")) {
                return TaskManager.markTask(input, taskManager);

            } else if (input.startsWith("unmark")) {
                return TaskManager.unmarkTask(input, taskManager);

            } else if (input.startsWith("todo")) {
                Todo todoTask = Todo.create(input);
                return taskManager.addTask(todoTask);

            } else if (input.startsWith("deadline")) {
                Deadline deadlineTask = Deadline.create(input);
                return taskManager.addTask(deadlineTask);

            } else if (input.startsWith("event")) {
                Event eventTask = Event.create(input);
                return taskManager.addTask(eventTask);

            } else if (input.startsWith("find")) {
                return taskManager.findTasks(input);

            } else {
                return Greeting.callForHelp();
            }
        } catch (NumberFormatException | MarkException | TaskException | IOException e) {
            return "______________________________________________________________________________________\n"
                    + e.getMessage()
                    + "\n______________________________________________________________________________________\n";
        }
    }
}
