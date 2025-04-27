package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;
import tooth.task.ToDo;

/**
 * Command that creates a todo task
 */
public class TodoCommand implements Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Execute task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int prevNumTask = tasks.numTask();
        ToDo todo = ToDo.of(description);
        tasks.add(todo);
        ui.say("Added new todo");
        assert prevNumTask < tasks.numTask();
    }
}
