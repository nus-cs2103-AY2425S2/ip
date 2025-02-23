package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public MarkCommand(String[] inputs) {
        super(inputs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws Exception {
        int index = this.parseIndex(inputs[0]);
        TaskList.mark(index);
        ui.displayTaskMarked(TaskList.get(index));
    }
}
