package phil.command;

import phil.model.TaskList;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends TaskCommand {

    /**
     * Sets up the ListCommand instance.
     *
     * @param taskList taskList used for accessing the tasks needed.
     */
    public ListCommand(TaskList taskList) {
        super(taskList, false);
    }

    /**
     * Performs the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() {
        return super.getTaskList().toString();
    }
}
