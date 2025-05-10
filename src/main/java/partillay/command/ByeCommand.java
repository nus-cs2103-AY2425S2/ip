package partillay.command;

import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a command that signals to terminate the program with a closing message.
 * <p>
 *     When executed, this command displays a farewell message and signals
 *     that the program should exit.
 * </p>
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new Bye command.
     * Sets the exit flag.
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Instructs the user interface to show a goodbye message.
     * Task list remains unchanged.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.getGoodbyeMessage();
    }

    /**
     * Checks if this {@code ByeCommand} is equal to another object.
     * <p>
     *     Two instances of {@code ByeCommand} are always considered equal.
     * </p>
     *
     * @param other the object to be compared
     * @return {@code true} if the other object is also an instance of {@code ByeCommand}, otherwise {@code false}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof ByeCommand;
    }
}
