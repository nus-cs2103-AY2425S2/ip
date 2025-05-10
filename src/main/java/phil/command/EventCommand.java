package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.Event;
import phil.model.TaskList;

/**
 * Represents the command to create an Event task.
 */
public class EventCommand extends TaskCommand {
    private List<String> inputArgs;

    /**
     * Sets up the Event Command instance.
     *
     * @param inputArgs input arguments.
     * @param taskList list of tasks.
     * @param isInputInvalid whether input to command is valid.
     */
    public EventCommand(List<String> inputArgs, TaskList taskList, boolean isInputInvalid) {
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
        super.throwExceptionIfInvalid(CommandType.CREATE_EVENT);
        int fromIndex = inputArgs.indexOf("/from");
        int toIndex = inputArgs.indexOf("/to");
        String description = String.join(" ", inputArgs.subList(1, fromIndex));
        String fromDate = String.join(" ", inputArgs.subList(fromIndex + 1, toIndex));
        String toDate = String.join(" ", inputArgs.subList(toIndex + 1, inputArgs.size()));
        return super.getTaskList().addTask(new Event(description, fromDate, toDate));
    }
}
