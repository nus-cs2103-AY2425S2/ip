package eve.command;

import eve.exception.EveException;
import eve.exception.InvalidTaskNumException;
import eve.task.Task;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command for deleting a task from the taskList.
 */
public class DeleteCommand implements Command {
    private final int num;

    public DeleteCommand(String description) {
        this.num = Integer.parseInt(description);
    }

    /**
     * Deletes the task at index num - 1 from the taskList.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        StringBuilder response = new StringBuilder();
        try {
            Task task = taskList.remove(num - 1);
            storage.writeToFile(taskList);
            response.append("No problem!! I've removed this task:\n\t").append(task).append("\nNow you have ")
                    .append(Integer.toString(taskList.size())).append(" tasks in the list.");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumException();
        }
        return response.toString();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
