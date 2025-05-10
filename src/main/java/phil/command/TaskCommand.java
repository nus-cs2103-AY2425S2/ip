package phil.command;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.TaskList;

/**
 * Represents a command relating to tasks.
 */
public abstract class TaskCommand {
    private TaskList taskList;
    private boolean isInputInvalid;

    /**
     * Sets up the Task Command instance.
     *
     * @param taskList list of tasks.
     * @param isInputInvalid whether input to command is valid.
     */
    public TaskCommand(TaskList taskList, boolean isInputInvalid) {
        this.taskList = taskList;
        this.isInputInvalid = isInputInvalid;
    }

    /**
     * Returns a list of tasks.
     *
     * @return tasklist with a list of tasks.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Throws an InvalidArgumentException with a specified command type
     * if the input is invalid.
     *
     * @param commandType command type to create the InvalidArgumentException with.
     * @throws InvalidArgumentException exception to be thrown.
     */
    public void throwExceptionIfInvalid(CommandType commandType) throws InvalidArgumentException {
        if (!this.isInputInvalid) {
            return;
        }
        throw new InvalidArgumentException(commandType);
    }

    /**
     * Throws an InvalidArgumentException with a specified command type
     * if the input is invalid.
     *
     * @param commandType command type to create the InvalidArgumentException with.
     * @param numTasks number of tasks in task list.
     * @throws InvalidArgumentException exception to be thrown.
     */
    public void throwExceptionIfInvalid(CommandType commandType, int numTasks) throws InvalidArgumentException {
        if (!this.isInputInvalid) {
            return;
        }
        throw new InvalidArgumentException(commandType, numTasks);
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    public abstract String execute();
}
