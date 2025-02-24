package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
import SirDuke.backend.task.Task;
import SirDuke.backend.task.DeadlineTask;
import SirDuke.backend.task.EventTask;

import java.time.format.DateTimeParseException;

public class EditCommand extends Command {


    public EditCommand(String input) {
        super(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        String[] parsedInput = input.split(" ", 3);
        int index = Integer.parseInt(parsedInput[0]) - 1;
        Task task = toDoList.getTask(index);
        try {
            String temp1 = parsedInput[1];
            String temp2 = parsedInput[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            return UI.informThatCommandIsIncomplete();
        }
        String editType = parsedInput[1].toLowerCase();
        switch (editType) {
            case "/description":
                String newDescription = parsedInput[2];
                task.setDescription(newDescription);
                break;
            case "/by":
                String newToBeCompletedBy = parsedInput[2];
                if (task instanceof DeadlineTask) {
                    try {
                        ((DeadlineTask) task).setToBeCompletedBy(newToBeCompletedBy);
                    } catch (DateTimeParseException e) {
                        return UI.informThatDateIsInvalid();
                    }
                } else {
                    return UI.informThatCommandDoesNotWorkOnTask(task);
                }
                break;
            case "/start":
                String newStart = parsedInput[2];
                if (task instanceof EventTask) {
                    try {
                        ((EventTask) task).setStartTime(newStart);
                    } catch (DateTimeParseException e) {
                        return UI.informThatDateIsInvalid();
                    }
                } else {
                    return UI.informThatCommandDoesNotWorkOnTask(task);
                }
                break;
            case "/end":
                String newEnd = parsedInput[2];
                if (task instanceof EventTask) {
                    try {
                        ((EventTask) task).setEndTime(newEnd);
                    } catch (DateTimeParseException e) {
                        return UI.informThatDateIsInvalid();
                    }
                } else {
                    return UI.informThatCommandDoesNotWorkOnTask(task);
                }
            default:
                return UI.informThatCommandIsInvalid();
        }
        return UI.informThatTaskHasBeenEdited();
    }
}
