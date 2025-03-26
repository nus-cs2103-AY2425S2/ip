package viktor.commands;

import java.io.IOException;

import viktor.exceptions.ViktorException;
import viktor.storage.Storage;
import viktor.tasks.TaskList;
/**
 * Command to unmark a task as done.
 */
public class UnmarkCommand implements Commandable {
    private int taskNumber;
    private TaskList tasks;

    /**
     * Constructs an UnmarkCommand with the specified task number and task list.
     *
     * @param taskNumber The index of the task to be unmarked as done.
     * @param tasks The list of tasks in which the task will be unmarked as done.
     */
    public UnmarkCommand(int taskNumber, TaskList tasks) {
        this.taskNumber = taskNumber;
        this.tasks = tasks;
    }

    /**
     * Executes the command to unmark a task as done.
     *
     * @throws ViktorException If the input is invalid or there is an error with task creation.
     */
    @Override
    public String execute() throws ViktorException {
        if (taskNumber >= tasks.size()) {
            throw new ViktorException("You're asking for the impossible! That task doesn't exist.");
        }
        tasks.getTask(taskNumber).beUndone();
        String response = "Oh you've yet to finish " + tasks.getTask(taskNumber).getDescription()
                + "? Don't forget: progress waits for no man";
        try {
            Storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Ah something must've gone awry: " + e.getMessage()
                    + " Well, mistakes are but a part of progress.");
        }
        return response.toString();
    }
}
