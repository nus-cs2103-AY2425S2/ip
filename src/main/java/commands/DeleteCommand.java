package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;
import task.Task;


public class DeleteCommand extends Commands {
    private int i;

    public DeleteCommand(int i){
        this.i = i;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        Task c = list.getTask(i);
        list.delete(i);
        ui.deleteTask(c, list);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
