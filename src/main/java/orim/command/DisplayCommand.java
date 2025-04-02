package orim.command;

import orim.storage.Storage;
import orim.tasklist.TaskList;
import orim.ui.Ui;

/**
 * Represents a command to display the current TaskList.
 */
public class DisplayCommand extends Command {

    public DisplayCommand() {
        super(false);
    }

    /**
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     */
    public String execute(TaskList  tasks, Ui ui, Storage file) {
        return Ui.showListing(tasks);
    }

}
