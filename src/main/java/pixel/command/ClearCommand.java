package pixel.command;

import pixel.task.TaskList;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

public class ClearCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) throws PixelException {
        String response = Ui.clearResponse(taskList.clearMarked(), taskList.getListSize());
        storage.save(taskList);
        return response;
    }
}
