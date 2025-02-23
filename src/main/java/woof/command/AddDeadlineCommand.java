package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

public class AddDeadlineCommand extends Command {
    /**
     * Creates an instance of a command for adding deadlines.
     * @param inputs
     */
    public AddDeadlineCommand(String[] inputs) {
        super(inputs);
    }

    /**
     * Executes the command by adding a deadline task in the task list and displaying a message.
     *
     * @param tasks Current task list.
     * @param ui Ui instance for displaying a message.
     * @throws Exception Possible exceptions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws Exception {
        TaskList.addDeadline(inputs[0], inputs[1]);
        ui.displayTaskAdded(TaskList.getLast(), TaskList.size());
    }
}
