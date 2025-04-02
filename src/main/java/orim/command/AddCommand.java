package orim.command;

import orim.storage.Storage;
import orim.task.Task;
import orim.tasklist.TaskList;
import orim.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add tasks to the TaskList.
 */
public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     */
    public String execute(TaskList tasks, Ui ui, Storage file) {
        tasks.add(task);
        try {
            file.store(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Got it. I've added this task:\n " + task.toString() + "\n"
            + "Now you have " + tasks.size() + " tasks in the list.";
    }

}
