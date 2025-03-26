package viktor.commands;

import java.io.IOException;

import viktor.exceptions.ViktorException;
import viktor.storage.Storage;
import viktor.tasks.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteTaskCommand implements Commandable {
    private int taskNumber;
    private TaskList tasks;

    /**
     * Constructs a DeleteTaskCommand command with the specified task number and task list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @param tasks The list of tasks from which the task will be removed.
     */
    public DeleteTaskCommand(int taskNumber, TaskList tasks) {
        this.taskNumber = taskNumber;
        this.tasks = tasks;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @return A response message after deleting the task.
     * @throws ViktorException If the input is invalid or there is an error with task creation.
     */
    @Override
    public String execute() throws ViktorException {
        if (taskNumber >= tasks.size()) {
            throw new ViktorException("You're asking for the impossible! That task doesn't exist.");
        }

        String taskDescription = tasks.getTask(taskNumber).getDescription();
        tasks.removeTask(taskNumber);

        String response = "I guess " + taskDescription + " is no longer your concern.\n"
                + "Now you have " + tasks.size() + " remaining tasks.";

        // Save the updated task list
        try {
            Storage.save(tasks);
        } catch (IOException e) {
            response += "\nAh, something must've gone awry: " + e.getMessage()
                    + " Well, mistakes are but a part of progress.";
        }

        return response; // Return the final response
    }
}
