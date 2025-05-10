package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class ListCommand extends Commands {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        ui.list(list);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
