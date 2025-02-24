package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{


    /**
     * Create a Deadline command.
     */
    public DeadlineCommand(String input) {
        super(input);
    }
    /**
     * Deadline does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] split = input.split(" /by ");
            toDoList.createDeadlineTask(split[0], split[1]);
            Task deadlineTask = toDoList.getTask(toDoList.getLength() - 1);
            assert (deadlineTask != null) : "Task should not be null";
            return UI.informThatTaskHasBeenCreated(deadlineTask);
        } catch (DateTimeParseException e) { //date is invalid
            return UI.informThatDateIsInvalid();
        } catch (ArrayIndexOutOfBoundsException e) { //command is incomplete
            return UI.informThatCommandIsIncomplete();
        }
    }
}
