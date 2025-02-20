package pixel.command;

import pixel.task.TaskList;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

/**
 * Represents an update Command consisting of the integer index of the task and the updated completion status.
 * On execution, updates the completion status of the task corresponding to the index in the TaskList,
 * and triggers a Ui response to be displayed.
 * TaskList contents are saved to storage on execution.
 */
public class UpdateCommand extends Command {
    private final boolean isCompleted;
    private final int taskIndex;

    public UpdateCommand(boolean isCompleted, int taskIndex) {
        this.isCompleted = isCompleted;
        this.taskIndex = taskIndex;
    }

    /**
     * Updates the isDone boolean of the Task stored at the provided taskIndex in the TaskList to the
     * provided isCompleted boolean.
     * Ui response is triggered and TaskList contents are saved to storage after successful task update.
     *
     * @param taskList TaskList storing the tasks
     * @param storage  Storage object handling disk storage for this instance of Pixel
     * @throws PixelException If taskIndex provided does not correspond to a Task in the TaskList
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws PixelException {
        String response;
        if (isCompleted) {
            response = Ui.markResponse(taskList.markTask(this.taskIndex));
        } else {
            response = Ui.unmarkResponse(taskList.unmarkTask(this.taskIndex));
        }
        storage.save(taskList);
        return response;
    }
}
