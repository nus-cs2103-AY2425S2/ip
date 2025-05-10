package helix.command;

import helix.enums.CommandType;
import helix.enums.TaskType;
import helix.exception.HelixException;
import helix.exception.InvalidCommandException;
import helix.exception.InvalidTaskTypeException;
import helix.exception.TaskIndexOutOfBoundsException;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Handles the update of an existing task.
 */
public class UpdateCommand extends Command {
    private final int taskIndex;
    private final TaskType expectedType;
    private final String newDetails;

    /**
     * Constructs an UpdateCommand.
     *
     * @param taskIndex    The index of the task to be updated.
     * @param expectedType The expected task type.
     * @param newDetails   The new details for the task.
     */
    public UpdateCommand(int taskIndex, TaskType expectedType, String newDetails) {
        super(getCommandTypeFromTaskType(expectedType));
        this.taskIndex = taskIndex;
        this.expectedType = expectedType;
        this.newDetails = newDetails;
    }

    /**
     * Converts a {@link TaskType} to the corresponding {@link CommandType}.
     *
     * @param taskType The task type to convert.
     * @return The corresponding CommandType.
     */
    private static CommandType getCommandTypeFromTaskType(TaskType taskType) {
        return switch (taskType) {
        case TODO -> CommandType.TODO;
        case DEADLINE -> CommandType.DEADLINE;
        case EVENT -> CommandType.EVENT;
        default -> CommandType.UPDATE; // Assume unknown types are update requests;
        };
    }

    /**
     * Executes the update helix.command, modifying the specified task in the {@link TaskList}.
     *
     * @param taskList The TaskList containing the helix.task to be updated.
     * @param consoleUi The ConsoleUi component used to display messages to the user.
     * @throws HelixException If the task index is invalid or the task type does not match.
     * @throws InvalidCommandException If the new details are in an invalid format.
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) throws HelixException {
        validateTaskIndex(taskList);
        Task task = taskList.getTask(taskIndex);
        validateTaskType(task);

        String formattedDetails = parseNewDetails(expectedType, newDetails);

        taskList.updateTask(taskIndex, formattedDetails, consoleUi);
        consoleUi.showTaskUpdated(task);
    }

    /**
     * Validates that the task index is within the valid range.
     *
     * @param taskList The {@code TaskList} containing the tasks.
     * @throws TaskIndexOutOfBoundsException if the index is invalid.
     */
    private void validateTaskIndex(TaskList taskList) throws TaskIndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }
    }

    /**
     * Validates that the task type matches the expected type.
     *
     * @param task The task to check.
     * @throws InvalidTaskTypeException if the task type does not match.
     */
    private void validateTaskType(Task task) throws InvalidTaskTypeException {
        if (task.getTaskType() != expectedType) {
            throw new InvalidTaskTypeException("Task type mismatch. Expected: " + task.getTaskType());
        }
    }

    /**
     * Parses and formats the new task details based on the expected task type.
     *
     * @param taskType The type of task being updated.
     * @param details The raw user input details.
     * @return The formatted details string.
     * @throws HelixException If the task index is invalid or the task type does not match.
     * @throws InvalidCommandException If the new details are in an invalid format.
     */
    private String parseNewDetails(TaskType taskType, String details) throws HelixException {
        return switch (taskType) {
        case TODO -> details;
        case DEADLINE -> parseDeadlineDetails(details);
        case EVENT -> parseEventDetails(details);
        default -> throw new InvalidTaskTypeException("Unsupported task type.");
        };
    }

    /**
     * Parses the details for a deadline task.
     *
     * @param details The raw user input details.
     * @return The formatted deadline details.
     * @throws InvalidCommandException if the format is invalid.
     */
    private String parseDeadlineDetails(String details) throws InvalidCommandException {
        String[] deadlineParts = details.split(" /by ", 2);
        if (deadlineParts.length != 2) {
            throw new InvalidCommandException(
                    "Invalid format. Use: update <taskNum> deadline <description> /by <due date>"
            );
        }
        return deadlineParts[0] + " - " + deadlineParts[1];
    }

    /**
     * Parses the details for an event task.
     *
     * @param details The raw user input details.
     * @return The formatted event details.
     * @throws InvalidCommandException if the format is invalid.
     */
    private String parseEventDetails(String details) throws InvalidCommandException {
        String[] eventParts = details.split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new InvalidCommandException(
                    "Invalid format. Use: update <taskNum> event <description> /from <start> /to <end>"
            );
        }
        return eventParts[0] + " - " + eventParts[1] + " - " + eventParts[2];
    }
}
