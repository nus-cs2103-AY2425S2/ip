package eve.command;

import eve.exception.EveException;
import eve.exception.InvalidTaskNumException;
import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to mark a task as done in the taskList.
 */
public class MarkCommand implements Command {
    private final int num;

    public MarkCommand(String description) {
        this.num = Integer.parseInt(description);
    }

    /**
     * Marks the task at index num - 1 in the taskList as done.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        StringBuilder response = new StringBuilder();
        try {
            taskList.get(num - 1).markAsDone();
            storage.writeToFile(taskList);
            response.append("Nice! I've marked this task as done:\n\t")
                    .append(taskList.get(num - 1).toString());
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
