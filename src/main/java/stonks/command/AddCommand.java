package stonks.command;

import stonks.storage.Storage;
import stonks.task.Task;
import stonks.task.TaskManager;

/**
 * Command which adds a task into the taskManager
 */
public class AddCommand extends Command {
    private final Task task;
    private final static String MESSAGE = "     Got it. I've added this task:\n"
            + "       %s\n"
            + "     Now you have %d tasks in the list.";

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskManager tm, Storage storage) {
        tm.addTask(task);
        assert tm.size() > 0 : "TaskManager should have at least 1 task";
        storage.save(tm.getTasks());
        return String.format(MESSAGE, task.toString(), tm.size());
    }
}