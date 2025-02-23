package woof.command;

import javafx.application.Platform;
import woof.gui.Ui;
import woof.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    /**
     * Exits the program after displaying a goodbye message for 3 seconds.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Platform.runLater(ui::displayGoodbye);

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.exit();
        }).start();
    }
}
