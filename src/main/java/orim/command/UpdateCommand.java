package orim.command;

import orim.storage.Storage;
import orim.tasklist.TaskList;
import orim.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to update the state of the task(done or not done).
 */

public class UpdateCommand extends Command {

    private final int index;
    private final boolean shouldMark;


    public UpdateCommand(int index, boolean shouldMark) {
        super(false);
        this.index = index;
        this.shouldMark = shouldMark;
    }

    /**
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     */
    public String execute(TaskList tasks, Ui ui, Storage file) {
        if (shouldMark) {
            String output = tasks.get(index).mark();
            try {
                file.store(tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        } else {
            String output = tasks.get(index).unmark();
            try {
                file.store(tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        }
    }

}
