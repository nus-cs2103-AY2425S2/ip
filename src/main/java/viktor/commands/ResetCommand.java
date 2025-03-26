package viktor.commands;

import java.io.File;

import viktor.storage.Storage;
import viktor.tasks.TaskList;

/**
 * Command to reset all tasks, effectively clearing the task list and stored data.
 */
public class ResetCommand implements Commandable {
    private TaskList taskList;

    /**
     * Constructs a ResetCommand to reset the task list and clear storage.
     *
     * @param taskList The list of tasks to reset.
     */
    public ResetCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the reset command, clearing the task list and removing saved tasks.
     *
     * @return A response message after resetting the tasks.
     */
    @Override
    public String execute() {
        // Clear the task list
        taskList.clear();

        // Remove the saved tasks file
        File file = new File(Storage.FILE_PATH);
        if (file.exists()) {
            if (file.delete()) {
                return "All tasks have been cleared, and saved data has been removed.";
            } else {
                return "Tasks cleared, but there was an issue deleting the saved data file.";
            }
        } else {
            return "Tasks have been cleared, but no saved data file was found.";
        }
    }
}
