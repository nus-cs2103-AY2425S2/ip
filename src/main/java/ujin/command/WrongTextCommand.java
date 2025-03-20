package ujin.command;

import ujin.task.TaskList;
import ujin.ui.Ui;

public class WrongTextCommand extends Command {

    /**
     * Saying the command is wrong through the UI.
     *
     * @param taskList The task list containing all tasks.
     * @param ui       The user interface handler.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.sayWrongCommand();
    }
}