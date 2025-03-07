package commands;

import controller.Console;
import datastructure.TaskList;
import exception.InvalidInputException;

/**
 * {@code DeleteCommand} class responsible for executing deletion
 */
public class DeleteCommand extends AbstractCommand {
    private final int taskId;

    /**
     * Construct a {@code DeleteCommand} instance with a int for the id
     *
     * @param taskId Id of task to be deleted from list
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Execute the command by deleting task from list then printing the message to ui
     *
     * @param taskList task list that contains all the task
     * @param console       user interface that will facilitate printing
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    @Override
    public void execute(TaskList taskList, Console console) throws InvalidInputException {
        this.message = taskList.delete(this.taskId);
        console.showTaskMessage(this.message);
    }
}
