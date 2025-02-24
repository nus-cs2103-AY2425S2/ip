package runny.commands;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;



/**
 * Displays a goodbye message
 * The application exits after this commands is executed.
 */
public class ByeCommand implements Command {

    /**
     * Executes command by displaying a goodbye message to the user.
     *
     * @param ui      user interface for interacting with users.
     * @param storage The storage for writing and reading data.
     * @param tasks   The list of tasks.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) {
        assert ui != null && storage != null && tasks != null : "One of the three objects, "
                + "ui,storage or tasks is null";
        ui.printMessage("It has been my pleasure to serve you!\nGoodbyeee...");
        closeChatbot();
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {

    }

    /**
     * Closes the chatbot after two seconds of delay.
     */
    public void closeChatbot() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new BlankCommand();
    }


}
