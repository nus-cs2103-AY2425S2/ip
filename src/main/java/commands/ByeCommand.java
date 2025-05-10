package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class ByeCommand extends Commands {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        try {
            ui.bye();
            Storage.writeToFile(list.printListForStorage());

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, 2500);

        } catch (Exception e){
            ui.showLoadingError();
        }
    }

    @Override
    public boolean isExit(){
        return true;
    }

}
