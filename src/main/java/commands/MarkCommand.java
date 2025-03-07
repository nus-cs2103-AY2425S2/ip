package commands;

import controller.Console;
import datastructure.TaskList;
import exception.InvalidInputException;

/**
 * {@code MarkCommand} class responsible for marking a task completed
 */
public class MarkCommand extends AbstractCommand {
    private final int id;

    /**
     * Constructs a {@code MarkCommand} class with int id of Task
     *
     * @param id Id of the Task to be marked completed
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    /**
     * execute the command by marking the task in task list then calling
     * user interface to print message
     *
     * @param taskList task list that contains all the task
     * @param console       user interface that will facilitate printing
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    @Override
    public void execute(TaskList taskList, Console console) throws InvalidInputException {
        this.message = taskList.mark(id);
        console.showTaskMessage(this.message);
    }
}
