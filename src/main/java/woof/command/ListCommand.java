package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayTaskList();
    }
}
