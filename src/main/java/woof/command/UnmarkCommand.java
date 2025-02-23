package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] inputs) {
        super(inputs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws Exception {
        int index = this.parseIndex(inputs[0]);
        TaskList.unmark(index);
        ui.displayTaskUnmarked(TaskList.get(index));
    }
}
