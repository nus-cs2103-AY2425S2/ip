package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.Deadline;
import phil.model.TaskList;

/**
 * Represents the command to create a Deadline task.
 */
public class DeadlineCommand extends TaskCommand {
    private List<String> inputArgs;

    /**
     * Sets up the Deadline Command instance.
     *
     * @param inputArgs input arguments.
     * @param taskList list of tasks.
     * @param isInputInvalid whether input to command is valid.
     */
    public DeadlineCommand(List<String> inputArgs, TaskList taskList, boolean isInputInvalid) {
        super(taskList, isInputInvalid);
        this.inputArgs = inputArgs;
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() throws InvalidArgumentException {
        super.throwExceptionIfInvalid(CommandType.CREATE_DEADLINE);
        int byIndex = inputArgs.indexOf("/by");
        String description = String.join(" ", inputArgs.subList(1, byIndex));
        String byDate = String.join(" ", inputArgs.subList(byIndex + 1, inputArgs.size()));
        return super.getTaskList().addTask(new Deadline(description, byDate));
    }
}
