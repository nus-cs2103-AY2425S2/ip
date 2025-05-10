package tom.command;

import tom.exception.TomCommandException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents an abstract command in the application.
 */
public abstract class Command {

    protected int id = 1;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TomCommandException;

    public abstract boolean isExit();
}
