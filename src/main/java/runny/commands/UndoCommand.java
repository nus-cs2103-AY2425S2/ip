package runny.commands;

import java.util.Stack;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;
/**
 * Represents a command to undo the previous user command.
 * This command is responsible for reversing the effects of the most recent user command.
 */
public class UndoCommand implements Command {
    private static Stack<Command> pastCommands = new Stack<>();
    /**
     * Saves a command to the stack of previous commands.
     *
     * @param command The command to be saved for potential undo.
     */
    public static void saveCommand(Command command) {
        if (command instanceof UndoCommand) {
            //Do nothing
        } else {
            pastCommands.push(command);
        }
    }
    /**
     * Executes the undo command, reversing the effects of the most recent user command.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @throws RunnyException If an error occurs during the undo process.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        if (UndoCommand.pastCommands.size() == 0) {
            ui.printMessage("OOPSSS!!! There is no command to be undone.");
        } else {
            ui.printMessage("Undoing the previous command...");
            UndoCommand.pastCommands.pop().undoTask(tasks).doCommand(ui, storage, tasks);
        }
    }
    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
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
