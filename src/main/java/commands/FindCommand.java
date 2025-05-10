package commands;

import storage.Storage;
import task.Task;
import taskList.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Commands {
    private String des;

    public FindCommand(String des){
        this.des = des;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        ArrayList<Task> tasks = list.getTasks(des);
        ui.find(tasks);

    }

    @Override
    public boolean isExit(){
        return false;
    }
}
