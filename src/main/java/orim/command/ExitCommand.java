package orim.command;

import orim.storage.Storage;
import orim.tasklist.TaskList;
import orim.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    /**
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     * @throws RuntimeException if file cannot be open with FileWriter.
     */
    public String execute(TaskList tasks, Ui ui, Storage file) {
        Ui.showGoodbye();
        try {
            file.store(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Bye. Hope to see you again soon!\n";
    }


}
