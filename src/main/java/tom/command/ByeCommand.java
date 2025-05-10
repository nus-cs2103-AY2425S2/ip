package tom.command;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {

    private static final int BYE_TIMEOUT = 3;

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The task list.
     * @param ui      The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Display goodbye message to user.
        ui.showMessage(id, "Tom is tired from playing. He'll go home in a few seconds!");

        // Close the JavaFx application after a few seconds.
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.schedule(new Runnable() {
            @Override
            public void run() {
                javafx.application.Platform.exit();
            }
        }, BYE_TIMEOUT, TimeUnit.SECONDS);
    };

    /**
     * Indicates whether this command exits the application.
     *
     * @return true if this command exits the application, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
