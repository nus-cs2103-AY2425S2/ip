package orim.command;

import orim.storage.Storage;
import orim.task.Task;
import orim.tasklist.TaskList;
import orim.ui.Ui;

/**
 * Stores the action that needs to perform on the tasks.
 */
public abstract class Command {

    private final boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command to perform its functionality.
     *
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage file);

    public boolean isExit() {
        return this.isExit;
    }

}
