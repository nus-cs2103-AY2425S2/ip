package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
import SirDuke.backend.task.EventTask;
import SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;


public class EditStartTimeCommand extends Command {
    public EditStartTimeCommand(String input) {
        super(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewTime = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewTime[0]) - 1;
            String newTime = taskIndexAndNewTime[1];
            Task task = toDoList.getTask(taskIndex);
            if (task instanceof EventTask) {
                ((EventTask) task).setStartTime(newTime);
                return UI.informThatTaskHasBeenEdited();
            } else {
                return UI.informThatCommandDoesNotWorkOnTask(task);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return UI.informThatCommandIsIncomplete();
        } catch (DateTimeParseException e) {
            return UI.informThatDateIsInvalid();
        } catch (NumberFormatException e) {
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) {
            return UI.informThatTaskDoesNotExist();
        }
    }
}
