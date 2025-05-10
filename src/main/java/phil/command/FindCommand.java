package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.TaskList;


/**
 * Represents the command to find tasks.
 */
public class FindCommand extends TaskCommand {
    private List<String> inputArgs;

    /**
     * Sets up the Find Command instance.
     *
     * @param inputArgs input arguments.
     * @param taskList list of tasks.
     * @param isInputInvalid whether input to command is valid.
     */
    public FindCommand(List<String> inputArgs, TaskList taskList, boolean isInputInvalid) {
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
        super.throwExceptionIfInvalid(CommandType.FIND_TASK);
        String searchTerm = String.join(" ", inputArgs.subList(1, inputArgs.size()));
        return super.getTaskList().filteredTasksToString(searchTerm);
    }
}
