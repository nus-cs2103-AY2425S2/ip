package woof.command;

import woof.gui.Ui;
import woof.task.TaskList;

public class AddTodoCommand extends Command {
    /**
     * Creates an instance of a command for adding todos.
     * @param inputs
     */
    public AddTodoCommand(String[] inputs) {
        super(inputs);
    }

    /**
     * Executes the command by adding a todo task in the task list and displaying a message.
     *
     * @param tasks Current task list.
     * @param ui Ui instance for displaying a message.
     * @throws Exception Possible exceptions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList.addTodo(inputs[0]);
        ui.displayTaskAdded(TaskList.getLast(), TaskList.size());
    }
}
