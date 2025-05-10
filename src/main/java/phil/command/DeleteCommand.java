package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.TaskList;


/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends TaskCommand {
    private List<String> inputArgs;
    private int numTasks;

    /**
     * Sets up the Delete Command instance.
     *
     * @param inputArgs input arguments.
     * @param taskList list of tasks.
     * @param isInputInvalid whether input to command is valid.
     * @param numTasks number of tasks in task list.
     */
    public DeleteCommand(List<String> inputArgs, TaskList taskList, boolean isInputInvalid, int numTasks) {
        super(taskList, isInputInvalid);
        this.inputArgs = inputArgs;
        this.numTasks = numTasks;
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() throws InvalidArgumentException {
        super.throwExceptionIfInvalid(CommandType.DELETE_TASK, this.numTasks);
        int taskToDelete = Integer.parseInt(this.inputArgs.get(1));
        return super.getTaskList().deleteTask(taskToDelete);
    }
}
