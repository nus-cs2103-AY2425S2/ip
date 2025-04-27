package Ninon.Command;

import Ninon.Storage;
import Ninon.TaskList;
import Ninon.Ui;

public class FindCommand extends Command {

    public String description;

    public FindCommand(String description) {
        this.description = description;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.find(description);
        ui.output(message);
        return message;
    }
}
