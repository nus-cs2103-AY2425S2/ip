package ujin.command;

import ujin.helper.TaskProcessor;
import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Represents a command to exit from the system.
 * This class inherits from the {@link Command} class
 * and implements the execute behavior of exiting.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super.setExit(true);
    }
    /**
     * Executes the command by exiting the system and displaying goodbye message through the UI.
     *
     * @param taskList The task list containing all tasks.
     * @param ui       The user interface handler.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String filePath = "./data/tasks.txt";
        TaskProcessor.writeTasksToFile(taskList, filePath);
        return ui.fareWell();
    }
}
