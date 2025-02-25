package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.backend.exception.IllegalStartAndEndTimeException;
import SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

public class EventCommand extends Command{

    /**
     * Create a Delete command.
     */
    public EventCommand(String input) {
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
            String[] eventName = input.split(" /start ");
            String[] startAndEnd = eventName[1].split(" /end ");
            toDoList.createEventTask(eventName[0], startAndEnd[0], startAndEnd[1]);
            Task eventTask = toDoList.getTask(toDoList.getLength() - 1);
            assert (eventTask != null) : "Task should not be null";
            return UI.informThatTaskHasBeenCreated(eventTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            return UI.informThatCommandIsIncomplete();
        } catch (DateTimeParseException e) {
            return UI.informThatDateIsInvalid();
        } catch (IllegalStartAndEndTimeException e) {
            return e.toString();
        }
    }
}