package pixel.command;

import pixel.task.TaskList;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

public class UpdateAllCommand extends Command {
    private final boolean isCompleted;
    private final int from;
    private final int to;

    public UpdateAllCommand(boolean isCompleted, int from, int to) {
        this.isCompleted = isCompleted;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws PixelException {
        if (this.from > this.to) {
            throw new PixelException("Please enter a valid range of task numbers!");
        }
        String response;
        if (isCompleted) {
            response = Ui.markAllResponse(taskList.markAll(this.from, this.to));
        } else {
            response = Ui.unmarkAllResponse(taskList.unmarkAll(this.from, this.to));
        }
        storage.save(taskList);
        return response;
    }
}
