package stonks.command;

import stonks.storage.Storage;
import stonks.task.Task;
import stonks.task.TaskManager;

/**
 * Deletes a certain task from the list of tasks
 */
public class DeleteCommand extends Command {
    private final int index;
    private static final String MESSAGE = "     Noted. I've removed this task:\n       \n     Now you have %d tasks in the list.";

    public DeleteCommand(int index) {
        assert index >= 0 : "Index should be non-negative";
        this.index = index;
    }

    @Override
    public String execute(TaskManager tm, Storage storage) {
        assert index < tm.size() : "Index should be within the range of the task list";
        if (index >= tm.size()) {
            return "     The task number you entered is invalid.";
        }
        Task t = tm.deleteTask(index);
        storage.save(tm.getTasks());
        return String.format(MESSAGE, tm.size());
    }
}
