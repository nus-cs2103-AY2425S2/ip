package commands;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public abstract class Commands {

    /**
     * Executes the command, performing operations on the given task list, user interface, and storage.
     *
     * @param tasks   The TaskList to be modified or accessed.
     * @param ui      The Ui responsible for user interaction.
     * @param storage The Storage handling task persistence.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether the command signals the program to exit.
     *
     * @return boolean true if the command is an exit command, otherwise false.
     */
    public abstract boolean isExit();

}
