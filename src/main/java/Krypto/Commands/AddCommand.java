package Krypto.Commands;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.TaskList;
import Krypto.Task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private Task newTask;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.newTask = task;
    }

    @Override
    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        tasks.addTask(newTask);
        storage.store(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}