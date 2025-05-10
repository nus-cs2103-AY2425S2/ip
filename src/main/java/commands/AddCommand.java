package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;
import task.Task;



public class AddCommand extends Commands {
    private Task t;

    public AddCommand(Task t){
        this.t = t;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.addTask(t);
        ui.addTask(t, list);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
