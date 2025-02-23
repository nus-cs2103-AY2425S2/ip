package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

public class AddEventCommand extends Command {
    /**
     * Creates an instance of a command for adding events.
     * @param inputs
     */
    public AddEventCommand(String[] inputs) {
        super(inputs);
    }

    /**
     * Executes the command by adding an event task in the task list and displaying a message.
     *
     * @param tasks Current task list.
     * @param ui Ui instance for displaying a message.
     * @throws Exception Possible exceptions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws Exception {
        TaskList.addEvent(inputs[0], inputs[1], inputs[2]);
        ui.displayTaskAdded(TaskList.getLast(), TaskList.size());
    }
}
