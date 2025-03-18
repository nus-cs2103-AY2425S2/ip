package clank.command;

import clank.exception.ClankException;
import clank.task.Deadline;
import clank.task.Event;
import clank.task.Task;
import clank.task.TaskList;
import clank.task.Todo;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command to add a new task (Todo, Deadline, or Event).
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an {@code AddCommand} based on the user's input.
     * Parses the command to determine the type of task (Todo, Deadline, or Event)
     * and initializes the corresponding task.
     *
     * @param input The raw input string from the user.
     * @throws ClankException If the input does not match the expected format
     *                        or if the command type is unknown.
     */
    public AddCommand(String input) throws ClankException {
        String[] parts = input.split(" ", 2);

        String mainCommand = parts[0].toLowerCase();
        String[] taskDetails = parts.length > 1 ? parts[1].split("/by | /from | /to ", 3) : new String[]{""};
        assert taskDetails.length > 0 : "Task details should not be empty";

        switch (mainCommand) {
        case "todo":
            this.task = createTodo(taskDetails);
            break;
        case "deadline":
            this.task = createDeadline(taskDetails);
            break;
        case "event":
            this.task = createEvent(taskDetails);
            break;
        default:
            throw new ClankException(ClankException.ErrorType.UNKNOWN_TASK_TYPE, "");
        }
    }

    /**
     * Creates a Todo task.
     */
    private Task createTodo(String[] taskDetails) throws ClankException {
        if (taskDetails.length != 1 || taskDetails[0].trim().isEmpty()) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT, "todo <description>");
        }
        return new Todo(taskDetails[0].trim());
    }

    /**
     * Creates a Deadline task.
     */
    private Task createDeadline(String[] taskDetails) throws ClankException {
        if (taskDetails.length != 2 || taskDetails[0].trim().isEmpty() || taskDetails[1].trim().isEmpty()) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                    "deadline <description> /by <d/M/yyyy HHmm>");
        }
        return new Deadline(taskDetails[0].trim(), taskDetails[1].trim());
    }

    /**
     * Creates an Event task.
     */
    private Task createEvent(String[] taskDetails) throws ClankException {
        if (taskDetails.length != 3 || taskDetails[0].trim().isEmpty()
                || taskDetails[1].trim().isEmpty() || taskDetails[2].trim().isEmpty()) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                    "event <description> /from d/M/yyyy HHmm /to <d/M/yyyy HHmm>");
        }
        return new Event(taskDetails[0].trim(), taskDetails[1].trim(), taskDetails[2].trim());
    }

    /**
     * Executes the add command by adding the task to the task list and saving it to storage.
     *
     * @param taskList The task list to modify.
     * @param ui The UI instance to interact with the user.
     * @param storage The storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "TaskList should not be null.";
        assert storage != null : "Storage should not be null.";

        taskList.addTask(task);
        storage.saveTasks(taskList);
    }
}
