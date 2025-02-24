package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * ListCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            toDoList.deleteTask(Integer.parseInt(input) - 1);
            return UI.informThatTaskDeleteIsSuccessful();
        } catch (IndexOutOfBoundsException e) {
            return UI.informThatTaskDoesNotExist();
        } catch (NumberFormatException e) {
            return UI.informThatTaskIndexIsInvalid();
        }
    }
}
