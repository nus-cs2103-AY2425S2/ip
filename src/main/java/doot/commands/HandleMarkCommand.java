package doot.commands;

import doot.Parser;
import doot.TaskList;
import doot.Ui;
import doot.Storage;

import java.io.IOException;


public class HandleMarkCommand implements Command{
    public String userInput;
    public TaskList list;

    public HandleMarkCommand(String userInput, TaskList list) {
        this.userInput = userInput;
        this.list = list;
    }




    public String execute() throws IOException {
        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (Parser.isValidIndex(index, list)) {
            if (list.getTask(index).isDone()) {
                return "Task already done!\n\n" + list.returnList();
            }
            list.mark(index);
            Ui.showMessage("Eye'm the strongest!\n\n" + list.returnList());
            Storage.saveList(list);
            return "Job's finished!\n\n" + list.returnList();
        } else {
            Ui.showMessage("I cant do that! That number is wrong!");
            return "I cant do that! That number is wrong!";
        }
    }
}
