package commands;

import controller.Console;
import datastructure.TaskList;
import taskObjects.AbstractTask;
/**
 * {@code AddCommand} class responsible for adding task to list
 */
public class AddCommand extends AbstractCommand {
    private final AbstractTask task;
    /**
     * Constructs a {@code AddCommand} instance with task to be added
     *
     * @param task Task to be added
     */
    public AddCommand(AbstractTask task) {
        this.task = task;
    }

    /**
     * Execute the add command by adding to the list then printing message to ui
     *
     * @param taskList task list that contains all the task
     * @param console       user interface that will facilitate printing
     */
    @Override
    public void execute(TaskList taskList, Console console) {
        this.message = taskList.add(this.task);
        console.showTaskMessage(this.message);
    }
}
