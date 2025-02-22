package zea.command;

import java.util.Objects;

import zea.Storage;
import zea.ZeaException;
import zea.task.Tasks;
import zea.task.Todo;
import zea.ui.Ui;

/**
 * A command for creating a todo
 */
public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        if (description.isEmpty()) {
            throw new ZeaException("Description cannot be empty.");
        }
        Todo t = new Todo(description);
        tasks.addTask(t);
        storage.save(tasks);
        return Ui.formatItem("Got it. I've added this task:", t,
                "Now you have " + tasks.getTotalTasks() + " tasks in the list");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return Objects.equals(this.description, ((TodoCommand) other).description);
    }
}
