package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;
import seedu.SirDuke.backend.task.Task;

/**
 * The ToDoCommand class represents a command to create a ToDo.
 */
public class ToDoCommand extends Command {

    /**
     * Create a ToDoCommand.
     * @param input
     */
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * ToDo does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Create a ToDo in toDolist.
     * @param toDoList toDoList to create ToDo in and add the task to
     * @return String representing the ToDoCommand's execution status
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        toDoList.createToDoTask(input);
        Task toDoTask = toDoList.getTask(toDoList.getLength() - 1);
        assert (toDoTask != null) : "Task should not be null";
        return UI.informThatTaskHasBeenCreated(toDoTask);
    }
}