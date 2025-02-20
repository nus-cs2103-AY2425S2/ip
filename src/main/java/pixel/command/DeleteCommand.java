package pixel.command;

import pixel.task.TaskList;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

/**
 * Represents a deletion Command consisting of the integer index of the task to be deleted.
 * On execution, deletes the task corresponding to the index from the TaskList,
 * and triggers a Ui response to be displayed.
 * TaskList contents are saved to storage on execution.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the Task stored at the provided taskIndex in the TaskList.
     * Ui response is triggered and TaskList contents are saved to storage after successful task deletion.
     *
     * @param taskList TaskList storing the tasks
     * @param storage Storage object handling disk storage for this instance of Pixel
     * @throws PixelException If taskIndex provided does not correspond to a Task in the TaskList
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws PixelException {
        String response = Ui.deleteResponse(taskList.deleteTask(this.taskIndex), taskList.getListSize());
        storage.save(taskList);
        return response;
    }
}
