package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class MarkCommand extends Commands {
    private int i;

    public MarkCommand(int i){
        this.i = i;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.mark(i);
        ui.markDone(list, i);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
