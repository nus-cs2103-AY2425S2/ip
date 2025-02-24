package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
import SirDuke.backend.task.Task;

public class EditDescriptionCommand extends Command {
    public EditDescriptionCommand(String input) {
        super(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewDescription = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewDescription[0]) - 1;
            String newDescription = taskIndexAndNewDescription[1];
            Task task = toDoList.getTask(taskIndex);
            task.setDescription(newDescription);
            return UI.informThatTaskHasBeenEdited();
        } catch (ArrayIndexOutOfBoundsException e) {
            return UI.informThatCommandIsIncomplete();
        } catch (NumberFormatException e) {
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) {
            return UI.informThatTaskDoesNotExist();
        }
    }
}