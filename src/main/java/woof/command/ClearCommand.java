package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

public class ClearCommand extends Command {
    /**
     * Creates an instance of a command for clearing all tasks.
     */
    public ClearCommand() {
        super();
    }

    /**
     * Executes the command by adding a deadline task in the task list and displaying a message.
     *
     * @param tasks Current task list.
     * @param ui Ui instance for displaying a message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList.clear();
        ui.displayTasksCleared();
    }
}
