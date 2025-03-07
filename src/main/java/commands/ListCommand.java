package commands;

import controller.Console;
import datastructure.TaskList;
import exception.InvalidInputException;

/**
 * {@code ListCommand} class responsible for listing all task
 */
public class ListCommand extends AbstractCommand {

    /**
     * Execute the command by getting the list from {@code TaskList} class to pass to
     * {@code Ui} class to print
     *
     * @param taskList task list that contains all the task
     * @param console       user interface that will facilitate printing
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    @Override
    public void execute(TaskList taskList, Console console) throws InvalidInputException {
        this.message = taskList.getTaskList();
        console.showTaskMessage(this.message);
    }
}
