package eve.command;

import eve.exception.EveException;
import eve.exception.InvalidTaskNumException;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to mark a task as not done in the taskList.
 */
public class UnmarkCommand implements Command {
    private final int num;

    public UnmarkCommand(String description) {
        this.num = Integer.parseInt(description);
    }

    /**
     * Marks the task at index num - 1 in the taskList as not done.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        StringBuilder response = new StringBuilder();
        try {
            taskList.get(num - 1).markAsNotDone();
            response.append("OK, I've marked this task as not done yet:\n\t")
                    .append(taskList.get(num - 1).toString());
            storage.writeToFile(taskList);
        } catch (IndexOutOfBoundsException e) {
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
