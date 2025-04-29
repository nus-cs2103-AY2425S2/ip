package doot.commands;

import doot.TaskList;
import doot.Ui;

public class HandleFindCommand implements Command {
    public String userInput;
    public TaskList list;

    /**
     * The constructor of the HandleFindCommand, which looks through a tasklist to see if any
     * task has a matching description
     * @param list the tasklist this command will look through
     * @param userInput the entire input of the user
     */
    public HandleFindCommand(TaskList list, String userInput) {
        this.userInput = userInput;
        this.list = list;
    }

    public String execute() {
        String text = this.userInput.substring(5).strip();
        if (text.isEmpty()) {
            Ui.showMessage("Cirno needs something to find! Put something after the command!");
            return "Cirno needs something to find! Put something after the command!";
        } else {
            String found = list.searchWord(text);
            if (found.isEmpty()) {
                Ui.showMessage("Cirno found nothing!");
                return "Cirno found nothing!";
            } else {
                Ui.showMessage(found);
                return found;
            }
        }
    }
}
