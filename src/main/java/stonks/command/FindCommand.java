package stonks.command;

import stonks.storage.Storage;
import stonks.task.Task;
import stonks.task.TaskManager;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String searchString;
    private static final String MESSAGE = "     Here are the matching tasks in your list:\n";

    public FindCommand(String searchString) {
        assert searchString != null;
        assert !searchString.isEmpty();
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskManager tm, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task i: tm.getTasks()) {
            if (i.contains(searchString)) {
                tasks.add(i);
            }
        }
        return MESSAGE + new TaskManager(tasks);
    }
}