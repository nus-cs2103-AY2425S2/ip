package Tom.commands;

import Tom.Storage;
import Tom.TomException;
import Tom.tasks.Deadlines;
import Tom.tasks.ToDo;
import Tom.tasks.Events;
import Tom.tasks.Task;
import Tom.tasks.TaskList;
import Tom.tasks.TaskType;

/**
 * Represents a user add command to add a task list.
 */
public class AddCommand extends Command{
    private String[] input;
    private TaskType type;

    /**
     * Constructs an AddCommand with the given input parts and task type.
     *
     * @param input The array of input strings containing task details.
     * @param taskType The type of task to be added.
     */
    public AddCommand(String[] input, TaskType taskType) {
        this.input = input;
        this.type = taskType;
    }
    /**
     * Executes the add task command.
     *
     * @param taskList The TaskList instance to add the task to.
     * @return The string representation of the command's response.
     * @throws TomException If there is an error in task creation.
     */
    @Override
    public String execute(TaskList taskList) throws TomException {
        if (input.length < 2 || input[1].isEmpty()) {
            throw new TomException("Please input description!");
        }

        Task task;
        switch (type) {
            case TODO:
                task = new ToDo(input[1]);
                break;
            case DEADLINE:
                task = parseDeadline(input[1]);
                break;
            case EVENT:
                task = parseEvent(input[1]);
                break;
            default:
                throw new TomException("Invalid task type.");
        }

        taskList.addTask(task);
        Storage.saveTasks(taskList.getTaskList());
        return "Task added: \n " + task + "\n There are now " + taskList.getTaskListSize() + " tasks in the list." ;
    }

    /**
     * Parses a deadline task from user input.
     *
     * @param input The user input string.
     * @return A Deadline task.
     * @throws TomException If the format is incorrect.
     */
    private Task parseDeadline(String input) throws TomException {
        String[] deadlineParts = input.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            throw new TomException("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd>");
        }

        String deadlineStr = normalizeDateFormat(deadlineParts[1].trim());

        return new Deadlines(deadlineParts[0].trim(), false, deadlineStr);
    }

    /**
     * Parses an event task from user input.
     *
     * @param input The user input string.
     * @return An Event task.
     * @throws TomException If the format is incorrect.
     */
    private Task parseEvent(String input) throws TomException {
        String[] eventParts = input.split(" /from | /to ", 3);
        if (eventParts.length < 3) {
            throw new TomException("Invalid event format. Use: event <description> "
                    + "/from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }

        String fromStr = normalizeDateFormat(eventParts[1].trim());
        String toStr = normalizeDateFormat(eventParts[2].trim());

        return new Events(eventParts[0].trim(), false, fromStr, toStr);
    }

    /**
     * Normalizes date format if users do not add a 0 in front of the month or day.
     *
     * @param dateStr The user input date string.
     * @return a normalized date string.
     */
    private String normalizeDateFormat(String dateStr) {
        String[] parts = dateStr.split(" ");
        String[] dateParts = parts[0].split("-");

        String year = dateParts[0];
        String month = (dateParts[1].length() == 1) ? "0" + dateParts[1] : dateParts[1];
        String day = (dateParts[2].length() == 1) ? "0" + dateParts[2] : dateParts[2];

        String normalizedDate = year + "-" + month + "-" + day;

        return normalizedDate;
    }
}
