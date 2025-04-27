package Ninon.Command;

import Ninon.Storage;
import Ninon.TaskList;
import Ninon.Ui;

/**
 * Represents an abstract command that can be executed on a task list.
 * Subclasses should implement specific behaviors for different types of commands.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param taskList The task list to modify.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to handle saving and loading of tasks.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determines if the command signals an exit from the program.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
