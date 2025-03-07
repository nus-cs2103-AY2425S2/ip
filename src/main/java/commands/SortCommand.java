package commands;

import controller.Console;
import datastructure.TaskList;
import exception.InvalidInputException;

public class SortCommand extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Console console) throws InvalidInputException {
        this.message = taskList.sortTasksByDeadline();
        console.showTaskMessage(this.message);
    }
}
